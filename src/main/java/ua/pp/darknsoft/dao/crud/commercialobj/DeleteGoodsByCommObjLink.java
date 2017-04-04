package ua.pp.darknsoft.dao.crud.commercialobj;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 04.04.2017.
 */
public class DeleteGoodsByCommObjLink extends SqlUpdate {
    private static final String SQL_DELETE = "DELETE FROM commercial_object_basic_group_of_goods_table WHERE id=:id";

    public DeleteGoodsByCommObjLink(DataSource ds) {
        super(ds, SQL_DELETE );
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
        compile();
    }
}
