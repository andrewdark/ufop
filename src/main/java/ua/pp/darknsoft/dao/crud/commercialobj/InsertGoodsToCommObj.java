package ua.pp.darknsoft.dao.crud.commercialobj;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 25.03.2017.
 */
public class InsertGoodsToCommObj extends SqlUpdate{
    private final static String SQL_INSERT_GOODS="INSERT INTO commercial_object_basic_group_of_goods_table(comm_obj_link,goods_catalog_link) " +
            "VALUES(:comm_obj_link,:goods_catalog_link)";

    public InsertGoodsToCommObj(DataSource ds) {
        super(ds, SQL_INSERT_GOODS);
        super.declareParameter(new SqlParameter("comm_obj_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("goods_catalog_link",Types.INTEGER));
        compile();
    }
}
