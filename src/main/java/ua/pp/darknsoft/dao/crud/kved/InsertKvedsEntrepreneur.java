package ua.pp.darknsoft.dao.crud.kved;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 16.02.2017.
 */
public class InsertKvedsEntrepreneur extends SqlUpdate{
    private final static String SQL_INSERT_KVED="INSERT INTO kveds_entrepreneur_table(entrepreneur_link,kved_catalog_link,owner)" +
            " VALUES (:entrepreneur_link,:kved_catalog_link,(SELECT id FROM user_table WHERE username =:owner))";

    public InsertKvedsEntrepreneur(DataSource dataSource){
        super(dataSource,SQL_INSERT_KVED);
        super.declareParameter(new SqlParameter("entrepreneur_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("kved_catalog_link", Types.VARCHAR));
        super.declareParameter(new SqlParameter("owner", Types.VARCHAR));
        compile();

    }
}
