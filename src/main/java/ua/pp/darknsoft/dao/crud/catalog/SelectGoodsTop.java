package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.BasicGroupOfGoodsCatalog;
import ua.pp.darknsoft.entity.LocationCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 03.02.2017.
 */
public class SelectGoodsTop extends MappingSqlQuery<BasicGroupOfGoodsCatalog> {
    private static final String SQL_SELECT_GOODS_CATALOG="SELECT * FROM basic_group_of_goods_catalog_table WHERE nlevel(treemark) <=1 ORDER BY id";

    public SelectGoodsTop(DataSource ds){
        super(ds, SQL_SELECT_GOODS_CATALOG);
    }

    @Override
    protected BasicGroupOfGoodsCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        BasicGroupOfGoodsCatalog goods = new BasicGroupOfGoodsCatalog();
        goods.setId(resultSet.getInt("id"));
        goods.setName(resultSet.getString("name"));
        goods.setTreemark(resultSet.getString("treemark"));
        goods.setDegree_of_a_risk_link(resultSet.getShort("degree_of_a_risk_link"));
        return goods;
    }
}
