package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 26.03.2017.
 */
public class InsertCheckEventReturnId extends SqlUpdate {
    private static final String SQL_INSERT ="INSERT INTO check_event_table (ufop_link,event_date_begin,event_date_end,check_type,check_violation,event_result,check_sampling,result_sampling,creator_link,structure_catalog_link) " +
            "VALUES (:ufop_link,:event_date_begin::DATE ,:event_date_end::DATE,:check_type,:check_violation,:event_result,:check_sampling,:result_sampling,(SELECT id FROM user_table WHERE username = :creator_link),:structure_catalog_link)";

    public InsertCheckEventReturnId(DataSource ds) {
        super(ds, SQL_INSERT);
        super.declareParameter(new SqlParameter("ufop_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("event_date_begin",Types.VARCHAR));
        super.declareParameter(new SqlParameter("event_date_end",Types.VARCHAR));
        super.declareParameter(new SqlParameter("check_type",Types.INTEGER));
        super.declareParameter(new SqlParameter("check_violation",Types.INTEGER));
        super.declareParameter(new SqlParameter("event_result",Types.VARCHAR));
        super.declareParameter(new SqlParameter("check_sampling",Types.INTEGER));
        super.declareParameter(new SqlParameter("result_sampling",Types.INTEGER));
        super.declareParameter(new SqlParameter("creator_link",Types.VARCHAR));
        super.declareParameter(new SqlParameter("structure_catalog_link",Types.INTEGER));
        super.setGeneratedKeysColumnNames(new String[] {"id"});
        super.setReturnGeneratedKeys(true);
    }
}
