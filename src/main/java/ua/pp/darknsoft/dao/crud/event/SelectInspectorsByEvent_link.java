package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.Inspectors;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 29.05.2017.
 */
public class SelectInspectorsByEvent_link extends MappingSqlQuery<Inspectors> {
    private static final String SQL_SELECT = "SELECT it.*,ct.first_name,ct.last_name, sct.name structure_name FROM inspectors_table it " +
            "LEFT JOIN user_table ut ON (it.user_link=ut.id) LEFT JOIN contact_table ct ON (ct.id = ut.contact_link) LEFT JOIN structure_catalog_table sct ON (it.structure_link::ltree = sct.treemark) " +
            "WHERE check_event_link = :check_event_link";

    public SelectInspectorsByEvent_link(DataSource ds) {
        super(ds, SQL_SELECT);
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
    }

    @Override
    protected Inspectors mapRow(ResultSet resultSet, int i) throws SQLException {
        Inspectors inspectors = new Inspectors();
        inspectors.setId(resultSet.getLong("id"));
        inspectors.setCheck_event_link(resultSet.getLong("check_event_link"));
        inspectors.setUser_link(resultSet.getInt("user_link"));
        inspectors.setUser_name(resultSet.getString("last_name")+" "+resultSet.getString("first_name"));
        inspectors.setStructure_link(resultSet.getString("structure_link"));
        inspectors.setStructure_name(resultSet.getString("structure_name"));
        inspectors.setAccepted(resultSet.getBoolean("accepted"));
        inspectors.setDatereg(resultSet.getTimestamp("datereg"));
        inspectors.setCreator_link(resultSet.getInt("creator_link"));
        return inspectors;
    }
}
