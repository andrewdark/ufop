package ua.pp.darknsoft.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.entity.CheckEventSupplemented;
import ua.pp.darknsoft.entity.CheckingCommObj;

import java.util.regex.Pattern;

/**
 * Created by Dark on 27.03.2017.
 */
@Component
public class EventValidator implements Validator {
    private final static Pattern DATE_PATTERN = Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
    @Override
    public boolean supports(Class<?> aClass) {
        return CheckEventSupplemented.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CheckEventSupplemented event = (CheckEventSupplemented) o;
        boolean co = false;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_date_begin", "event_date_begin.empty", "* обов'язкове поле");
        if(event.getEvent_date_begin().equals("0001-01-01")) errors.rejectValue("event_date_begin", "event_date_begin.isEmpty", "* обов'язкове поле");
        if(event.getCheck_violation()==1) ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_result", "event_result.empty", "* обов'язкове поле");
        for(CheckingCommObj it: event.getCommobj_list()){
            if(it.isChecking()) co=true;
        }

        if(!co)errors.rejectValue("commobj_list", "commobj_list.isEmpty", "* Виберіть хоч один комерційний об'єкт");
        if(!event.getEvent_date_begin().isEmpty()){
            if(!isDate(event.getEvent_date_begin())){
                errors.rejectValue("event_date_begin","date.notDog","Не вірний формат. РРРР-ММ-ДД");
            }
        }
        if(!event.getEvent_date_end().isEmpty()){
            if(!isDate(event.getEvent_date_end())){
                errors.rejectValue("event_date_end","date.notDog","Не вірний формат. РРРР-ММ-ДД");
            }
        }

    }
    private boolean isDate(String value) {
        return DATE_PATTERN.matcher(value).matches();
    }
}
