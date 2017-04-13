package ua.pp.darknsoft.validator;

/**
 * Created by Andrew on 13.01.2017.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.pp.darknsoft.dao.CommercialObjectDao;
import ua.pp.darknsoft.dao.UserDaoImpl;
import ua.pp.darknsoft.entity.GoodsOfCommObj;
import ua.pp.darknsoft.entity.User;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Dark on 18.11.2016.
 */
@Component
public class GoodsOfCommObjValidator implements Validator {
    @Autowired
    CommercialObjectDao commercialObjectDao;
    private final static Pattern EMAIL_PATTERN = Pattern.compile(".+@.+\\.[a-z]+");
    private final static Pattern LOGIN_PATTERN = Pattern.compile("[0-9a-zA-Z_@.'!$=?{}\\- ()]+");

    @Override
    public boolean supports(Class<?> aClass) {
        return GoodsOfCommObj.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        GoodsOfCommObj goodsOfCommObj = (GoodsOfCommObj) o;
        String str="";
        try {
            List<GoodsOfCommObj> goods_list =  commercialObjectDao.getCommObjGoodsByCommObjlink(goodsOfCommObj.getComm_obj_link());
            for (GoodsOfCommObj items : goods_list
                 ) {
                if(goodsOfCommObj.getGoods_catalog_link().equals(items.getGoods_catalog_link()))
                    errors.rejectValue("goods_catalog_link", "goods_catalog_link.full", "Група товарів вже існує"+str);

            }

        } catch (Exception ex) {
            str = ""+ex;
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "comm_obj_link", "comm_obj_link.empty", "comm_obj_link must not be empty.");

        if(goodsOfCommObj.getComm_obj_link()<=0){
            errors.rejectValue("comm_obj_link", "comm_obj_link.empty", "Вкажіть комерційний об'єкт");
        }
        if(goodsOfCommObj.getGoods_catalog_link().equals("")) errors.rejectValue("goods_catalog_link", "goods_catalog_link.empty", "Виберіть групу товарів");

    }


}