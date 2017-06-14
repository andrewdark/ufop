package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.entity.Precaution;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by Andrew on 29.03.2017.
 */
@Component
public class PrecautionValidator implements Validator {
    private final static Pattern DATE_PATTERN = Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
    @Autowired
    CheckEventDao checkEventDao;
    SimpleDateFormat parser = new SimpleDateFormat("yy-MM-dd");

    @Override
    public boolean supports(Class<?> aClass) {
        return Precaution.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Precaution precaution = (Precaution) o;
        try {

            if (checkEventDao.getPrecautionByEventAndPrecautionLink(precaution.getCheck_event_link(), precaution.getPrecaution_catalog_link()).get(0).getPrecaution_catalog_link() == precaution.getPrecaution_catalog_link()) {
                errors.rejectValue("precaution_catalog_link", "precaution_catalog_link.lenght", "Прийнятий захід вже існує");
            }
        } catch (Exception ex) {

        }
        if (precaution.getCheck_event_link() == 0)
            errors.rejectValue("check_event_link", "check_event_link.lenght", "Відсутня вказівка на перевірку");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "check_event_link", "check_event_link.empty", "Відсутня вказівка на перевірку");
        if (!isTreemark(precaution.getPlan_date())) errors.rejectValue("plan_date", "plan_date", "Не вірний формат. РРРР-ММ-ДД");
        if (!isTreemark(precaution.getFact_date())) errors.rejectValue("fact_date", "fact_date", "Не вірний формат. РРРР-ММ-ДД");
        if (!isTreemark(precaution.getService_date())) errors.rejectValue("service_date", "service_date", "Не вірний формат. РРРР-ММ-ДД");
        try{
            Date date_end = parser.parse(precaution.getFact_date());
            Date date_begin = parser.parse(precaution.getService_date());
            if(!date_end.equals(parser.parse("0001-01-01")) && date_begin.after(date_end))
                errors.rejectValue("fact_date","date.notDog","Не вірна дата сплати.");
        }catch (Exception ex){

        }
    }

    private boolean isTreemark(String value) {
        return DATE_PATTERN.matcher(value).matches();
    }
}
