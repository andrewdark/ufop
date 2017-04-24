package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 24.04.2017.
 */
public class UpdateCheckingCommObj extends SqlUpdate {
    private static final String SQL_UPDATE = "UPDATE checking_comm_obj_table SET checking = :checking " +
            "WHERE id = :id";

    public UpdateCheckingCommObj(DataSource ds) {
        super(ds, SQL_UPDATE);
        super.declareParameter(new SqlParameter("id",Types.BIGINT));
        super.declareParameter(new SqlParameter("checking", Types.BOOLEAN));
        compile();
    }
}
