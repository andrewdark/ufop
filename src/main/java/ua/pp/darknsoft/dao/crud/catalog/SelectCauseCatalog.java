package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.CauseCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 02.03.2017.
 */
public class SelectCauseCatalog extends MappingSqlQuery<CauseCatalog>{
    private static final String SQL_SELECT_CAUSE = "SELECT * FROM cause_catalog_table WHERE type = :type";

    public SelectCauseCatalog(DataSource dataSource) {
        super(dataSource,SQL_SELECT_CAUSE);
        super.declareParameter(new SqlParameter("type", Types.SMALLINT));
    }

    @Override
    protected CauseCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        CauseCatalog cc = new CauseCatalog();
        cc.setId(resultSet.getInt("id"));
        cc.setName(resultSet.getString("name"));
        return cc;
    }
}
