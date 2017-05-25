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
public class SelectSanctionById extends MappingSqlQuery<Sanction> {
    private static final String SQL_SELECT = "SELECT st.*,st.charged_amount::money::numeric::float8 amount,cat.caption FROM sanctions_table st " +
            "INNER JOIN articles_law_catalog_table cat ON (st.articles_law_link::ltree = cat.treemark) WHERE st.id = :id";

    public SelectSanctionById(DataSource ds) {
        super(ds, SQL_SELECT);
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
    }

    @Override
    protected Sanction mapRow(ResultSet resultSet, int i) throws SQLException {
        Sanction sanction = new Sanction();
        sanction.setId(resultSet.getLong("id"));
        sanction.setCheck_event_link(resultSet.getLong("check_event_link"));
        sanction.setArticles_law_link(resultSet.getString("articles_law_link"));
        sanction.setArticles_law_caption(resultSet.getString("caption"));
        sanction.setSanction_number(resultSet.getString("sanction_number"));
        sanction.setCharged_amount(resultSet.getBigDecimal("amount").setScale(2, BigDecimal.ROUND_HALF_DOWN));
        sanction.setService_date(resultSet.getString("service_date"));
        sanction.setPlan_date(resultSet.getString("plan_date"));
        sanction.setFact_date(resultSet.getString("fact_date"));
        return sanction;
    }
}
