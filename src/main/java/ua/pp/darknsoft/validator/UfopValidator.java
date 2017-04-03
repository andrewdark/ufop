package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.UfopDao;
import ua.pp.darknsoft.entity.Ufop;

import java.util.regex.Pattern;

/**
 * Created by Andrew on 06.03.2017.
 */
@Component
public class UfopValidator implements Validator {
    private final static Pattern RNTC_PATTERN = Pattern.compile("^[0-9]+$");
    private final static Pattern SR_PASSPORT_PATTERN = Pattern.compile("^[a-zA-Z]+$");
    private Ufop sqlUfop = new Ufop();
    @Autowired
    UfopDao ufopDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        Ufop ufop = (Ufop) o;
        try {
            sqlUfop = ufopDao.searchUfopByCode(ufop.getUfop_code()).get(0);
        } catch (IndexOutOfBoundsException ex) {
            sqlUfop.setId(0);
        } catch (Exception ex) {
            errors.rejectValue("ufop_code", "ufop_code.lenght", "Помилка" + ex);
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ufop_is", "ufop_is.empty", "Зробіть свій вибір");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ufop_name", "ufop_name.empty", "* Обов'язкове поле");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ufop_code", "ufop_code.empty", "* Обов'язкове поле");
        if(ufop.getNav()!=0){
            if(ufop.getUfop_code().equals(sqlUfop.getUfop_code())){
                errors.rejectValue("ufop_code", "ufop_code.lenght", "Суб'єкт з таким кодом вже існує");
            }
        }
        if(ufop.getNav()==0){
            if(ufop.getUfop_code().equals(sqlUfop.getUfop_code()) && ufop.getId()!=sqlUfop.getId()){
                errors.rejectValue("ufop_code", "ufop_code.lenght", "Суб'єкт з таким кодом вже існує");
            }
        }

        if (ufop.getUfop_is() == 0) {
            if (ufop.getUfop_code().length() != 10 && ufop.getUfop_code().length() > 0)
                errors.rejectValue("ufop_code", "ufop_code.lenght", "Не вірний формат");
            if (!ufop.getSeries_of_passport().isEmpty() || !ufop.getNumber_of_passport().isEmpty()) {
                if (!isSrPassport(ufop.getSeries_of_passport()) || ufop.getSeries_of_passport().length() != 2) {
                    errors.rejectValue("series_of_passport", "series_of_passport", "Не вірний формат");
                }
                if (!isNumber(ufop.getNumber_of_passport())) {
                    errors.rejectValue("number_of_passport", "number_of_passport", "Не вірний формат");
                }
            }
        }
        if (ufop.getUfop_is() == 1) {
            if (ufop.getUfop_code().length() != 8 && ufop.getUfop_code().length() > 0)
                errors.rejectValue("ufop_code", "ufop_code.lenght", "Не вірний формат");
        }
    }

    private boolean isSrPassport(String value) {
        return SR_PASSPORT_PATTERN.matcher(value).matches();
    }

    private boolean isNumber(String value) {
        return RNTC_PATTERN.matcher(value).matches();
    }
}
