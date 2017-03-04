package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.WorkTime;

import java.util.List;

/**
 * Created by Andrew on 27.02.2017.
 */
public interface WorkTimeDao {
    void setMyWorkWorkTime(String user_link, int cause_link);

    void acceptWorkTime(WorkTime workTime);

    List<WorkTime> getMyWorkWorkTimeASC(String user_link, Integer limit);

    List<WorkTime> getMyWorkWorkTimeDESC(String user_link, Integer limit);

    List<WorkTime> getMySlavesWorkTimeDesc(String user_link, String datereg, Integer limit);
}
