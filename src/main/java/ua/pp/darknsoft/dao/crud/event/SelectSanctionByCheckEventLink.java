package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.Sanction;
import ua.pp.darknsoft.entity.SanctionSupplemented;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 30.03.2017.
 */
public class SelectSanctionByCheckEventLink extends MappingSqlQuery<Sanction>{
    private static final String SQL_SELECT_SANCTION = "SELECT * FROM sanctions_table WHERE check_event_link = :check_event_link";

    public SelectSanctionByCheckEventLink(DataSource ds) {
        super(ds, SQL_SELECT_SANCTION);
        super.declareParameter(new SqlOutParameter("check_event_link", Types.BIGINT));
    }

    @Override
    protected Sanction mapRow(ResultSet resultSet, int i) throws SQLException {
        Sanction sanction = new Sanction();
        sanction.setId(resultSet.getLong("id"));
        sanction.setCheck_event_link(resultSet.getLong("check_event_link"));
        sanction.setCharged_amount(resultSet.getString("charged_amount"));
        sanction.setService_date(resultSet.getString("service_date"));
        sanction.setPlan_date(resultSet.getString("plan_date"));
        sanction.setFact_date(resultSet.getString("fact_date"));
        return sanction;
    }
}
