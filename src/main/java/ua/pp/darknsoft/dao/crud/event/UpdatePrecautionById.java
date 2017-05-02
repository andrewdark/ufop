package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 02.05.2017.
 */
public class UpdatePrecautionById extends SqlUpdate{
private static final String SQL_UPDATE = "UPDATE precaution_table SET service_date =:service_date,plan_date=:plan_date,fact_date =:fact_date " +
        "WHERE id = :id";
    public UpdatePrecautionById(DataSource ds) {
        super(ds, SQL_UPDATE);
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
        super.declareParameter(new SqlParameter("service_date", Types.DATE));
        super.declareParameter(new SqlParameter("plan_date", Types.DATE));
        super.declareParameter(new SqlParameter("fact_date", Types.DATE));
        compile();
    }
}
