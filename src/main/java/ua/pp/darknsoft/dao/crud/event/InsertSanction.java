package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 23.05.2017.
 */
public class InsertSanction extends SqlUpdate {
    private static final String SQL_INSERT = "INSERT INTO sanctions_table(check_event_link,articles_law_link,sanction_number,charged_amount,service_date,plan_date,fact_date,creator_link) " +
            "VALUES(:check_event_link,:articles_law_link,:sanction_number,:charged_amount,:service_date,:plan_date,:fact_date,(SELECT id FROM user_table WHERE username = :creator_link))";
    public InsertSanction(DataSource ds) {
        super(ds, SQL_INSERT);
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("articles_law_link", Types.VARCHAR));
        super.declareParameter(new SqlParameter("sanction_number", Types.VARCHAR));
        super.declareParameter(new SqlParameter("charged_amount", Types.DECIMAL));
        super.declareParameter(new SqlParameter("service_date", Types.DATE));
        super.declareParameter(new SqlParameter("plan_date", Types.DATE));
        super.declareParameter(new SqlParameter("fact_date", Types.DATE));
        super.declareParameter(new SqlParameter("creator_link", Types.VARCHAR));
        compile();
    }
}
