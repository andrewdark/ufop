package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.entity.Inspectors;

import java.util.List;

/**
 * Created by Andrew on 31.05.2017.
 */
@Component
public class InspectorsValidator implements Validator{
    @Autowired
    CheckEventDao checkEventDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return Inspectors.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Inspectors inspectors = (Inspectors) o;
        try{
            List<Inspectors> list = checkEventDao.getInspectorsByCheckEventLink(inspectors.getCheck_event_link());
            if(!list.isEmpty()){
                for (Inspectors items: list){
                    if(items.getUser_link()==inspectors.getUser_link())errors.rejectValue("user_link", "user_link.equals", "Даний співробітник вже добавлений");

                }

            }
        }catch (org.springframework.jdbc.BadSqlGrammarException ex){
            errors.rejectValue("check_event_link", "check_event_link.BadSqlGrammarException", "BadSqlGrammarException: " + ex);
        }
    }
}
