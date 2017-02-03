package ua.pp.darknsoft.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.entity.IndividualEnterpreneur;

/**
 * Created by Andrew on 03.02.2017.
 */
@Component
public class IndividualEnterpreneurValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        IndividualEnterpreneur individualEnterpreneur = (IndividualEnterpreneur) o;
        if(individualEnterpreneur.getRntc().length()<10 || individualEnterpreneur.getRntc().length()<6) errors.rejectValue("rntc", "rntc.tooShort", "* ");
    }
}
