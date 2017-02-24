package ua.pp.darknsoft.dao.crud.etrepreneur;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.dao.crud.contact.SelectContactById;
import ua.pp.darknsoft.entity.Contact;
import ua.pp.darknsoft.entity.IndividualEntrepreneur;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew on 21.02.2017.
 */
public class SelectIEById extends MappingSqlQuery<IndividualEntrepreneur> {
    private final static String SELECT_CONTACT_SQL="SELECT c.*,u.username FROM individual_entrepreneur_table c " +
            "INNER JOIN user_table u ON(c.owner=u.id) WHERE c.id=:id ";
    private SelectContactById selectContactById;
    public SelectIEById(DataSource ds) {
        super(ds, SELECT_CONTACT_SQL);
        super.declareParameter(new SqlParameter("id", Types.BIGINT));
        this.selectContactById = new SelectContactById(ds);
    }

    @Override
    protected IndividualEntrepreneur mapRow(ResultSet resultSet, int i) throws SQLException {
        IndividualEntrepreneur individualEntrepreneur = new IndividualEntrepreneur();
        individualEntrepreneur.setId(resultSet.getLong("id"));
        individualEntrepreneur.setContact_link(resultSet.getLong("contact_link"));
        individualEntrepreneur.setA_place_of_reg(resultSet.getString("a_place_of_reg"));
        individualEntrepreneur.setN_place_of_reg(resultSet.getString("n_place_of_reg"));
        individualEntrepreneur.setRntc(resultSet.getString("rntc"));
        individualEntrepreneur.setSeries_of_passport(resultSet.getString("series_of_passport"));
        individualEntrepreneur.setNumber_of_passport(resultSet.getString("number_of_passport"));
        individualEntrepreneur.setRisk_group(resultSet.getInt("risk_group"));
        individualEntrepreneur.setIf_pdv(resultSet.getBoolean("if_pdv"));
        individualEntrepreneur.setDatereg(resultSet.getTimestamp("datereg"));
        individualEntrepreneur.setOwner(resultSet.getString("username"));
        individualEntrepreneur.setDescription(resultSet.getString("description"));
        individualEntrepreneur.setContact(getContactByid(resultSet.getLong("contact_link")));
        return individualEntrepreneur;
    }

    public Contact getContactByid(long id){
        Map<String,Long> bind = new HashMap<>();
        bind.put("id",id);

        try{
            return selectContactById.executeByNamedParam(bind).get(0);
        }catch (Exception ex){
            Contact fallContact = new Contact();
            fallContact.setId(0L);
            fallContact.setFirst_name("NULL");
            fallContact.setLast_name("Error: "+ex);
            return fallContact;
        }

    }
}
