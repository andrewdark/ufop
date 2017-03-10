package ua.pp.darknsoft.dao.crud.kved;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 16.02.2017.
 */
public class InsertKvedsUfop extends SqlUpdate{
    private final static String SQL_INSERT_KVED="INSERT INTO kveds_ufop_table(ufop_link,kved_catalog_link,creator_link)" +
            " VALUES (:ufop_link,:kved_catalog_link,(SELECT id FROM user_table WHERE LOWER(username) =LOWER(:creator_link)))";

    public InsertKvedsUfop(DataSource dataSource){
        super(dataSource,SQL_INSERT_KVED);
        super.declareParameter(new SqlParameter("ufop_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("kved_catalog_link", Types.VARCHAR));
        super.declareParameter(new SqlParameter("creator_link", Types.VARCHAR));
        compile();

    }
}
