package ua.pp.darknsoft.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.entity.StructureOrganization;

/**
 * Created by Andrew on 28.02.2017.
 */
@Component
public class StructureOrganizationValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return StructureOrganization.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        StructureOrganization structureOrganization = (StructureOrganization) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "treemark", "treemark.empty", "* Обов'язкове поле");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "structure_catalog_link", "structure_catalog_link.empty", "* Обов'язкове поле");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user_link", "user_link.empty", "* Обов'язкове поле");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "owner", "owner.empty", "* Обов'язкове поле");
    }
}
