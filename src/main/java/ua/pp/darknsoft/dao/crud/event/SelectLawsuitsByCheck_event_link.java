package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.Lawsuits;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 31.03.2017.
 */
public class SelectLawsuitsByCheck_event_link extends MappingSqlQuery<Lawsuits>{
    private static final String SQL_SELECT_LAWSUIT="SELECT * FROM lawsuits_table WHERE check_event_link = :check_event_link";

    public SelectLawsuitsByCheck_event_link(DataSource ds) {
        super(ds, SQL_SELECT_LAWSUIT);
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
    }

    @Override
    protected Lawsuits mapRow(ResultSet resultSet, int i) throws SQLException {
        Lawsuits lawsuits = new Lawsuits();
        lawsuits.setId(resultSet.getLong("id"));
        lawsuits.setCheck_event_link(resultSet.getLong("check_event_link"));
        lawsuits.setFiled_on_action(resultSet.getInt("filed_on_action"));
        lawsuits.setFiled_date(resultSet.getString("filed_date"));
        lawsuits.setResult_link(resultSet.getInt("result_link"));
        lawsuits.setDescription(resultSet.getString("description"));
        return lawsuits;
    }
}
