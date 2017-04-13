package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.CheckingGroupOfGoods;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 13.04.2017.
 */
public class SelectCheckGoodsByEvent_link extends MappingSqlQuery<CheckingGroupOfGoods>{
private static final String SQL_SELECT_CHECKINGGoG="SELECT cggt.*,bgct.name FROM checking_group_of_goods_table cggt " +
        "LEFT JOIN basic_group_of_goods_catalog_table bgct ON (cggt.goods_catalog_link::ltree = bgct.treemark) " +
        "WHERE cggt.check_event_link = :check_event_link";
    public SelectCheckGoodsByEvent_link(DataSource ds) {
        super(ds, SQL_SELECT_CHECKINGGoG);
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
    }

    @Override
    protected CheckingGroupOfGoods mapRow(ResultSet resultSet, int i) throws SQLException {
        CheckingGroupOfGoods checkingGroupOfGoods = new CheckingGroupOfGoods();
        checkingGroupOfGoods.setId(resultSet.getLong("id"));
        checkingGroupOfGoods.setCheck_event_link(resultSet.getLong("check_event_link"));
        checkingGroupOfGoods.setGoods_catalog_link(resultSet.getString("goods_catalog_link"));
        checkingGroupOfGoods.setS_goods_catalog_link(resultSet.getString("name"));
        checkingGroupOfGoods.setChecking(resultSet.getBoolean("checking"));
        return checkingGroupOfGoods;
    }
}
