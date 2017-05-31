package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 29.05.2017.
 */
public class SelectUsersBySelectorStructureLink extends MappingSqlQuery<User>{
    private static final String SQL_SELECT_USERS = "SELECT ut.id, ct.last_name, ct.first_name FROM user_table ut " +
            "LEFT JOIN contact_table ct ON (ut.contact_link = ct.id) " +
            "WHERE ut.structure_link <@ (SELECT structure_link FROM user_table WHERE username = :username)::ltree";

    public SelectUsersBySelectorStructureLink(DataSource ds) {
        super(ds, SQL_SELECT_USERS);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
    }

    @Override
    protected User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("last_name")+" "+resultSet.getString("first_name"));
        return user;
    }
}
