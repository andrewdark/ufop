package ua.pp.darknsoft.dao.crud.worktime;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 02.03.2017.
 */
public class UpdateWorkTimeTableAccept extends SqlUpdate{
    private static final String SQL_UPDATE_WorkTimeTable = "UPDATE work_time_table SET accepted = :accepted,user_accepted_link = :user_accepted_link WHERE id = :id";

    public UpdateWorkTimeTableAccept(DataSource dataSource) {
        super(dataSource,SQL_UPDATE_WorkTimeTable);
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
        super.declareParameter(new SqlParameter("accepted", Types.BOOLEAN));
        super.declareParameter(new SqlParameter("user_accepted_link", Types.INTEGER));
        compile();
    }
}
