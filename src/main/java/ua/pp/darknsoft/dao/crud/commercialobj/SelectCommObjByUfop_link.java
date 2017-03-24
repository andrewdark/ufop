package ua.pp.darknsoft.dao.crud.commercialobj;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.dao.crud.catalog.SelectParentLocationByTreemark;
import ua.pp.darknsoft.entity.CommercialObject;
import ua.pp.darknsoft.entity.LocationCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dark on 27.02.2017.
 */
public class SelectCommObjByUfop_link extends MappingSqlQuery<CommercialObject>{
    private SelectParentLocationByTreemark selectParentLocationByTreemark;
    private static final String SELECT_ENTREPRENEUR_COMM_OBJ = "SELECT coet.*, cott.name s_obj_type FROM comm_obj_entrepreneur_table coet INNER JOIN commercial_object_type_table cott ON(cott.id = coet.obj_type) WHERE coet.ufop_link = :ufop_link";

    public SelectCommObjByUfop_link(DataSource ds) {
        super(ds, SELECT_ENTREPRENEUR_COMM_OBJ);
        super.declareParameter(new SqlParameter("ufop_link", Types.BIGINT));
        this.selectParentLocationByTreemark = new SelectParentLocationByTreemark(ds);
    }

    @Override
    protected CommercialObject mapRow(ResultSet resultSet, int i) throws SQLException {
        CommercialObject commObj = new CommercialObject();
        commObj.setId(resultSet.getLong("id"));
        commObj.setObj_name(resultSet.getString("obj_name"));
        commObj.setObj_type(resultSet.getInt("obj_type"));
        commObj.setS_obj_type(resultSet.getString("s_obj_type"));
        commObj.setA_place_of_reg(resultSet.getString("a_obj_location"));
        commObj.setN_place_of_reg(resultSet.getString("n_obj_location"));
        commObj.setLocationCatalog((LocationCatalog) getLoc(resultSet.getString("a_obj_location")));
        commObj.setDescription(resultSet.getString("description"));
        return commObj;
    }

    private List<LocationCatalog> getLoc(String treemark){
        Map<String,String> bind = new HashMap<>(3);
        bind.put("treemark",treemark);
        return selectParentLocationByTreemark.executeByNamedParam(bind);
    }
}
