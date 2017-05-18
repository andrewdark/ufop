package ua.pp.darknsoft.dao.crud.contact;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 18.05.2017.
 */
public class UpdateContact extends SqlUpdate{
    private static final String SQL_UPDATE = "UPDATE contact_table SET " +
            "first_name = :first_name,last_name = :last_name,patronymic_name = :patronymic_name,rntc = :rntc," +
            "series_of_passport = :series_of_passport,number_of_passport = :number_of_passport,a_stay_address = :a_stay_address," +
            "n_stay_address = :n_stay_address,f_stay_address = :f_stay_address,b_stay_address = :b_stay_address," +
            "tel = :tel,fax = :fax,email = :email,creator_link=(SELECT id FROM user_table WHERE LOWER (username) = LOWER (:creator_link))," +
            "birthday = :birthday,organization = :organization,\"position\" = :position,description = :description " +
            "WHERE id = :id";

    public UpdateContact(DataSource ds) {
        super(ds, SQL_UPDATE);
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
        super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("patronymic_name", Types.VARCHAR));
        super.declareParameter(new SqlParameter("rntc", Types.VARCHAR));
        super.declareParameter(new SqlParameter("series_of_passport", Types.VARCHAR));
        super.declareParameter(new SqlParameter("number_of_passport", Types.VARCHAR));
        super.declareParameter(new SqlParameter("a_stay_address", Types.VARCHAR));
        super.declareParameter(new SqlParameter("n_stay_address", Types.VARCHAR));
        super.declareParameter(new SqlParameter("f_stay_address", Types.VARCHAR));
        super.declareParameter(new SqlParameter("b_stay_address", Types.VARCHAR));
        super.declareParameter(new SqlParameter("tel", Types.VARCHAR));
        super.declareParameter(new SqlParameter("fax", Types.VARCHAR));
        super.declareParameter(new SqlParameter("email", Types.VARCHAR));
        super.declareParameter(new SqlParameter("creator_link", Types.VARCHAR));
        super.declareParameter(new SqlParameter("birthday", Types.DATE));
        super.declareParameter(new SqlParameter("organization", Types.BIGINT));
        super.declareParameter(new SqlParameter("position", Types.VARCHAR));
        super.declareParameter(new SqlParameter("description", Types.VARCHAR));
        compile();

    }
}
