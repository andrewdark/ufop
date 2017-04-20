package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 20.04.2017.
 */
public class UpdateCheckEventReturnID extends SqlUpdate {
    private static final String SQL_UPDATE = "UPDATE check_event_table SET " +
            " event_number =:event_number,event_date_begin =:event_date_begin " +
            "WHERE id=:id";

    public UpdateCheckEventReturnID(DataSource ds) {
        super(ds, SQL_UPDATE);
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
        super.declareParameter(new SqlParameter("event_number", Types.VARCHAR));
        super.declareParameter(new SqlParameter("event_date_begin", Types.DATE));
        super.declareParameter(new SqlParameter("event_date_end", Types.DATE));
        super.declareParameter(new SqlParameter("check_type", Types.INTEGER));
        super.declareParameter(new SqlParameter("is_checking", Types.BOOLEAN));
        super.declareParameter(new SqlParameter("check_violation", Types.INTEGER));
        super.declareParameter(new SqlParameter("event_result", Types.VARCHAR));
        super.declareParameter(new SqlParameter("check_sampling", Types.INTEGER));
        super.declareParameter(new SqlParameter("result_sampling", Types.INTEGER));
        super.declareParameter(new SqlParameter("creator_link", Types.INTEGER));
        super.declareParameter(new SqlParameter("structure_catalog_link", Types.INTEGER));
        super.setGeneratedKeysColumnNames(new String[] {"id"});
        super.setReturnGeneratedKeys(true);
    }
}
