package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.CheckingCommObj;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 20.04.2017.
 */
public class SelectCheckingCommercialObject extends MappingSqlQuery<CheckingCommObj> {
    private static final String SQL_SELECT="SELECT t1.*,t2.obj_name FROM checking_comm_obj_table t1 " +
            "INNER JOIN comm_object_table t2 ON (t1.comm_obj_link = t2.id) " +
            "WHERE t1.check_event_link = :check_event_link";

    public SelectCheckingCommercialObject(DataSource ds) {
        super(ds, SQL_SELECT);
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
    }

    @Override
    protected CheckingCommObj mapRow(ResultSet resultSet, int i) throws SQLException {
        CheckingCommObj checkingCommObj = new CheckingCommObj();
        checkingCommObj.setId(resultSet.getLong("id"));
        checkingCommObj.setCheck_event_link(resultSet.getLong("check_event_link"));
        checkingCommObj.setComm_obj_link(resultSet.getLong("comm_obj_link"));
        checkingCommObj.setChecking(resultSet.getBoolean("checking"));
        checkingCommObj.setComm_obj_name(resultSet.getString("obj_name"));

        return checkingCommObj;
    }
}
