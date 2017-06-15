package ua.pp.darknsoft.dao.crud.user;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Dark on 09.11.2016.
 */
public class SelectUserListByUserNameLike extends MappingSqlQuery{
    private static final String SQL_CHECK_USER="SELECT id,username,email,structure_link FROM user_table WHERE LOWER(username) LIKE LOWER(:username)";

    public SelectUserListByUserNameLike(DataSource ds) {
        super(ds, SQL_CHECK_USER);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
    }

    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {

        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEmail(rs.getString("email"));
        user.setStructure_link(rs.getString("structure_link"));
        return user;
    }
}
