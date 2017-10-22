package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.entity.Sanction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Andrew on 29.03.2017.
 */
@Component
public class SanctionEditValidator implements Validator {
    private final static Pattern DATE_PATTERN = Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
    private final static Pattern PRICE_PATTERN = Pattern.compile("^[0-9.]+$");
    private final static Pattern TREE_MARK_PATTERN = Pattern.compile("^[a-zA-Z0-9.]+$");
    SimpleDateFormat parser = new SimpleDateFormat("yy-MM-dd");
    @Override
    public boolean supports(Class<?> aClass) {
        return Sanction.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sanction sanction = (Sanction) o;
        if(sanction.getCheck_event_link()==0)errors.rejectValue("articles_law_link", "articles_law_link.lenght", "* Обов'язкове поле");
        if(sanction.getCharged_amount_str().equals(""))errors.rejectValue("charged_amount_str", "charged_amount_str", "* Обов'язкове поле");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "check_event_link", "check_event_link.empty", "Відсутня вказівка на перевірку");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sanction_number", "sanction_number.empty", "* Обов'язкове поле");
if(sanction.getService_date().equals("0001-01-01")) errors.rejectValue("service_date", "service_date.empty", "Вкажіть дату вручення постанови");
        if(!isDate(sanction.getService_date()))errors.rejectValue("service_date", "service_date", "Не вірний формат. РРРР-ММ-ДД");
        if(!isDate(sanction.getPlan_date()))errors.rejectValue("plan_date", "plan_date", "Не вірний формат. РРРР-ММ-ДД");
        if(!isDate(sanction.getFact_date()))errors.rejectValue("fact_date", "fact_date", "Не вірний формат. РРРР-ММ-ДД");
        if(!isPrice(sanction.getCharged_amount_str()))errors.rejectValue("charged_amount_str", "charged_amount_str", "Не вірний формат.");
        try{
            Date date_end = parser.parse(sanction.getFact_date());
            Date date_begin = parser.parse(sanction.getService_date());
            if(!date_end.equals(parser.parse("0001-01-01")) && date_begin.after(date_end))
                errors.rejectValue("fact_date","date.notDog","Не вірна дата сплати.");
        }catch (Exception ex){

        }
        if(!isTreemark(sanction.getArticles_law_link())) errors.rejectValue("articles_law_link", "articles_law_link", "Не вірний код клисифікатора");
    }

    private boolean isDate(String value) {
        return DATE_PATTERN.matcher(value).matches();
    }
    private boolean isPrice(String value) { return PRICE_PATTERN.matcher(value).matches(); }
    private boolean isTreemark(String value) {
        return TREE_MARK_PATTERN.matcher(value).matches();
    }
}
