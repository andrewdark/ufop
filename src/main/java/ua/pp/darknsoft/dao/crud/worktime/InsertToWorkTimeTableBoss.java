package ua.pp.darknsoft.dao.crud.worktime;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 02.03.2017.
 */
public class InsertToWorkTimeTableBoss extends SqlUpdate {
    private final static String SQL_INSERT_INTO_WTT = "INSERT INTO work_time_table(user_link,cause_link,accepted,user_accepted_link,dateaccept) " +
            "VALUES (:user_link,:cause_link,true,(SELECT id FROM user_table WHERE LOWER(username) = LOWER(:s_user_accepted_link))," +
            "(now())::timestamp without time zone)";

    public InsertToWorkTimeTableBoss(DataSource dataSource) {
        super(dataSource,SQL_INSERT_INTO_WTT);
        super.declareParameter(new SqlOutParameter("user_link", Types.INTEGER));
        super.declareParameter(new SqlOutParameter("cause_link", Types.INTEGER));
        super.declareParameter(new SqlOutParameter("s_user_accepted_link", Types.VARCHAR));
        compile();
    }
}
