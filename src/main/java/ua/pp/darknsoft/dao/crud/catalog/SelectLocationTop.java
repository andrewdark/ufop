package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.LocationCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 03.02.2017.
 */
public class SelectLocationTop extends MappingSqlQuery<LocationCatalog> {
    private static final String SQL_SELECT_LOCATION_CATALOG="SELECT * FROM location_table WHERE nlevel(treemark) <=1 ORDER BY id";

    public SelectLocationTop(DataSource ds){
        super(ds, SQL_SELECT_LOCATION_CATALOG);
    }

    @Override
    protected LocationCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        LocationCatalog locationCatalog = new LocationCatalog();
        locationCatalog.setId(resultSet.getInt("id"));
        locationCatalog.setLtree(resultSet.getString("treemark"));
        locationCatalog.setName(resultSet.getString("name"));
        locationCatalog.setType(resultSet.getInt("type"));
        locationCatalog.setNote(resultSet.getString("note"));

        return locationCatalog;
    }
}
