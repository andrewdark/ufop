package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.entity.CheckEventSupplemented;
import ua.pp.darknsoft.entity.CheckingCommObj;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.regex.Pattern;

/**
 * Created by Dark on 27.03.2017.
 */
@Component
public class EventValidator implements Validator {
    @Autowired
    CheckEventDao checkEventDao;
    private final static Pattern DATE_PATTERN = Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
    @Override
    public boolean supports(Class<?> aClass) {
        return CheckEventSupplemented.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CheckEventSupplemented event = (CheckEventSupplemented) o;
        SimpleDateFormat parser = new SimpleDateFormat("yy-MM-dd");

        boolean co = false;
        CheckEventSupplemented event_db = null;
        try{
            if(event.getId()>0) event_db = checkEventDao.getCheckEventById(event.getId()).get(0);
            if(event_db!=null){
                if(event_db.getCheck_violation()==1 && event.getCheck_violation()==0)
                    errors.rejectValue("check_violation","check_violation.notDog","Неправомірна дія");
            }
        }catch (org.springframework.jdbc.BadSqlGrammarException ex){
            errors.rejectValue("kved_catalog_link", "kved_catalog_link.lenght", "SQL error:" + ex);
        } catch (Exception ex){

        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_date_begin", "event_date_begin.empty", "* обов'язкове поле");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "event_number", "event_number.empty", "* обов'язкове поле");
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
        try{
            Date date_end = parser.parse(event.getEvent_date_end());
            Date date_begin = parser.parse(event.getEvent_date_begin());
            if(!date_end.equals(parser.parse("0001-01-01")) && !date_begin.before(date_end))
                errors.rejectValue("event_date_end","date.notDog","Не вірна дата закінчення.");
        }catch (Exception ex){

        }
    }
    private boolean isDate(String value) {
        return DATE_PATTERN.matcher(value).matches();
    }
}
