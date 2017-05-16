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
        return Contact.class.isAssignableFrom(aClass);
    }
    @Autowired
    ContactDao contactDao;
    private final static Pattern DATE_PATTERN = Pattern.compile("[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
    private final static Pattern EMAIL_PATTERN = Pattern.compile(".+@.+\\.[a-z]+");
    private final static Pattern RNTC_PATTERN = Pattern.compile("^[0-9]+$");
    private final static Pattern SR_PASSPORT_PATTERN = Pattern.compile("^[a-zA-Z]+$");
    private final static Pattern TREE_MARK_PATTERN = Pattern.compile("^[a-zA-Z0-9.]+$");
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
               there_is = contactDao.getContactByRntc(contact.getRntc());
            }catch (Exception ex){
                there_is = "0000000000";
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
        if(!contact.getEmail().isEmpty()){
            if (!isEmail(contact.getEmail())) {
                errors.rejectValue("email", "email.notDog", "Not @email format");
            }
        }
        if(!contact.getBirthday().isEmpty()){
            if(!isDate(contact.getBirthday())){
                errors.rejectValue("birthday","date.notDog","Не вірний формат. РРРР-ММ-ДД");
            }
        }
        if(!contact.getA_stay_address().isEmpty()){
            if(!isTreemark(contact.getA_stay_address()))errors.rejectValue("a_stay_address", "a_stay_address", "Не вірний код клисифікатора");
        }

    }

    private boolean isSrPassport(String value) { return SR_PASSPORT_PATTERN.matcher(value).matches(); }
    private boolean isNumber(String value) { return RNTC_PATTERN.matcher(value).matches(); }
    private boolean isEmail(String value) {
        return EMAIL_PATTERN.matcher(value).matches();
    }
    private boolean isDate(String value) {
        return DATE_PATTERN.matcher(value).matches();
    }
    private boolean isTreemark(String value) {
        return TREE_MARK_PATTERN.matcher(value).matches();
    }
}
