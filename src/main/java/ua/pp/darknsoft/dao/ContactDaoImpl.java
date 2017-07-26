package ua.pp.darknsoft.dao;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.contact.*;
import ua.pp.darknsoft.entity.Contact;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 31.01.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ContactDaoImpl implements ContactDao, Serializable {
    DataSource dataSource;
    //InsertContact insertContact;
    SelectContact selectContact;
    SelectContactByUserName selectContactByUserName;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    SelectContactByOrganizationLink contactByOrganizationLink;
    SelectContactById selectContactById;
    UpdateContact updateContact;
    SelectContactByUserId selectContactByUserId;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectContact = new SelectContact(dataSource);
        //this.insertContact = new InsertContact(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.selectContactByUserName = new SelectContactByUserName(dataSource);
        this.contactByOrganizationLink = new SelectContactByOrganizationLink(dataSource);
        this.selectContactById = new SelectContactById(dataSource);
        this.updateContact = new UpdateContact(dataSource);
        this.selectContactByUserId = new SelectContactByUserId(dataSource);
    }

    @Override
    public List<Contact> getContactByOrganizationLink(long organization) {
        Map<String, Long> bind = new HashMap<>(3);
        bind.put("organization", organization);
        return contactByOrganizationLink.executeByNamedParam(bind);
    }

    @Override
    public List<Contact> getContactById(long id) {
        Map<String, Long> bind = new HashMap<>(3);
        bind.put("id", id);
        return selectContactById.executeByNamedParam(bind);
    }

    @Override
    public List<Contact> getContact(int total, int pageid) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("total", total);
        bind.put("pageid", pageid);

        return selectContact.executeByNamedParam(bind);
    }

    @Override
    public Contact getContactByName(String username) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("username", username);
        Contact contact = selectContactByUserName.executeByNamedParam(bind).get(0);
        return contact;
    }
    @Override
    public Contact getContactByUserId(int user_id) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("id", user_id);
        Contact contact = selectContactByUserId.executeByNamedParam(bind).get(0);
        return contact;
    }

    @Override
    public long insert(Contact contact) {
        if (contact.getBirthday().isEmpty()) contact.setBirthday("0001-01-01");
        String sql = "INSERT INTO contact_table (first_name,last_name,creator_link,patronymic_name,a_stay_address,n_stay_address,f_stay_address,b_stay_address," +
                "series_of_passport,number_of_passport,tel,fax,email,birthday,organization,\"position\",description) " +
                "VALUES (:first_name,:last_name,(SELECT id FROM user_table WHERE LOWER (username) = LOWER (:creator_link)),:patronymic_name,:a_stay_address,:n_stay_address,:f_stay_address,:b_stay_address," +
                ":series_of_passport,:number_of_passport,:tel,:fax,:email,:birthday::DATE,:organization,:position,:description)";

        Map<String, Object> bind = new HashMap<>();
        bind.put("first_name", contact.getFirst_name());
        bind.put("last_name", contact.getLast_name());
        bind.put("patronymic_name", contact.getPatronymic_name());
        bind.put("a_stay_address", contact.getA_stay_address());
        bind.put("n_stay_address", contact.getN_stay_address());
        bind.put("f_stay_address", contact.getF_stay_address());
        bind.put("b_stay_address", contact.getB_stay_address());
        bind.put("series_of_passport", contact.getSeries_of_passport());
        bind.put("number_of_passport", contact.getNumber_of_passport());
        bind.put("tel", contact.getTel());
        bind.put("fax", contact.getFax());
        bind.put("email", contact.getEmail());
        bind.put("birthday", contact.getBirthday());
        bind.put("organization", contact.getOrganization());
        bind.put("position", contact.getPosition());
        bind.put("creator_link", contact.getCreator_link());
        bind.put("description", contact.getDescription());
        if (contact.getRntc().length() == 10) {
            bind.put("rntc", contact.getRntc());
            sql = "INSERT INTO contact_table (rntc,first_name,last_name,creator_link,patronymic_name,a_stay_address,n_stay_address,f_stay_address,b_stay_address," +
                    "series_of_passport,number_of_passport,tel,fax,email,birthday,organization,\"position\",description) " +
                    "VALUES (:rntc,:first_name,:last_name,(SELECT id FROM user_table WHERE LOWER (username) = LOWER (:creator_link)),:patronymic_name,:a_stay_address,:n_stay_address,:f_stay_address,:b_stay_address," +
                    ":series_of_passport,:number_of_passport,:tel,:fax,:email,:birthday::DATE,:organization,:position,:description)";
        }


        SqlParameterSource paramSource = new MapSqlParameterSource(bind);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String[] columnNames = new String[]{"id"};

        this.namedParameterJdbcTemplate.update(sql, paramSource, keyHolder, columnNames);
        Map<String, Object> keys = keyHolder.getKeys();
        return (Long) keys.get("id");
    }

    @Override
    public String getContactByRntc(String rntc) {
        String sql = "SELECT rntc FROM contact_table WHERE rntc=:rntc";
        Map<String, Object> bind = new HashMap<>();
        bind.put("rntc", rntc);

        return (String) namedParameterJdbcTemplate.queryForObject(sql, bind, String.class);
    }

    @Override
    public void updateContact(Contact contact) {
        Map<String, Object> bind = new HashedMap();
        if (contact.getBirthday().isEmpty()) contact.setBirthday("0001-01-01");
        bind.put("id", contact.getId());
        bind.put("first_name", contact.getFirst_name());
        bind.put("last_name", contact.getLast_name());
        bind.put("patronymic_name", contact.getPatronymic_name());
        bind.put("a_stay_address", contact.getA_stay_address());
        bind.put("n_stay_address", contact.getN_stay_address());
        bind.put("f_stay_address", contact.getF_stay_address());
        bind.put("b_stay_address", contact.getB_stay_address());
        bind.put("series_of_passport", contact.getSeries_of_passport());
        bind.put("number_of_passport", contact.getNumber_of_passport());
        bind.put("tel", contact.getTel());
        bind.put("fax", contact.getFax());
        bind.put("email", contact.getEmail());
        bind.put("birthday", contact.getBirthday());
        bind.put("organization", contact.getOrganization());
        bind.put("position", contact.getPosition());
        bind.put("creator_link", contact.getCreator_link());
        bind.put("description", contact.getDescription());

        if (contact.getRntc().length() == 10) bind.put("rntc", contact.getRntc());
        else bind.put("rntc", null);
        updateContact.updateByNamedParam(bind);
    }
}
