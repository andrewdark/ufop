package ua.pp.darknsoft.dao.crud.worktime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.WorkTime;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew on 02.03.2017.
 */
public class SelectWorkTimeByUser_NameASC extends MappingSqlQuery<WorkTime> {
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private static final String SQL_SELECT_WorkTimeByUser_link = "SELECT wtt.*,cct.name s_cause_link FROM work_time_table wtt " +
            "INNER JOIN cause_catalog_table cct ON(cct.id=wtt.cause_link) " +
            "WHERE wtt.user_link = (SELECT ut.id FROM user_table ut WHERE LOWER(ut.username) = LOWER(:user_link))" +
            "ORDER BY datereg ASC LIMIT :limit" ;

    public SelectWorkTimeByUser_NameASC(DataSource ds) {
        super(ds, SQL_SELECT_WorkTimeByUser_link);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(ds);
        super.declareParameter(new SqlParameter("user_link", Types.VARCHAR));
        super.declareParameter(new SqlParameter("limit", Types.INTEGER));
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
        workTime.setS_user_accepted_link(getUserNameById(resultSet.getInt("user_accepted_link")));
        workTime.setDateaccept(resultSet.getTimestamp("dateaccept"));
        return workTime;
    }

    private String getUserNameById(int id) {
        String sql = "SELECT username FROM user_table WHERE id=:id";
        String result=null;
        Map<String, Integer> bind = new HashMap<>();
        bind.put("id", id);
try{
    result = (String) namedParameterJdbcTemplate.queryForObject(sql, bind, String.class);
}catch(Exception ex){
    result = null;
}
        return result;
    }
}
