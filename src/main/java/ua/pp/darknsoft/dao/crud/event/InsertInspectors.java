package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 29.05.2017.
 */
public class InsertInspectors extends SqlUpdate{
    private static final String SQL_INCERT = "INSERT INTO inspectors_table " +
            "(check_event_link, user_link, structure_link, creator_link) " +
            "VALUES (:check_event_link, :user_link, (SELECT structure_link FROM user_table WHERE id = :user_link), (SELECT id FROM user_table WHERE username = :creator_name))";

    public InsertInspectors(DataSource ds) {
        super(ds, SQL_INCERT);
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("user_link", Types.INTEGER));
        super.declareParameter(new SqlParameter("creator_name", Types.VARCHAR));
        super.compile();
    }
}
