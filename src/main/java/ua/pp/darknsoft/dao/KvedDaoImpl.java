package ua.pp.darknsoft.dao;

/**
 * Created by Andrew on 16.02.2017.
 */

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.kved.InsertKvedsEntrepreneur;
import ua.pp.darknsoft.dao.crud.kved.SelectKvedByTreemark;
import ua.pp.darknsoft.dao.crud.kved.SelectKvedTop;
import ua.pp.darknsoft.entity.EntrepreneursKveds;
import ua.pp.darknsoft.entity.KvedCatalog;
import ua.pp.darknsoft.entity.LocationCatalog;
import ua.pp.darknsoft.entity.User;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class KvedDaoImpl implements KvedDao, Serializable{
    DataSource dataSource;
    InsertKvedsEntrepreneur insertKvedsEntrepreneur;
    SelectKvedTop selectKvedTop;
    SelectKvedByTreemark selectKvedByTreemark;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertKvedsEntrepreneur = new InsertKvedsEntrepreneur(dataSource);
        this.selectKvedTop = new SelectKvedTop(dataSource);
        this.selectKvedByTreemark = new SelectKvedByTreemark(dataSource);
    }
    @Override
    public void createEntrepreneursKveds(EntrepreneursKveds entrepreneursKveds) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("entrepreneur_link", entrepreneursKveds.getEntrepreneur_link());
        paramMap.put("kved_catalog_link", entrepreneursKveds.getKved_catalog_link());
        paramMap.put("owner", entrepreneursKveds.getOwner());

        insertKvedsEntrepreneur.updateByNamedParam(paramMap);
    }

    @Override
    public List<KvedCatalog> getKvedTop(){
        return selectKvedTop.execute();
    }

    @Override
    public List<KvedCatalog> getKvedByTreemark(String treemark, int level){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("treemark", treemark);
        paramMap.put("nlevel",level);
        List<KvedCatalog> downloc = selectKvedByTreemark.executeByNamedParam(paramMap);
        if(downloc.isEmpty()){
            KvedCatalog lc = new KvedCatalog();
            lc.setId(0); lc.setTreemark("0.0"); lc.setName("No data");
        }
        return downloc;
    }



}
