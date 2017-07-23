package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.LocationType;
import ua.pp.darknsoft.entity.Role;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 17.01.2017.
 */

public class SelectAllRole extends MappingSqlQuery<Role> {
    private static final String SQL_SELECT_LOCATION_TYPE="SELECT * FROM role_table ORDER BY id";

    public SelectAllRole(DataSource ds) {
        super(ds, SQL_SELECT_LOCATION_TYPE);
    }

    @Override
    protected Role mapRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setId(resultSet.getInt("id"));
        role.setRole_name(resultSet.getString("role_name"));
        return role;
    }
}
