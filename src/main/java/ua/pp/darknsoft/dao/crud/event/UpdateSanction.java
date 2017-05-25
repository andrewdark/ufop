package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 25.05.2017.
 */
public class UpdateSanction extends SqlUpdate{
    private static final String SQL_UPDATE = "UPDATE sanctions_table SET service_date = :service_date," +
            "plan_date = :plan_date, fact_date = :fact_date WHERE id = :id";

    public UpdateSanction(DataSource ds) {
        super(ds, SQL_UPDATE);
        super.declareParameter(new SqlParameter("id",Types.BIGINT));
        super.declareParameter(new SqlParameter("service_date", Types.DATE));
        super.declareParameter(new SqlParameter("plan_date", Types.DATE));
        super.declareParameter(new SqlParameter("fact_date", Types.DATE));
        super.compile();
    }
}
