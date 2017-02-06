package ua.pp.darknsoft.dao.crud.user;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dark on 09.11.2016.
 */
public class SelectUser extends MappingSqlQuery{
    private static final String SQL_CHECK_USER="SELECT id,username,pwd,email FROM user_table WHERE username=:username";

    public SelectUser(DataSource ds) {
        super(ds, SQL_CHECK_USER);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
    }

    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPwd(rs.getString("pwd"));
        user.setEmail(rs.getString("email"));
        return user;
    }
}
