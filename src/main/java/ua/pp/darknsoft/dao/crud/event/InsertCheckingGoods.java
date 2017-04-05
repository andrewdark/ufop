package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 26.03.2017.
 */
public class InsertCheckingGoods extends SqlUpdate{
    private static final String SQL_INSERT ="INSERT INTO checking_group_of_goods_table (check_event_link,goods_catalog_link,checking)" +
            "VALUES (:check_event_link,:goods_catalog_link,:checking)";

    public InsertCheckingGoods(DataSource ds) {
        super(ds, SQL_INSERT);

        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("goods_catalog_link", Types.VARCHAR));
        super.declareParameter(new SqlParameter("checking", Types.BOOLEAN));

    }
}
