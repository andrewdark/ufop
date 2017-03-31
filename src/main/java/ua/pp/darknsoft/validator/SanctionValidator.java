package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.entity.PunishmentArticles;
import ua.pp.darknsoft.entity.Sanction;

import java.util.List;

/**
 * Created by Andrew on 29.03.2017.
 */
@Component
public class SanctionValidator implements Validator {
    @Autowired
    CheckEventDao checkEventDao;
    @Override
    public boolean supports(Class<?> aClass) {
        return Sanction.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Sanction sanction = (Sanction) o;
        try{
            List<Sanction> list = checkEventDao.getSanctionEventByCheckEventLink(sanction.getCheck_event_link());
            if(!list.isEmpty()){errors.rejectValue("check_event_link", "check_event_link.empty", "Санкція вже накладена");


            }
        }catch (org.springframework.jdbc.BadSqlGrammarException ex){
            errors.rejectValue("check_event_link", "check_event_link.empty", "SQL error:" + ex);
        }
        if(sanction.getCheck_event_link()==0)errors.rejectValue("check_event_link", "check_event_link.lenght", "Відсутня вказівка на перевірку");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "check_event_link", "check_event_link.empty", "Відсутня вказівка на перевірку");
    }
}
