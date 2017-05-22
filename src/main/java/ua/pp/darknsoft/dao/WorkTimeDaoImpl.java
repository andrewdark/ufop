package ua.pp.darknsoft.dao;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.worktime.*;
import ua.pp.darknsoft.entity.WorkTime;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 27.02.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WorkTimeDaoImpl implements WorkTimeDao, Serializable {
    private DataSource dataSource;
    private InsertToWorkTimeTable insertToWorkTimeTable;
    private InsertToWorkTimeTableBoss insertToWorkTimeTableBoss;
    private UpdateWorkTimeTableAccept updateWorkTimeTableAccept;
    private SelectWorkTimeByUser_NameDESC selectWorkTimeByUser_nameDESC;
    private SelectWorkTimeByUser_NameASC selectWorkTimeByUser_nameASC;
    private SelectWorkTimeMySlaveDESC selectWorkTimeMySlaveDESC;
    private SelectWTByUserAndDate selectWTByUserAndDate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertToWorkTimeTable = new InsertToWorkTimeTable(dataSource);
        this.insertToWorkTimeTableBoss = new InsertToWorkTimeTableBoss(dataSource);
        this.updateWorkTimeTableAccept = new UpdateWorkTimeTableAccept(dataSource);
        this.selectWorkTimeByUser_nameASC = new SelectWorkTimeByUser_NameASC(dataSource);
        this.selectWorkTimeByUser_nameDESC = new SelectWorkTimeByUser_NameDESC(dataSource);
        this.selectWorkTimeMySlaveDESC = new SelectWorkTimeMySlaveDESC(dataSource);
        this.selectWTByUserAndDate = new SelectWTByUserAndDate(dataSource);
    }

    @Override
    public void setMyWorkWorkTime(String user_link, int cause_link) {
        Map<String, Object> bind = new HashedMap();
        bind.put("user_link", user_link);
        bind.put("cause_link", cause_link);

        insertToWorkTimeTable.updateByNamedParam(bind);
    }

    @Override
    public void acceptWorkTime(WorkTime workTime) {
        Map<String, Object> bind = new HashedMap();
        bind.put("id", workTime.getId());
        bind.put("accepted", workTime.isAccepted());
        bind.put("user_accepted_link", workTime.getUser_accepted_link());

        updateWorkTimeTableAccept.updateByNamedParam(bind);
    }

    @Override
    public List<WorkTime> getMyWorkWorkTimeASC(String user_link, Integer limit) {
        Map<String, Object> bind = new HashedMap();
        bind.put("user_link", user_link);
        bind.put("limit", limit);

        return selectWorkTimeByUser_nameASC.executeByNamedParam(bind);
    }

    @Override
    public List<WorkTime> getMyWorkWorkTimeDESC(String user_link, Integer limit) {
        Map<String, Object> bind = new HashedMap();
        bind.put("user_link", user_link);
        bind.put("limit", limit);

        return selectWorkTimeByUser_nameDESC.executeByNamedParam(bind);
    }

    @Override
    public List<WorkTime> getMySlavesWorkTimeDesc(String user_link, String datereg_f, String datereg_l, Integer limit) {
        Map<String, Object> bind = new HashedMap();
        bind.put("user_link", user_link);
        bind.put("datereg_f", datereg_f);
        bind.put("datereg_l", datereg_l);
        bind.put("limit", limit);

        return selectWorkTimeMySlaveDESC.executeByNamedParam(bind);
    }

    @Override
    public List<WorkTime> getWTUserDetail(int user_link, String datestart, String datestop) {
        Map<String, Object> bind = new HashedMap();
        bind.put("user_link", user_link);
        bind.put("datestart", datestart);
        bind.put("datestop", datestop);
        return selectWTByUserAndDate.executeByNamedParam(bind);
    }

    @Override
    public void setWorkTimeUserByBoss(WorkTime workTime) {
        Map<String, Object> bind = new HashedMap();
        bind.put("user_link", workTime.getUser_link());
        bind.put("cause_link", workTime.getCause_link());
        bind.put("s_user_accepted_link", workTime.getS_user_accepted_link());
        insertToWorkTimeTableBoss.updateByNamedParam(bind);

    }
}
