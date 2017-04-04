package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.LawsuitsResultCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 04.04.2017.
 */
public class SelectLawsuitsResultCatalog extends MappingSqlQuery<LawsuitsResultCatalog>{
private static final String SQL_SELECT_LAWSUITS_RESULT_CATALOG = "SELECT * FROM lawsuits_result_catalog_table ORDER BY id";
    public SelectLawsuitsResultCatalog(DataSource ds) {
        super(ds, SQL_SELECT_LAWSUITS_RESULT_CATALOG);
    }

    @Override
    protected LawsuitsResultCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        LawsuitsResultCatalog lawsuitsResultCatalog = new LawsuitsResultCatalog();
        lawsuitsResultCatalog.setId(resultSet.getInt("id"));
        lawsuitsResultCatalog.setName(resultSet.getString("name"));
        lawsuitsResultCatalog.setDescription(resultSet.getString("description"));
        return lawsuitsResultCatalog;
    }
}
