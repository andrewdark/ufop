package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 26.03.2017.
 */
public class InsertCheckingCommObj extends SqlUpdate {
    private static final String SQL_INSERT ="INSERT INTO checking_comm_obj_table (check_event_link, comm_obj_link, checking) " +
            "VALUES (:check_event_link, :comm_obj_link, :checking)";

    public InsertCheckingCommObj(DataSource ds) {
        super(ds, SQL_INSERT);
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("comm_obj_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("checking", Types.BOOLEAN));
        compile();
    }
}
