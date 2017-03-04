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
@Scope(value="session",proxyMode= ScopedProxyMode.TARGET_CLASS)
public class WorkTimeDaoImpl implements WorkTimeDao, Serializable {
    private DataSource dataSource;
    private InsertToWorkTimeTable insertToWorkTimeTable;
    private UpdateWorkTimeTableAccept updateWorkTimeTableAccept;
    private SelectWorkTimeByUser_linkDESC selectWorkTimeByUser_linkDESC;
    private SelectWorkTimeByUser_linkASC selectWorkTimeByUser_linkASC;
    private SelectWorkTimeMySlaveDESC selectWorkTimeMySlaveDESC;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertToWorkTimeTable = new InsertToWorkTimeTable(dataSource);
        this.updateWorkTimeTableAccept = new UpdateWorkTimeTableAccept(dataSource);
        this.selectWorkTimeByUser_linkASC = new SelectWorkTimeByUser_linkASC(dataSource);
        this.selectWorkTimeByUser_linkDESC = new SelectWorkTimeByUser_linkDESC(dataSource);
        this.selectWorkTimeMySlaveDESC = new SelectWorkTimeMySlaveDESC(dataSource);
    }
    @Override
    public void setMyWorkWorkTime(String user_link, int cause_link){
        Map<String,Object> bind = new HashedMap();
        bind.put("user_link",user_link);
        bind.put("cause_link",cause_link);

        insertToWorkTimeTable.updateByNamedParam(bind);
    }
    @Override
    public void acceptWorkTime(WorkTime workTime){
        Map<String,Object> bind = new HashedMap();
        bind.put("id",workTime.getId());
        bind.put("accepted",workTime.isAccepted());
        bind.put("user_accepted_link",workTime.getUser_accepted_link());

        updateWorkTimeTableAccept.updateByNamedParam(bind);
    }
    @Override
    public List<WorkTime> getMyWorkWorkTimeASC(String user_link, Integer limit){
        Map<String,Object> bind = new HashedMap();
        bind.put("user_link",user_link);
        bind.put("limit",limit);

        return selectWorkTimeByUser_linkASC.executeByNamedParam(bind);
    }
    @Override
    public List<WorkTime> getMyWorkWorkTimeDESC(String user_link, Integer limit){
        Map<String,Object> bind = new HashedMap();
        bind.put("user_link",user_link);
        bind.put("limit",limit);

        return selectWorkTimeByUser_linkDESC.executeByNamedParam(bind);
    }
    @Override
    public List<WorkTime> getMySlavesWorkTimeDesc(String user_link, String datereg, Integer limit){
        Map<String,Object> bind = new HashedMap();
        bind.put("user_link",user_link);
        bind.put("datereg",datereg);
        bind.put("limit",limit);

        return selectWorkTimeMySlaveDESC.executeByNamedParam(bind);
    }

}
