package ua.pp.darknsoft.dao.crud.kved;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.KvedCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 16.02.2017.
 */
public class SelectKvedTop extends MappingSqlQuery<KvedCatalog> {
    private static final String SQL_SELECT_KVED_CATALOG="SELECT * FROM kved_catalog_table WHERE nlevel(treemark) <=1 ORDER BY id";

    public SelectKvedTop(DataSource ds){
        super(ds, SQL_SELECT_KVED_CATALOG);
    }
    @Override
    protected KvedCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
