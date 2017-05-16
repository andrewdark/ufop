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
import java.util.regex.Pattern;

/**
 * Created by Andrew on 29.03.2017.
 */
@Component
public class PunishmentArticlesValidator implements Validator {
    private final static Pattern TREE_MARK_PATTERN = Pattern.compile("^[a-zA-Z0-9.]+$");
    @Autowired
    CheckEventDao checkEventDao;

    @Override
    public boolean supports(Class<?> aClass) {
        return PunishmentArticles.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PunishmentArticles punishmentArticles = (PunishmentArticles) o;
        try {
            List<PunishmentArticles> list = checkEventDao.getPunishmentArticlesByCheckEventLink(punishmentArticles.getCheck_event_link());
            if (!list.isEmpty()) {
                for (PunishmentArticles items : list) {
                    if (items.getArticles_law_link().equals(punishmentArticles.getArticles_law_link()))
                        errors.rejectValue("articles_law_link", "articles_law_link.lenght", "Данна стаття вже добавлена");
                }

            }
        } catch (org.springframework.jdbc.BadSqlGrammarException ex) {
            errors.rejectValue("articles_law_link", "articles_law_link.lenght", "SQL error:" + ex);
        }
        if (punishmentArticles.getCheck_event_link() == 0)
            errors.rejectValue("check_event_link", "check_event_link.lenght", "Відсутня вказівка на перевірку");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "check_event_link", "check_event_link.empty", "Відсутня вказівка на перевірку");
        if(!punishmentArticles.getArticles_law_link().isEmpty()){
            if (!isTreemark(punishmentArticles.getArticles_law_link()))
                errors.rejectValue("articles_law_link", "articles_law_link", "Не вірний код клисифікатора");
        }

    }

    private boolean isTreemark(String value) {
        return TREE_MARK_PATTERN.matcher(value).matches();
    }
}
