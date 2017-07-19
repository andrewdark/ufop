package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.event.search.SelectCheckEventUniversalByPaginator;
import ua.pp.darknsoft.entity.CheckEvent;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 19.07.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SearchUniversalImpl implements SearchUniversalDao, Serializable {
    private DataSource dataSource;
    private SelectCheckEventUniversalByPaginator selectCheckEventUniversalByPaginator;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
        this.selectCheckEventUniversalByPaginator = new SelectCheckEventUniversalByPaginator(dataSource);
    }
    @Override
    public List<CheckEvent> checkEventList(CheckEvent checkEvent, int total, int pageid){
        Map<String, Object> bind = new HashMap<>();
        if (checkEvent.getId()==0)bind.put("id",null);
        else bind.put("id",checkEvent.getId());
        bind.put("structure_catalog_link",checkEvent.getStructure_catalog_link());
        bind.put("total", total);
        bind.put("pageid", pageid);
        return selectCheckEventUniversalByPaginator.executeByNamedParam(bind);
    }
}
