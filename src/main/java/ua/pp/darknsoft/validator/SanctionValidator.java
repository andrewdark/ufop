package ua.pp.darknsoft.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.entity.Sanction;

/**
 * Created by Andrew on 29.03.2017.
 */
@Component
public class SanctionValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Sanction.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sanction sanction = (Sanction) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "check_event_link", "check_event_link.empty", "Відсутня вказівка на перевірку");
    }
}
