package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.BasicGroupOfGoodsCatalog;
import ua.pp.darknsoft.entity.LocationCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 03.02.2017.
 */
public class SelectGoodsByTreemark extends MappingSqlQuery<BasicGroupOfGoodsCatalog> {
    private static final String SQL_SELECT_GOODS_CATALOG="SELECT l.*, nlevel(l.treemark) FROM basic_group_of_goods_catalog_table l WHERE l.treemark<@:treemark::ltree AND nlevel(l.treemark) = :nlevel ORDER BY l.id";

    public SelectGoodsByTreemark(DataSource ds){
        super(ds, SQL_SELECT_GOODS_CATALOG);
        super.declareParameter(new SqlParameter("treemark", Types.VARCHAR));
        super.declareParameter(new SqlParameter("nlevel", Types.INTEGER));

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