package ua.pp.darknsoft.dao.crud.contact;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.Contact;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 03.03.2017.
 */
public class SelectContactByUserName extends MappingSqlQuery<Contact> {
    private final static String SELECT_CONTACT_SQL = "SELECT c.* FROM contact_table c WHERE " +
            "id=(SELECT contact_link FROM user_table WHERE LOWER(username)=LOWER(:username))";

    public SelectContactByUserName(DataSource ds) {
        super(ds, SELECT_CONTACT_SQL);
        super.declareParameter(new SqlParameter("username", Types.VARCHAR));
    }


    @Override
    protected Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        Contact contact = new Contact();
        contact.setId(resultSet.getLong("id"));
        contact.setFirst_name(resultSet.getString("first_name"));
        contact.setLast_name(resultSet.getString("last_name"));
        contact.setPatronymic_name(resultSet.getString("patronymic_name"));
        contact.setRntc(resultSet.getString("rntc"));
        contact.setSeries_of_passport(resultSet.getString("series_of_passport"));
        contact.setNumber_of_passport(resultSet.getString("number_of_passport"));
        contact.setA_stay_address(resultSet.getString("a_place_of_reg"));
        contact.setN_stay_address(resultSet.getString("n_place_of_reg"));
        contact.setF_stay_address(resultSet.getString("f_place_of_reg"));
        contact.setB_stay_address(resultSet.getString("b_place_of_reg"));
        contact.setTel(resultSet.getString("tel"));
        contact.setFax(resultSet.getString("fax"));
        contact.setEmail(resultSet.getString("email"));
        contact.setDatereg(resultSet.getTimestamp("datereg"));
        //contact.setOwner(resultSet.getString("username"));
        contact.setBirthday(resultSet.getString("birthday"));
        contact.setOrganization(resultSet.getString("organization"));
        contact.setPosition(resultSet.getString("position"));
        contact.setDescription(resultSet.getString("description"));
        return contact;
    }
}
