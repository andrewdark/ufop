package ua.pp.darknsoft.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.KvedDao;
import ua.pp.darknsoft.dao.SchedullerDao;
import ua.pp.darknsoft.dao.SchedullerDaoImpl;
import ua.pp.darknsoft.dao.crud.scheduller.KickOutUserInsert;
import ua.pp.darknsoft.entity.KvedsUfop;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew on 11.05.2017.
 */
@Service
public class Scheduller {

    private KickOutUserInsert kickOutUserInsert;
    private DataSource dataSearch;

    @Resource(name = "dataSource")
    public void setDataSearch(DataSource dataSearch) {
        this.dataSearch = dataSearch;
        this.kickOutUserInsert = new KickOutUserInsert(dataSearch);
    }
    @Scheduled(cron = "0 0 21 * * ?")
    public void addKickOutUserInsert() {
        Map<String, String> bind = new HashMap<>();
        bind.put("log_text", "SCHEDULER" + new Date());
        try{
            kickOutUserInsert.updateByNamedParam(bind);
        }catch (Exception ex){
            System.out.println("ERROR " + ex);
       }

    }

//    @Scheduled(cron = "0 0/35 11 * * ?")
//    public void sched(){
//        try{
//            //schedullerDao.addKickOutUserInsert("SCHEDULLER");
//            System.out.println("XOXO! Now is:" + new Date());
//        }catch (Exception ex){
//            System.out.println("ERROR " + ex);
//        }
//
//
//    }
}
