package ua.pp.darknsoft.dao.crud.commercialobj;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.BasicGroupOfGoodsCatalog;
import ua.pp.darknsoft.entity.GoodsOfCommObj;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Dark on 25.03.2017.
 */
public class SelectCommObjGoodsByCommObj_link extends MappingSqlQuery {
    private static final String SQL_SELECT_GOODS_CATALOG="SELECT g.*,gcat.name FROM commercial_object_basic_group_of_goods_table g " +
            "INNER JOIN basic_group_of_goods_catalog_table gcat ON (gcat.treemark = g.goods_catalog_link::ltree) WHERE g.comm_obj_link = :comm_obj_link";

    public SelectCommObjGoodsByCommObj_link(DataSource ds) {
        super(ds, SQL_SELECT_GOODS_CATALOG);
        super.declareParameter(new SqlParameter("comm_obj_link", Types.BIGINT));
    }

    @Override
    protected Object mapRow(ResultSet resultSet, int i) throws SQLException {
        GoodsOfCommObj goodsOfCommObj = new GoodsOfCommObj();
        goodsOfCommObj.setId(resultSet.getLong("id"));
        goodsOfCommObj.setComm_obj_link(resultSet.getLong("comm_obj_link"));
        goodsOfCommObj.setGoods_catalog_link(resultSet.getString("goods_catalog_link"));
        goodsOfCommObj.setName(resultSet.getString("name"));
        return goodsOfCommObj;
    }
}
