package ua.pp.darknsoft.dao.crud.commercialobj;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.EntrepreneurCommercialObject;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 20.02.2017.
 */
public class SelectCommercialObjectByUfop extends MappingSqlQuery<EntrepreneurCommercialObject> {
    private static final String SQL_SELECT_COMMOBJ="SELECT * FROM commercial_object_table WHERE ";
    public SelectCommercialObjectByUfop(DataSource ds) {
        super(ds,SQL_SELECT_COMMOBJ);
    }

    @Override
    protected EntrepreneurCommercialObject mapRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }
}
