package ua.pp.darknsoft.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.entity.Precaution;

/**
 * Created by Andrew on 29.03.2017.
 */
@Component
public class PrecautionValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Precaution.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Precaution precaution = (Precaution) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "check_event_link", "check_event_link.empty", "Відсутня вказівка на перевірку");
    }
}
