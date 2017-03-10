package ua.pp.darknsoft.dao.crud.user;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 10.03.2017.
 */
public class SelectUserByStructure_link extends MappingSqlQuery<User>{
    private static final String SQL_SELECT_USERS = "SELECT * FROM user_table WHERE structure_link = (:structure_link)::LTREE";

    public SelectUserByStructure_link(DataSource ds) {
        super(ds, SQL_SELECT_USERS);
        super.declareParameter(new SqlParameter("structure_link", Types.VARCHAR));
    }

    @Override
    protected User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setContact_link(resultSet.getLong("contact_link"));
        user.setStructure_link(resultSet.getString("structure_link"));
        return null;
    }
}
