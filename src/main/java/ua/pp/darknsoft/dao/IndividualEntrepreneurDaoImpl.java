package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.contact.SelectContact;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew on 08.02.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndividualEntrepreneurDaoImpl implements IndividualEntrepreneurDao, Serializable{
    DataSource dataSource;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public String SelectContactByRntc(String rntc){
        String sql = "SELECT rntc FROM individual_table WHERE rntc=:rntc";
        Map<String, Object> bind = new HashMap<>();
        bind.put("rntc",rntc);

        return (String) namedParameterJdbcTemplate.queryForObject(sql,bind,String.class);
    }
}
