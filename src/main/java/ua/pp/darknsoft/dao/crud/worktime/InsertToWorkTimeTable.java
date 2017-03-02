package ua.pp.darknsoft.dao.crud.worktime;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 02.03.2017.
 */
public class InsertToWorkTimeTable extends SqlUpdate {
    private final static String SQL_INSERT_INTO_WTT = "INSERT INTO work_time_table(user_link,cause_link) " +
            "VALUES ((SELECT id FROM user_table WHERE LOWER(username) = LOWER(:user_link)),:cause_link)";

    public InsertToWorkTimeTable(DataSource dataSource) {
        super(dataSource,SQL_INSERT_INTO_WTT);
        super.declareParameter(new SqlOutParameter("user_link", Types.VARCHAR));
        super.declareParameter(new SqlOutParameter("cause_link", Types.INTEGER));
        compile();
    }
}
