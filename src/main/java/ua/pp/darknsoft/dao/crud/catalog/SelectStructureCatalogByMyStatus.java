package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.StructureCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 10.03.2017.
 */
public class SelectStructureCatalogByMyStatus extends MappingSqlQuery<StructureCatalog>{
    private static final String SQL_SELECT_STRUCTURE_CATALOG = "SELECT * FROM structure_catalog_table WHERE treemark <@ (SELECT ut.structure_link FROM user_table ut WHERE LOWER(ut.username) = LOWER(:user_link))";

    public SelectStructureCatalogByMyStatus(DataSource ds) {
        super(ds, SQL_SELECT_STRUCTURE_CATALOG);
        super.declareParameter(new SqlParameter("user_link", Types.VARCHAR));
    }

    @Override
    protected StructureCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        StructureCatalog sc = new StructureCatalog();
        sc.setId(resultSet.getInt("id"));
        sc.setTreemark(resultSet.getString("treemark"));
        sc.setName(resultSet.getString("name"));
        return sc;
    }
}
