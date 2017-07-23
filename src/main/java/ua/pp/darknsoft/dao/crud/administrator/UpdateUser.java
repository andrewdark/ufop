package ua.pp.darknsoft.dao.crud.administrator;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 09.11.2016.
 */
public class UpdateUser extends SqlUpdate{

    private final static String SQL_UPDATE_USER="UPDATE user_table SET enabled=:enabled, structure_link=:structure_link::ltree, " +
            "role=:role, email=:email WHERE id=:id";

    public UpdateUser(DataSource dataSource){
        super(dataSource,SQL_UPDATE_USER);

        super.declareParameter(new SqlParameter("id", Types.INTEGER));
        super.declareParameter(new SqlParameter("structure_link", Types.VARCHAR));
        super.declareParameter(new SqlParameter("role", Types.INTEGER));
        super.declareParameter(new SqlParameter("email", Types.VARCHAR));
        super.declareParameter(new SqlParameter("enabled", Types.BOOLEAN));

    }

}
