package ua.pp.darknsoft.dao.crud.contact;

import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.jdbc.object.SqlUpdate;
import ua.pp.darknsoft.entity.Contact;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 31.01.2017.
 */
public class InsertContact extends MappingSqlQuery<Contact> {
    private final static String SQL_INSERT_CONTACT = "SELECT * FROM contact_table";

    public InsertContact(DataSource ds) {
        super(ds, SQL_INSERT_CONTACT);
    }

    @Override
    protected Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        Contact contact = new Contact();
        contact.setId(resultSet.getLong("id"));

        return contact;
    }
}
