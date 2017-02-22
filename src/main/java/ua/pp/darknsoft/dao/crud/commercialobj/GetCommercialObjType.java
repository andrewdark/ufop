package ua.pp.darknsoft.dao.crud.commercialobj;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.CommercialObjectType;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 20.02.2017.
 */
public class GetCommercialObjType extends MappingSqlQuery<CommercialObjectType>{
    private static final String SQL_SELECT_COMMOBJTYPE="SELECT * FROM commercial_object_type_table";
    public GetCommercialObjType(DataSource ds) {
        super(ds,SQL_SELECT_COMMOBJTYPE);
    }
    @Override
    protected CommercialObjectType mapRow(ResultSet resultSet, int i) throws SQLException {
        CommercialObjectType commercialObjectType = new CommercialObjectType();
        commercialObjectType.setId(resultSet.getInt("id"));
        commercialObjectType.setName(resultSet.getString("name"));
        commercialObjectType.setDescription(resultSet.getString("description"));
        return commercialObjectType;
    }
}
