package ua.pp.darknsoft.dao.crud.user;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 09.11.2016.
 */
public class InsertUser extends SqlUpdate{

    private final static String SQL_INSERT_USER="INSERT INTO user_table(username,pwd,email) VALUES (:username,:pwd,:useremail)";

    public InsertUser(DataSource dataSource){
        super(dataSource,SQL_INSERT_USER);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
        super.declareParameter(new SqlParameter("pwd", Types.VARCHAR));
        super.declareParameter(new SqlParameter("useremail", Types.VARCHAR));

    }

}
