package ua.pp.darknsoft.dao.crud.commercialobj;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import ua.pp.darknsoft.entity.Contact;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 16.02.2017.
 */
public class InsertCommercialObject extends SqlUpdate {

    private final static String SQL_INSERT_CommObj="INSERT INTO commercial_object_table(ufop_link,obj_type,obj_name,a_obj_location,n_obj_location,owner)" +
            " VALUES (:ufop_link,:obj_type,:obj_name,:a_obj_location,:n_obj_location,(SELECT id FROM user_table WHERE username =:owner))";

    public InsertCommercialObject(DataSource dataSource){
        super(dataSource,SQL_INSERT_CommObj);
        super.declareParameter(new SqlParameter("ufop_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("obj_type", Types.INTEGER));
        super.declareParameter(new SqlParameter("obj_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("a_obj_location", Types.VARCHAR));
        super.declareParameter(new SqlParameter("n_obj_location", Types.VARCHAR));
        super.declareParameter(new SqlParameter("owner", Types.VARCHAR));
        compile();

    }
}
