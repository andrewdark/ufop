package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.DegreeRisk;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 12.04.2017.
 */
public class SelectDegreeRiskCatalog extends MappingSqlQuery<DegreeRisk>{
    private static final String SELECT_DEGREERISK = "SELECT * FROM degree_risk_catalog_table ORDER BY id";

    public SelectDegreeRiskCatalog(DataSource ds) {
        super(ds, SELECT_DEGREERISK);
    }

    @Override
    protected DegreeRisk mapRow(ResultSet resultSet, int i) throws SQLException {
        DegreeRisk degreeRisk = new DegreeRisk();
        degreeRisk.setId(resultSet.getInt("id"));
        degreeRisk.setTitle(resultSet.getString("title"));
        return degreeRisk;
    }
}
