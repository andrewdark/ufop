package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.LocationCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 03.02.2017.
 */
public class SelectParentLocationByTreemark extends MappingSqlQuery<LocationCatalog> {
    private static final String SQL_SELECT_LOCATION_CATALOG="SELECT l.*,t.type stype,nlevel(l.treemark) FROM location_table l INNER JOIN location_type_table t ON(t.id=l.type) WHERE l.treemark@>:treemark::ltree ORDER BY treemark";

    public SelectParentLocationByTreemark(DataSource ds){
        super(ds, SQL_SELECT_LOCATION_CATALOG);
        super.declareParameter(new SqlParameter("treemark", Types.VARCHAR));
    }

    @Override
    protected LocationCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        LocationCatalog locationCatalog = new LocationCatalog();
        locationCatalog.setId(resultSet.getInt("id"));
        locationCatalog.setLtree(resultSet.getString("treemark"));
        locationCatalog.setName(resultSet.getString("name"));
        locationCatalog.setType(resultSet.getInt("type"));
        locationCatalog.setStype(resultSet.getString("stype"));
        locationCatalog.setNote(resultSet.getString("note"));
        locationCatalog.setNlevel(resultSet.getInt("nlevel"));

        return locationCatalog;
    }

}