package ua.pp.darknsoft.dao.crud.scheduller;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 11.05.2017.
 */
public class KickOutUserInsert extends SqlUpdate {
    private static final String SQL_INSERT = "INSERT INTO log_end_of_the_day_table (log_text) VALUES (:log_text)";

    public KickOutUserInsert(DataSource ds){
        super(ds,SQL_INSERT);
        super.declareParameter(new SqlParameter("log_text", Types.VARCHAR));
        compile();
    }
}
