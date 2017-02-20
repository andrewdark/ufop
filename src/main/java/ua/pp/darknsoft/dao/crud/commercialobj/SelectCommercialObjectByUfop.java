package ua.pp.darknsoft.dao.crud.commercialobj;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.CommercialObject;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 20.02.2017.
 */
public class SelectCommercialObjectByUfop extends MappingSqlQuery<CommercialObject> {
    private static final String SQL_SELECT_COMMOBJ="SELECT * FROM commercial_object_table WHERE ";
    public SelectCommercialObjectByUfop(DataSource ds) {
        super(ds,SQL_SELECT_COMMOBJ);
    }

    @Override
    protected CommercialObject mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
