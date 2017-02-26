package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.IndividualEntrepreneurDao;
import ua.pp.darknsoft.entity.IndividualEntrepreneur;

import java.util.regex.Pattern;

/**
 * Created by Andrew on 03.02.2017.
 */
@Component
public class IndividualEnterpreneurValidator implements Validator {
    @Autowired
    IndividualEntrepreneurDao individualEntrepreneurDao;
    private final static Pattern RNTC_PATTERN = Pattern.compile("^[0-9]+$");
    private final static Pattern SR_PASSPORT_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {

        IndividualEntrepreneur individualEntrepreneur = (IndividualEntrepreneur) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contact_link", "contact_link.empty", "* Обов'язкове поле");
        if(individualEntrepreneur.getSeries_of_passport().isEmpty())
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rntc", "rntc.empty", "* Обов'язкове поле");

        if (!individualEntrepreneur.getRntc().isEmpty()) {
            if (!isNumber(individualEntrepreneur.getRntc()) || individualEntrepreneur.getRntc().length() != 10) {
                errors.rejectValue("rntc", "rntc.notDog", "Не вірний формат");
            }
            String there_is = "";
            try {
                there_is = individualEntrepreneurDao.SelectIEByRntc(individualEntrepreneur.getRntc());
            } catch (Exception ex) {
                 there_is = "0000000000";
            }
            if (there_is.equals(individualEntrepreneur.getRntc())) {
                errors.rejectValue("rntc", "rntc.there_is", "ІПН вже існує");
            }
        }
        if (!individualEntrepreneur.getSeries_of_passport().isEmpty() || !individualEntrepreneur.getNumber_of_passport().isEmpty()) {
            if (!isSrPassport(individualEntrepreneur.getSeries_of_passport()) || individualEntrepreneur.getSeries_of_passport().length() != 2) {
                errors.rejectValue("series_of_passport", "series_of_passport", "Не вірний формат");
            }
            if (!isNumber(individualEntrepreneur.getNumber_of_passport())) {
                errors.rejectValue("number_of_passport", "number_of_passport", "Не вірний формат");
            }
        }


    }

    private boolean isSrPassport(String value) {
        return SR_PASSPORT_PATTERN.matcher(value).matches();
    }
    private boolean isNumber(String value) {
        return RNTC_PATTERN.matcher(value).matches();
    }
}
