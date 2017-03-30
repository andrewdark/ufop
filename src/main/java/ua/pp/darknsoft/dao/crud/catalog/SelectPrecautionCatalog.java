package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.PrecautionCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 30.03.2017.
 */
public class SelectPrecautionCatalog extends MappingSqlQuery<PrecautionCatalog> {
    private static final String SQL_SELECT_PRECAUTION_CATALOG = "SELECT * FROM precaution_catalog_table";

    public SelectPrecautionCatalog(DataSource ds) {
        super(ds, SQL_SELECT_PRECAUTION_CATALOG);
    }

    @Override
    protected PrecautionCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        PrecautionCatalog precautionCatalog = new PrecautionCatalog();
        precautionCatalog.setId(resultSet.getInt("id"));
        precautionCatalog.setName(resultSet.getString("name"));
        precautionCatalog.setDescription(resultSet.getString("description"));
        return precautionCatalog;

    }
}
