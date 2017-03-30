package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 30.03.2017.
 */
public class InsertSanction extends SqlUpdate{
    private static final String SQL_INSERT_SANCTION="INSERT INTO sanctions_table (check_event_link, charged_amount, service_date, plan_date, fact_date) " +
            "VALUES(:check_event_link, :charged_amount, :service_date::DATE , :plan_date::DATE, :fact_date::DATE)";

    public InsertSanction(DataSource ds) {
        super(ds, SQL_INSERT_SANCTION);
        super.declareParameter(new SqlOutParameter("check_event_link", Types.BIGINT));
        super.declareParameter(new SqlOutParameter("charged_amount", Types.VARCHAR));
        super.declareParameter(new SqlOutParameter("service_date",Types.VARCHAR));
        super.declareParameter(new SqlOutParameter("plan_date",Types.VARCHAR));
        super.declareParameter(new SqlOutParameter("fact_date",Types.VARCHAR));

    }
}
