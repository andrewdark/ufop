package ua.pp.darknsoft.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by Dark on 26.03.2017.
 */
public class CheckEventSupplemented extends CheckEvent{
    private List<CheckingCommObj> commobj_list;
    private List<CheckingGroupOfGoods> groupofgoods_list;
    private List<OffenseArticles> offensearticles_list;
    private String current_state;

    public List<CheckingCommObj> getCommobj_list() {
        return commobj_list;
    }

    public void setCommobj_list(List<CheckingCommObj> commobj_list) {
        this.commobj_list = commobj_list;
    }

    public List<CheckingGroupOfGoods> getGroupofgoods_list() {
        return groupofgoods_list;
    }

    public void setGroupofgoods_list(List<CheckingGroupOfGoods> groupofgoods_list) {
        this.groupofgoods_list = groupofgoods_list;
    }

    public List<OffenseArticles> getOffensearticles_list() {
        return offensearticles_list;
    }

    public void setOffensearticles_list(List<OffenseArticles> offensearticles_list) {
        this.offensearticles_list = offensearticles_list;
    }

    public String getCurrent_state() {
        try{
            SimpleDateFormat parser = new SimpleDateFormat("yy-MM-dd");

            Date date_end = parser.parse(getEvent_date_end());
            Date date_begin = parser.parse(getEvent_date_begin());
            if(getCheck_sampling()==1 && date_begin.after(date_end))return "Перевірка проб";
            if(date_begin.before(date_end))return "Завершена";
            if (date_begin.after(date_end))return "В стадії перевірки";
        }catch (Exception ex){
            return "Error: "+ex;
        }


        return current_state;
    }

    public void setCurrent_state(String current_state) {
        this.current_state = current_state;
    }


}
