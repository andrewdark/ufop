package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.ufop.InsertUfopReturnId;
import ua.pp.darknsoft.dao.crud.ufop.SelectUfopByCodeEquals;
import ua.pp.darknsoft.entity.Ufop;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 09.03.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UfopDaoImpl implements UfopDao, Serializable {
    private DataSource dataSource;
    private SelectUfopByCodeEquals selectUfopByCodeEquals;
    private InsertUfopReturnId insertUfopReturnId;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectUfopByCodeEquals = new SelectUfopByCodeEquals(dataSource);
        this.insertUfopReturnId = new InsertUfopReturnId(dataSource);
    }

    @Override
    public List<Ufop> searchUfopByCode(String ufop_code) {
        Map<String, String> bind = new HashMap<>();
        bind.put("ufop_code", ufop_code);
        return selectUfopByCodeEquals.executeByNamedParam(bind);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @Override
    public Ufop createUfop(Ufop ufop) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("ufop_is", ufop.getUfop_is());
        bind.put("ufop_name", ufop.getUfop_name());
        bind.put("ufop_code", ufop.getUfop_code());
        bind.put("a_place_of_reg", ufop.getA_place_of_reg());
        bind.put("n_place_of_reg", ufop.getN_place_of_reg());
        bind.put("f_place_of_reg", ufop.getF_place_of_reg());
        bind.put("b_place_of_reg", ufop.getB_place_of_reg());
        bind.put("creator_link", SecurityContextHolder.getContext().getAuthentication().getName().toString());
        bind.put("description", ufop.getDescription());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertUfopReturnId.updateByNamedParam(bind, keyHolder);
        ufop.setId(keyHolder.getKey().longValue());
        return ufop;
    }
}
