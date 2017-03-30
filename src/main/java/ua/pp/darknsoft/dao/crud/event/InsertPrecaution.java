package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 30.03.2017.
 */
public class InsertPrecaution extends SqlUpdate{
    private static final String SQL_INSERT_PRECAUTION = "INSERT INTO precaution_table" +
            "(check_event_link,precaution_catalog_link,service_date,plan_date,fact_date) " +
            "VALUES(:check_event_link,:precaution_catalog_link,:service_date::DATE ,:plan_date::DATE,:fact_date::DATE)";

    public InsertPrecaution(DataSource ds) {
        super(ds, SQL_INSERT_PRECAUTION);
        super.declareParameter(new SqlOutParameter("check_event_link", Types.BIGINT));
        super.declareParameter(new SqlOutParameter("precaution_catalog_link", Types.INTEGER));
        super.declareParameter(new SqlOutParameter("service_date",Types.DATE));
        super.declareParameter(new SqlOutParameter("plan_date",Types.DATE));
        super.declareParameter(new SqlOutParameter("fact_date",Types.DATE));
        compile();
    }
}
