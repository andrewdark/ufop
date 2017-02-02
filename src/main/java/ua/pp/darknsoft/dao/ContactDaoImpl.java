package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.contact.InsertContact;
import ua.pp.darknsoft.dao.crud.contact.SelectContact;
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
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectContact = new SelectContact(dataSource);
        //this.insertContact = new InsertContact(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Contact> selectContact() {
        return selectContact.execute();
    }

    @Override
    public long insert(Contact contact) {
        String sql = "INSERT INTO contact_table (first_name,last_name) VALUES (:first_name,:last_name)";
        Map<String, Object> bind = new HashMap<>(3);
        bind.put("first_name", contact.getFirst_name());
        bind.put("last_name", contact.getFirst_name());
        SqlParameterSource paramSource = new MapSqlParameterSource(bind);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String[] columnNames = new String[]{"id"};

        this.namedParameterJdbcTemplate.update(sql, paramSource, keyHolder, columnNames);
        Map<String, Object> keys = keyHolder.getKeys();
        return (Long) keys.get("id");
    }
}
