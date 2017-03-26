package ua.pp.darknsoft.dao.crud.ufop;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;
import javax.sql.DataSource;
import java.sql.Types;


/**
 * Created by Andrew on 15.03.2017.
 */
public class InsertUfopReturnId extends SqlUpdate {
    private static final String SQL_INSERT = "INSERT INTO ufop_table" +
            "(ufop_is,ufop_name,ufop_code,a_place_of_reg,n_place_of_reg,f_place_of_reg,b_place_of_reg,creator_link,description) " +
            "VALUES (:ufop_is,:ufop_name,:ufop_code,:a_place_of_reg,:n_place_of_reg,:f_place_of_reg,:b_place_of_reg,(SELECT id FROM user_table WHERE username = :creator_link),:description)";

    public InsertUfopReturnId(DataSource ds) {
        super(ds, SQL_INSERT);
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
