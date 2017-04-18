package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 04.04.2017.
 */
public class DeleteCheckingGoodsById extends SqlUpdate {
    private static final String SQL_DELETE = "DELETE FROM checking_group_of_goods_table WHERE id=:id";

    public DeleteCheckingGoodsById(DataSource ds) {
        super(ds, SQL_DELETE );
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
        compile();
    }
}
