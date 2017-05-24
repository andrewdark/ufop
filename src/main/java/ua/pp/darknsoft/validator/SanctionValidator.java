package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.entity.PunishmentArticles;
import ua.pp.darknsoft.entity.Sanction;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Andrew on 29.03.2017.
 */
@Component
public class SanctionValidator implements Validator {
    private final static Pattern DATE_PATTERN = Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
    private final static Pattern PRICE_PATTERN = Pattern.compile("^[0-9.]+$");
    @Autowired
    CheckEventDao checkEventDao;
    @Override
    public boolean supports(Class<?> aClass) {
        return Sanction.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sanction sanction = (Sanction) o;
        try{
            List<Sanction> list = checkEventDao.getSanctionEventByCheckEventLink(sanction.getCheck_event_link());
            if(!list.isEmpty()){
                for (Sanction items: list){
                    if(items.getArticles_law_link().equals(sanction.getArticles_law_link()))errors.rejectValue("articles_law_link", "articles_law_link.lenght", "Дана стаття вже добавлена");

                }

            }

        }catch (org.springframework.jdbc.BadSqlGrammarException ex){
            errors.rejectValue("check_event_link", "check_event_link.empty", "SQL error:" + ex);
        }
        if(sanction.getCheck_event_link()==0)errors.rejectValue("articles_law_link", "articles_law_link.lenght", "* Обов'язкове поле");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "check_event_link", "check_event_link.empty", "Відсутня вказівка на перевірку");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sanction_number", "sanction_number.empty", "* Обов'язкове поле");
if(sanction.getService_date().equals("0001-01-01")) errors.rejectValue("service_date", "service_date.empty", "Вкажіть дату вручення постанови");
        if(!isDate(sanction.getService_date()))errors.rejectValue("service_date", "service_date", "Не вірний формат. РРРР-ММ-ДД");
        if(!isDate(sanction.getPlan_date()))errors.rejectValue("plan_date", "plan_date", "Не вірний формат. РРРР-ММ-ДД");
        if(!isDate(sanction.getFact_date()))errors.rejectValue("fact_date", "fact_date", "Не вірний формат. РРРР-ММ-ДД");
        if(!isPrice(sanction.getCharged_amount_str()))errors.rejectValue("charged_amount", "charged_amount", "Не вірний формат.");
    }

    private boolean isDate(String value) {
        return DATE_PATTERN.matcher(value).matches();
    }
    private boolean isPrice(String value) { return PRICE_PATTERN.matcher(value).matches(); }
}
