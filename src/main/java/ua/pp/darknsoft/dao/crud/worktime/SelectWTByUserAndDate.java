package ua.pp.darknsoft.dao.crud.worktime;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.WorkTime;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 19.05.2017.
 */
public class SelectWTByUserAndDate extends MappingSqlQuery<WorkTime> {
    private static final String SQL_SELECT = "SELECT wtt.*,cct.name s_cause_link FROM work_time_table wtt " +
            "INNER JOIN cause_catalog_table cct ON(cct.id=wtt.cause_link) " +
            "WHERE wtt.user_link = :user_link AND wtt.datereg BETWEEN :datestart AND :datestop";
    public SelectWTByUserAndDate(DataSource ds) {
        super(ds, SQL_SELECT);
        super.declareParameter(new SqlParameter("user_link", Types.INTEGER));
        super.declareParameter(new SqlParameter("datestart",Types.DATE));
        super.declareParameter(new SqlParameter("datestop",Types.DATE));
    }

    @Override
    protected WorkTime mapRow(ResultSet resultSet, int i) throws SQLException {
        WorkTime workTime = new WorkTime();
        workTime.setId(resultSet.getLong("id"));
        workTime.setUser_link(resultSet.getInt("user_link"));
        workTime.setType_of_action(resultSet.getShort("type_of_action"));
        workTime.setCause_link(resultSet.getInt("cause_link"));
        workTime.setS_cause_link(resultSet.getString("s_cause_link"));
        workTime.setDatereg(resultSet.getTimestamp("datereg"));
        workTime.setAccepted(resultSet.getBoolean("accepted"));
        workTime.setUser_accepted_link(resultSet.getInt("user_accepted_link"));
        workTime.setDateaccept(resultSet.getTimestamp("dateaccept"));
        return workTime;
    }
}
