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
    private static final String SQL_CHECK_USER="SELECT ut.id,ut.username,ut.email,ut.structure_link,ut.enabled,sct.name,rt.role_name FROM user_table ut " +
            "LEFT JOIN structure_catalog_table sct ON (ut.structure_link = sct.treemark) " +
            "LEFT JOIN role_table rt ON (ut.role = rt.id)" +
            "WHERE LOWER(ut.username) LIKE LOWER(:username)";

    public SelectUserListByUserNameLike(DataSource ds) {
        super(ds, SQL_CHECK_USER);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
    }

    @Override
    protected Object mapRow(ResultSet rs, int i) throws SQLException {

        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setEnabled(rs.getBoolean("enabled"));
        user.setEmail(rs.getString("email"));
        user.setStructure_link(rs.getString("name"));
        user.setRole_name(rs.getString("role_name"));
        return user;
    }
}
