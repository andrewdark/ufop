package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.LocationType;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 23.02.2017.
 */
public class SelectLocationTypeById extends MappingSqlQuery<LocationType> {
    private static final String SQL_SELECT_LOCATION_TYPE = "SELECT * FROM location_type_table WHERE id=:id";

    public SelectLocationTypeById(DataSource dataSource) {
        super(dataSource,SQL_SELECT_LOCATION_TYPE);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }

    @Override
    protected LocationType mapRow(ResultSet resultSet, int i) throws SQLException {
        LocationType locationType = new LocationType();
        locationType.setId(resultSet.getInt("id"));
        locationType.setType(resultSet.getString("type"));
        locationType.setNote(resultSet.getString("note"));
        locationType.setLevel(resultSet.getShort("level"));
        return locationType;
    }
}
