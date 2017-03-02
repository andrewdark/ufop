package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.etrepreneur.SelectIE;
import ua.pp.darknsoft.dao.crud.etrepreneur.SelectIEById;
import ua.pp.darknsoft.entity.IndividualEntrepreneur;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 08.02.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class IndividualEntrepreneurDaoImpl implements IndividualEntrepreneurDao, Serializable {
    private DataSource dataSource;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SelectIE selectIE;
    private SelectIEById selectIEById;


    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.selectIE = new SelectIE(dataSource);
        this.selectIEById = new SelectIEById(dataSource);
    }

    @Override
    public List<IndividualEntrepreneur> getEntrepreneur(int total, int pageid) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("total", total);
        bind.put("pageid", pageid);
        return selectIE.executeByNamedParam(bind);
    }
    @Override
    public List<IndividualEntrepreneur> getEntrepreneurById(long id){
        Map<String, Object> bind = new HashMap<>();
        bind.put("id",id);
        return selectIEById.executeByNamedParam(bind);
    }

    @Override
    public String SelectIEByRntc(String rntc) {
        String sql = "SELECT rntc FROM individual_entrepreneur_table WHERE rntc=:rntc";
        Map<String, Object> bind = new HashMap<>();
        bind.put("rntc", rntc);

        return (String) namedParameterJdbcTemplate.queryForObject(sql, bind, String.class);
    }

    @Override
    public long insertIE(IndividualEntrepreneur ie) {
        String sql = "INSERT INTO individual_entrepreneur_table (contact_link,a_place_of_reg,n_place_of_reg,series_of_passport,number_of_passport," +
                "if_pdv,risk_group,owner) " +
                "VALUES (:contact_link,:a_place_of_reg,:n_place_of_reg,:series_of_passport,:number_of_passport," +
                ":if_pdv,:risk_group,(SELECT id FROM user_table WHERE LOWER(username) =LOWER(:owner)))";

        Map<String, Object> bind = new HashMap<>();
        bind.put("contact_link", ie.getContact_link());

        bind.put("a_place_of_reg", ie.getA_place_of_reg());
        bind.put("n_place_of_reg", ie.getN_place_of_reg());
        bind.put("series_of_passport", ie.getSeries_of_passport());
        bind.put("number_of_passport", ie.getNumber_of_passport());
        bind.put("if_pdv", ie.isIf_pdv());
        bind.put("risk_group", ie.getRisk_group());
        bind.put("owner", ie.getOwner().toLowerCase());
        if (ie.getRntc().length() == 10) {
            bind.put("rntc", ie.getRntc());
            sql = "INSERT INTO individual_entrepreneur_table (contact_link,a_place_of_reg,n_place_of_reg,series_of_passport,number_of_passport," +
                    "if_pdv,risk_group,owner,rntc) " +
                    "VALUES (:contact_link,:a_place_of_reg,:n_place_of_reg,:series_of_passport,:number_of_passport," +
                    ":if_pdv,:risk_group,(SELECT id FROM user_table WHERE LOWER(username) =LOWER(:owner)),:rntc)";
        }

        SqlParameterSource paramSource = new MapSqlParameterSource(bind);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String[] columnNames = new String[]{"id"};

        this.namedParameterJdbcTemplate.update(sql, paramSource, keyHolder, columnNames);
        Map<String, Object> keys = keyHolder.getKeys();
        return (Long) keys.get("id");
    }
}
