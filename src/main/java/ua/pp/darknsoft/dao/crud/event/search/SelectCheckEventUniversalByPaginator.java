package ua.pp.darknsoft.dao.crud.event.search;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.CheckEvent;
import ua.pp.darknsoft.entity.CheckEventSupplemented;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Dark on 26.03.2017.
 */
public class SelectCheckEventUniversalByPaginator extends MappingSqlQuery<CheckEvent> {
    private static final String SQL_SELECT_CHECK_EVENT = "SELECT ce.*,u.username, sct.name structure_catalog_name FROM check_event_table ce " +
            "INNER JOIN user_table u ON(u.id = ce.creator_link) LEFT JOIN structure_catalog_table sct ON (ce.structure_catalog_link = sct.id) " +
            "WHERE " +
            "(ce.check_type = :check_type and :check_type is not null or :check_type is null) AND " +
            "(ce.check_violation = :check_violation and :check_violation is not null or :check_violation is null) AND " +
            "(ce.event_date_end BETWEEN :date_start::DATE and :date_stop::DATE) AND " +
            "(ce.structure_catalog_link = :structure_catalog_link and :structure_catalog_link is not null or :structure_catalog_link is null) " +
            "ORDER BY ce.id LIMIT :total OFFSET :pageid";

    public SelectCheckEventUniversalByPaginator(DataSource ds) {
        super(ds, SQL_SELECT_CHECK_EVENT);
        super.declareParameter(new SqlParameter("total", Types.INTEGER));
        super.declareParameter(new SqlParameter("pageid", Types.INTEGER));
        super.declareParameter(new SqlParameter("check_type", Types.INTEGER));
        super.declareParameter(new SqlParameter("check_violation", Types.INTEGER));
        super.declareParameter(new SqlParameter("date_start",Types.DATE));
        super.declareParameter(new SqlParameter("date_stop",Types.DATE));
        super.declareParameter(new SqlParameter("structure_catalog_link", Types.INTEGER));
    }

    @Override
    protected CheckEvent mapRow(ResultSet resultSet, int i) throws SQLException {
        CheckEventSupplemented checkEvent = new CheckEventSupplemented();
        checkEvent.setId(resultSet.getLong("id"));
        checkEvent.setUfop_link(resultSet.getLong("ufop_link"));
        checkEvent.setEvent_number(resultSet.getString("event_number"));
        checkEvent.setEvent_date_begin(resultSet.getString("event_date_begin"));
        checkEvent.setEvent_date_end(resultSet.getString("event_date_end"));
        checkEvent.setCheck_type(resultSet.getInt("check_type"));
        checkEvent.setCheck_violation(resultSet.getInt("check_violation"));
        checkEvent.setEvent_result(resultSet.getString("event_result"));
        checkEvent.setCheck_sampling(resultSet.getInt("check_sampling"));
        checkEvent.setResult_sampling(resultSet.getInt("result_sampling"));
        checkEvent.setCreator_link(resultSet.getString("username"));
        checkEvent.setStructure_catalog_link(resultSet.getInt("structure_catalog_link"));
        checkEvent.setStructure_catalog_name(resultSet.getString("structure_catalog_name"));
        checkEvent.setDatereg(resultSet.getTimestamp("datereg"));


        return checkEvent;
    }
}
