package ua.pp.darknsoft.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.entity.CommercialObject;

/**
 * Created by Andrew on 17.03.2017.
 */
@Component
public class CommobjValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CommercialObject.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CommercialObject co = (CommercialObject) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"ufop_link","ufop_link.empty","* Обов'язкове поле");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"obj_name","obj_name.empty","* Обов'язкове поле");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"a_place_of_reg","a_place_of_reg.empty","* Обов'язкове поле");
    }
}
