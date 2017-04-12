package ua.pp.darknsoft.dao.crud.commercialobj;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.dao.crud.catalog.SelectParentLocationByTreemark;
import ua.pp.darknsoft.entity.CommercialObject;
import ua.pp.darknsoft.entity.GoodsOfCommObj;
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
    private SelectCommObjGoodsByCommObj_link selectCommObjGoodsByCommObj_link;
    private static final String SELECT_ENTREPRENEUR_COMM_OBJ = "SELECT coet.*, cott.name s_obj_type, drkt.title s_degree_risk_link  " +
            "FROM comm_object_table coet LEFT JOIN commercial_object_type_table cott ON(cott.id = coet.obj_type) " +
            "LEFT JOIN degree_risk_catalog_table drkt ON(drkt.id = coet.degree_risk_link) " +
            "WHERE coet.ufop_link = :ufop_link";

    public SelectCommObjByUfop_link(DataSource ds) {
        super(ds, SELECT_ENTREPRENEUR_COMM_OBJ);
        super.declareParameter(new SqlParameter("ufop_link", Types.BIGINT));
        this.selectParentLocationByTreemark = new SelectParentLocationByTreemark(ds);
        this.selectCommObjGoodsByCommObj_link = new SelectCommObjGoodsByCommObj_link(ds);
    }

    @Override
    protected CommercialObject mapRow(ResultSet resultSet, int i) throws SQLException {
        CommercialObject commObj = new CommercialObject();
        commObj.setId(resultSet.getLong("id"));
        commObj.setObj_name(resultSet.getString("obj_name"));
        commObj.setObj_type(resultSet.getInt("obj_type"));
        commObj.setS_obj_type(resultSet.getString("s_obj_type"));
        commObj.setA_place_of_reg(resultSet.getString("a_place_of_reg"));
        commObj.setN_place_of_reg(resultSet.getString("n_place_of_reg"));
        commObj.setF_place_of_reg(resultSet.getString("f_place_of_reg"));
        commObj.setB_place_of_reg(resultSet.getString("b_place_of_reg"));
        commObj.setDegree_risk_link(resultSet.getInt("degree_risk_link"));
        commObj.setS_degree_risk_link(resultSet.getString("s_degree_risk_link"));
        commObj.setLocationCatalog((List<LocationCatalog>) getLoc(resultSet.getString("a_place_of_reg")));
        commObj.setGoodsList((List<GoodsOfCommObj>) getGoods(resultSet.getLong("id")));
        commObj.setDescription(resultSet.getString("description"));
        return commObj;
    }

    private List<LocationCatalog> getLoc(String treemark){
        Map<String,String> bind = new HashMap<>(3);
        bind.put("treemark",treemark);
        return selectParentLocationByTreemark.executeByNamedParam(bind);
    }
    private List<GoodsOfCommObj> getGoods(Long id){
        Map<String,Long> bind = new HashMap<>(3);
        bind.put("comm_obj_link",id);
        return selectCommObjGoodsByCommObj_link.executeByNamedParam(bind);
    }
}
