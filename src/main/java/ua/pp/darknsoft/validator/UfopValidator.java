package ua.pp.darknsoft.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.entity.Ufop;

/**
 * Created by Andrew on 06.03.2017.
 */
@Component
public class UfopValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
    Ufop ufop = (Ufop) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ufop_is", "ufop_is.empty", "Зробіть свій вибір");
    }
}
