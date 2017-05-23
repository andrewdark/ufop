package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.Sanction;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 23.05.2017.
 */
public class SelectSanctionByCheckEventLink extends MappingSqlQuery<Sanction> {
    private static final String SQL_SELECT = "SELECT *,charged_amount::money::numeric::float8 amount FROM sanctions_table WHERE check_event_link = :check_event_link";

    public SelectSanctionByCheckEventLink(DataSource ds) {
        super(ds, SQL_SELECT);
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
    }

    @Override
    protected Sanction mapRow(ResultSet resultSet, int i) throws SQLException {
        Sanction sanction = new Sanction();
        sanction.setId(resultSet.getLong("id"));
        sanction.setCheck_event_link(resultSet.getLong("check_event_link"));
        sanction.setArticles_law_link(resultSet.getString("articles_law_link"));
        sanction.setSanction_number(resultSet.getString("sanction_number"));
        sanction.setCharged_amount(resultSet.getBigDecimal("amount"));
        sanction.setService_date(resultSet.getString("service_date"));
        sanction.setPlan_date(resultSet.getString("plan_date"));
        sanction.setFact_date(resultSet.getString("fact_date"));
        return sanction;
    }
}
