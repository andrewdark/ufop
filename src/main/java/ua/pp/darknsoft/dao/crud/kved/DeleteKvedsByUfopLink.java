package ua.pp.darknsoft.dao.crud.kved;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 04.04.2017.
 */
public class DeleteKvedsByUfopLink extends SqlUpdate {
    private static final String SQL_DELETE = "DELETE FROM kveds_ufop_table WHERE id=:id";

    public DeleteKvedsByUfopLink(DataSource ds) {
        super(ds, SQL_DELETE );
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
        compile();
    }
}
