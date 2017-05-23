package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.Precaution;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 30.03.2017.
 */
public class SelectPrecautionByCheck_event_link extends MappingSqlQuery<Precaution>{
    private static final String SQL_SELECT_PRECAUTION = "SELECT pt.*,pct.name FROM precaution_table pt " +
            "INNER JOIN precaution_catalog_table pct ON(pct.id = pt.precaution_catalog_link) WHERE pt.check_event_link = :check_event_link";

    public SelectPrecautionByCheck_event_link(DataSource ds) {
        super(ds, SQL_SELECT_PRECAUTION );
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
    }

    @Override
    protected Precaution mapRow(ResultSet resultSet, int i) throws SQLException {
        Precaution precaution = new Precaution();
        precaution.setId(resultSet.getLong("id"));
        precaution.setCheck_event_link(resultSet.getLong("check_event_link"));
        precaution.setPrecaution_catalog_link(resultSet.getInt("precaution_catalog_link"));
        precaution.setDecision_number(resultSet.getString("decision_number"));
        precaution.setService_date(resultSet.getString("service_date"));
        precaution.setPlan_date(resultSet.getString("plan_date"));
        precaution.setFact_date(resultSet.getString("fact_date"));
        precaution.setPrecaution_name(resultSet.getString("name"));
        return precaution;
    }
}
