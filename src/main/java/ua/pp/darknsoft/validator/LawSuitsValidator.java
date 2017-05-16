package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.entity.Lawsuits;

import java.util.regex.Pattern;

/**
 * Created by Andrew on 29.03.2017.
 */
@Component
public class LawSuitsValidator implements Validator {
    private final static Pattern DATE_PATTERN = Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
    @Autowired
    CheckEventDao checkEventDao;
    @Override
    public boolean supports(Class<?> aClass) {
        return Lawsuits.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Lawsuits lawsuits = (Lawsuits) o;
        try{
            if(!checkEventDao.getLawsuitsByCheckEventLink(lawsuits.getCheck_event_link()).isEmpty()){
                errors.rejectValue("check_event_link", "check_event_link.lenght", "Судовий позов вже існує");
            }
        }catch (Exception ex){

        }
        if(lawsuits.getCheck_event_link()==0)errors.rejectValue("check_event_link", "check_event_link.lenght", "Відсутня вказівка на перевірку");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "check_event_link", "check_event_link.empty", "Відсутня вказівка на перевірку");
    if(!isDate(lawsuits.getFiled_date()))errors.rejectValue("filed_date","filed_date","Не вірний формат. РРРР-ММ-ДД");
    }
    boolean isDate(String value) {
        return DATE_PATTERN.matcher(value).matches();
    }
}
