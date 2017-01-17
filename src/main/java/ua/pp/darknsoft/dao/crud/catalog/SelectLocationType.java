package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.LocationType;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 17.01.2017.
 */

public class SelectLocationType extends MappingSqlQuery<LocationType> {
    private static final String SQL_SELECT_LOCATION_TYPE="SELECT * FROM location_type_table ORDER BY id";

    public SelectLocationType(DataSource ds) {
        super(ds, SQL_SELECT_LOCATION_TYPE);
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
