package ua.pp.darknsoft.dao.crud.user;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 03.12.2016.
 */
public class DeleteUser extends SqlUpdate{
    private static final String SQL_DELETE="DELETE FROM user_table WHERE username=:username";

    public DeleteUser(DataSource dataSource){
        super(dataSource,SQL_DELETE);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
        compile();
    }
}
