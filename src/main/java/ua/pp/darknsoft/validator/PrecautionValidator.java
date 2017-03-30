package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.entity.Precaution;

/**
 * Created by Andrew on 29.03.2017.
 */
@Component
public class PrecautionValidator implements Validator {
    @Autowired
    CheckEventDao checkEventDao;
    @Override
    public boolean supports(Class<?> aClass) {
        return Precaution.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Precaution precaution = (Precaution) o;
        try{
            if(!checkEventDao.getPrecautionByCheckEventLink(precaution.getCheck_event_link()).isEmpty()){
                errors.rejectValue("precaution_catalog_link", "precaution_catalog_link.lenght", "Прийнятий захід вже існує");
            }
        }catch (Exception ex){

        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "check_event_link", "check_event_link.empty", "Відсутня вказівка на перевірку");
    }
}
