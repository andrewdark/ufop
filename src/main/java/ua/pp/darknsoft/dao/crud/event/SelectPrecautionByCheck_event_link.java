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
    private static final String SQL_SELECT_PRECAUTION = "SELECT * FROM precaution_table WHERE check_event_link = :check_event_link";

    public SelectPrecautionByCheck_event_link(DataSource ds) {
        super(ds, SQL_SELECT_PRECAUTION );
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
    }

    @Override
    protected Precaution mapRow(ResultSet resultSet, int i) throws SQLException {
        Precaution precaution = new Precaution();
        precaution.setId(resultSet.getLong("id"));
        precaution.setCheck_event_link(resultSet.getLong("check_event_link"));
        precaution.setService_date(resultSet.getString("service_date"));
        precaution.setPlan_date(resultSet.getString("plan_date"));
        precaution.setFact_date(resultSet.getString("fact_date"));
        return precaution;
    }
}
