package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 02.05.2017.
 */
public class DeletePrecautionById extends SqlUpdate{
    private static final String SQL_DELETE = "DELETE FROM precaution_table WHERE id=:id";
    public DeletePrecautionById(DataSource ds) {
        super(ds, SQL_DELETE );
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
        compile();
    }
}
