package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.ContactDao;
import ua.pp.darknsoft.entity.Contact;

import java.util.regex.Pattern;

/**
 * Created by Andrew on 02.02.2017.
 */
@Component
public class ContactValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
    @Autowired
    ContactDao contactDao;


    private final static Pattern RNTC_PATTERN = Pattern.compile("^[0-9]+$");
    private final static Pattern SR_PASSPORT_PATTERN = Pattern.compile("^[a-zA-Z]+$");
    @Override
    public void validate(Object o, Errors errors) {
        Contact contact = (Contact) o;
        //Contact there_is=null;



        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "last_name", "last-name.empty", "* Обов'язкове поле");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "first_name", "first-name.empty", "* Обов'язкове поле");
        if(!contact.getRntc().isEmpty()){
            if(!isNumber(contact.getRntc())||contact.getRntc().length()!=10){errors.rejectValue("rntc", "rntc.notDog", "Не вірний формат");}
            String there_is="";
            try{
               there_is = contactDao.SelectContactByRntc(contact.getRntc());
            }catch (Exception ex){
                there_is="NAN";
            }
            if(there_is.equals(contact.getRntc())){errors.rejectValue("rntc", "rntc.there_is", "ІПН вже існує"); }
        }
        if(!contact.getSeries_of_passport().isEmpty() || !contact.getNumber_of_passport().isEmpty()){
            if(!isSrPassport(contact.getSeries_of_passport())||contact.getSeries_of_passport().length()!=2){
                errors.rejectValue("series_of_passport", "series_of_passport", "Не вірний формат");
            }
            if(!isNumber(contact.getNumber_of_passport())){
                errors.rejectValue("number_of_passport", "number_of_passport", "Не вірний формат");
            }
        }
    }

    private boolean isSrPassport(String value) { return SR_PASSPORT_PATTERN.matcher(value).matches(); }
    private boolean isNumber(String value) { return RNTC_PATTERN.matcher(value).matches(); }
}
