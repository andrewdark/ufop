package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.KvedDao;
import ua.pp.darknsoft.entity.KvedsUfop;
import ua.pp.darknsoft.entity.OffenseArticles;

import java.util.List;

/**
 * Created by Andrew on 06.04.2017.
 */
@Component
public class KvedsValidator implements Validator{
    @Autowired
    KvedDao kvedDao;
    @Override
    public boolean supports(Class<?> aClass) {
        return KvedsUfop.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    KvedsUfop kvedsUfop = (KvedsUfop) o;
        try{
            List<KvedsUfop> list = kvedDao.getKvedsByUfopLink(kvedsUfop.getUfop_link());
            if(!list.isEmpty()){
                for (KvedsUfop items: list){
                    if(items.getKved_catalog_link().equals(kvedsUfop.getKved_catalog_link()))errors.rejectValue("kved_catalog_link", "kved_catalog_link.lenght", "Даний КВЕД вже добавлений");

                }

            }
        }catch (org.springframework.jdbc.BadSqlGrammarException ex){
            errors.rejectValue("kved_catalog_link", "kved_catalog_link.lenght", "SQL error:" + ex);
        }
    }
}
