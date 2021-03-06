package ua.pp.darknsoft.dao.crud.commercialobj;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 24.03.2017.
 */
public class InsertCommercialObjectReturnId extends SqlUpdate {
    private final static String SQL_INSERT_USER="INSERT INTO comm_object_table(ufop_link,obj_type,obj_name,a_place_of_reg,n_place_of_reg,f_place_of_reg,b_place_of_reg,degree_risk_link,creator_link,description) " +
            "VALUES (:ufop_link,:obj_type,:obj_name,:a_place_of_reg,:n_place_of_reg,:f_place_of_reg,:b_place_of_reg,:degree_risk_link,(SELECT id FROM user_table WHERE LOWER (username) = LOWER (:creator_link)),:description)";

    public InsertCommercialObjectReturnId(DataSource dataSource){
        super(dataSource,SQL_INSERT_USER);
        super.declareParameter(new SqlParameter("ufop_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("obj_type", Types.INTEGER));
        super.declareParameter(new SqlParameter("obj_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("a_place_of_reg", Types.VARCHAR));
        super.declareParameter(new SqlParameter("n_place_of_reg", Types.VARCHAR));
        super.declareParameter(new SqlParameter("f_place_of_reg", Types.VARCHAR));
        super.declareParameter(new SqlParameter("b_place_of_reg", Types.VARCHAR));
        super.declareParameter(new SqlParameter("degree_risk_link", Types.INTEGER));
        super.declareParameter(new SqlParameter("creator_link", Types.VARCHAR));
        super.declareParameter(new SqlParameter("description", Types.VARCHAR));
        super.setGeneratedKeysColumnNames(new String[] {"id"});
        super.setReturnGeneratedKeys(true);


    }
}
