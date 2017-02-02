package ua.pp.darknsoft.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.entity.Contact;

/**
 * Created by Andrew on 02.02.2017.
 */
@Component
public class ContactValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Contact contact = (Contact) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "last-name.empty", "Last name must not be empty.");

    }
}
