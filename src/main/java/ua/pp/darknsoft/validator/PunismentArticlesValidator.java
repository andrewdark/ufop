package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.entity.Lawsuits;
import ua.pp.darknsoft.entity.PunishmentArticles;

import java.util.List;

/**
 * Created by Andrew on 29.03.2017.
 */
@Component
public class PunismentArticlesValidator implements Validator {
    @Autowired
    CheckEventDao checkEventDao;
    @Override
    public boolean supports(Class<?> aClass) {
        return PunishmentArticles.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PunishmentArticles punishmentArticles = (PunishmentArticles) o;
        try{
            List<PunishmentArticles> list = checkEventDao.getPunishmentArticlesByCheckEventLink(punishmentArticles.getCheck_event_link());
            if(!list.isEmpty()){
                for (PunishmentArticles items: list){
                    if(items.getArticles_law_link() == punishmentArticles.getArticles_law_link())errors.rejectValue("articles_law_link", "articles_law_link.lenght", "Данна стаття вже добавлена");
                }

            }
        }catch (org.springframework.jdbc.BadSqlGrammarException ex){
            errors.rejectValue("articles_law_link", "articles_law_link.lenght", "SQL error:" + ex);
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "check_event_link", "check_event_link.empty", "Відсутня вказівка на перевірку");
    }
}
