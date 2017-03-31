package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 31.03.2017.
 */
public class InsertLawsuits extends SqlUpdate{
    private static final String SQL_INSERT_LAWSUIT= "INSERT INTO lawsuits_table (check_event_link, filed_on_action, filed_date, result_link, description) " +
            "VALUES (:check_event_link, :filed_on_action, :filed_date::DATE , :result_link, :description)";
    public InsertLawsuits(DataSource dataSource){
        super(dataSource,SQL_INSERT_LAWSUIT);
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("filed_on_action", Types.INTEGER));
        super.declareParameter(new SqlParameter("filed_date", Types.VARCHAR));
        super.declareParameter(new SqlParameter("result_link", Types.INTEGER));
        super.declareParameter(new SqlParameter("description", Types.VARCHAR));
        compile();
    }
}
