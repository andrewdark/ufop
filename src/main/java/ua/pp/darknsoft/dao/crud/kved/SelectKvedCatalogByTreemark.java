package ua.pp.darknsoft.dao.crud.kved;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.KvedCatalog;
import ua.pp.darknsoft.entity.LocationCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 16.02.2017.
 */
public class SelectKvedCatalogByTreemark extends MappingSqlQuery<KvedCatalog> {
    private static final String SQL_SELECT_LOCATION_CATALOG="SELECT *,nlevel(treemark) FROM kved_catalog_table WHERE treemark<@:treemark::ltree AND nlevel(treemark) = :nlevel ORDER BY id";

    public SelectKvedCatalogByTreemark(DataSource ds){
        super(ds, SQL_SELECT_LOCATION_CATALOG);
        super.declareParameter(new SqlParameter("treemark", Types.VARCHAR));
        super.declareParameter(new SqlParameter("nlevel", Types.INTEGER));
    }

    @Override
    protected KvedCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        KvedCatalog kvedCatalog = new KvedCatalog();
        kvedCatalog.setId(resultSet.getInt("id"));
        kvedCatalog.setTreemark(resultSet.getString("treemark"));
        kvedCatalog.setName(resultSet.getString("name"));
        kvedCatalog.setLabel(resultSet.getString("label"));
        kvedCatalog.setDescription(resultSet.getString("description"));
        kvedCatalog.setNlevel(resultSet.getInt("nlevel"));

        return kvedCatalog;
    }
}
