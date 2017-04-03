package ua.pp.darknsoft.dao.crud.ufop;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;


/**
 * Created by Andrew on 15.03.2017.
 */
public class UpdateUfopReturnId extends SqlUpdate {
    private static final String SQL_INSERT = "UPDATE ufop_table SET " +
            "ufop_is=:ufop_is,ufop_name=:ufop_name,ufop_code=:ufop_code,a_place_of_reg=:a_place_of_reg,n_place_of_reg=:n_place_of_reg, " +
            "f_place_of_reg=:f_place_of_reg,b_place_of_reg=:b_place_of_reg,creator_link=(SELECT id FROM user_table WHERE username = :creator_link),description=:description " +
            "WHERE id=:id";

    public UpdateUfopReturnId(DataSource ds) {
        super(ds, SQL_INSERT);
        super.declareParameter(new SqlParameter("id",Types.BIGINT));
        super.declareParameter(new SqlParameter("ufop_is", Types.SMALLINT));
        super.declareParameter(new SqlParameter("ufop_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("ufop_code", Types.VARCHAR));
        super.declareParameter(new SqlParameter("a_place_of_reg", Types.VARCHAR));
        super.declareParameter(new SqlParameter("n_place_of_reg", Types.VARCHAR));
        super.declareParameter(new SqlParameter("f_place_of_reg", Types.VARCHAR));
        super.declareParameter(new SqlParameter("b_place_of_reg", Types.VARCHAR));
        super.declareParameter(new SqlParameter("creator_link", Types.VARCHAR));
        super.declareParameter(new SqlParameter("description", Types.VARCHAR));
        super.setGeneratedKeysColumnNames(new String[] {"id"});
        super.setReturnGeneratedKeys(true);
    }

}
