package ua.pp.darknsoft.entity;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Dark on 26.03.2017.
 */
public class CheckEventSupplemented extends CheckEvent{
    private String current_state;

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
