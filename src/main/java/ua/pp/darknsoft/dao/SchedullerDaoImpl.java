package ua.pp.darknsoft.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.scheduller.KickOutUserInsert;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew on 11.05.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SchedullerDaoImpl implements SchedullerDao, Serializable {
    private KickOutUserInsert kickOutUserInsert;
    private DataSource dataSearch;

    @Resource(name = "dataSource")
    public void setDataSearch(DataSource dataSearch) {
        this.dataSearch = dataSearch;
        this.kickOutUserInsert = new KickOutUserInsert(dataSearch);
    }

    @Override
    public void addKickOutUserInsert(String log_text) {
        Map<String, String> bind = new HashMap<>();
        bind.put("log_text", log_text);
        kickOutUserInsert.updateByNamedParam(bind);
    }
}
