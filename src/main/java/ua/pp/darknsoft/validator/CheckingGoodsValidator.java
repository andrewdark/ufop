package ua.pp.darknsoft.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CheckEventDao;
import ua.pp.darknsoft.entity.CheckingGroupOfGoods;

import java.util.List;

/**
 * Created by Andrew on 18.04.2017.
 */
@Component
public class CheckingGoodsValidator implements Validator {
    @Autowired
    CheckEventDao checkEventDao;
    @Override
    public boolean supports(Class<?> aClass) {
        return CheckingGroupOfGoods.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CheckingGroupOfGoods checkingGroupOfGoods = (CheckingGroupOfGoods) o;
        try{
            List<CheckingGroupOfGoods> goodsList = checkEventDao.getCheckingGroupOfGoodsByCheckEventLink(checkingGroupOfGoods.getCheck_event_link());
            for (CheckingGroupOfGoods items: goodsList){
                if(checkingGroupOfGoods.getGoods_catalog_link().equals(items.getGoods_catalog_link())){
                    errors.rejectValue("goods_catalog_link","goods_catalog_link.equals","Данна група товарів вже вибрана");
                }
            }
        }catch (Exception ex){

        }

    }
}
