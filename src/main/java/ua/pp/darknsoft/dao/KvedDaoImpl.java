package ua.pp.darknsoft.dao;

/**
 * Created by Andrew on 16.02.2017.
 */

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.kved.InsertEntrepreneursKveds;
import ua.pp.darknsoft.dao.crud.kved.SelectEntrepreneursKvedsByEntrepreneurLink;
import ua.pp.darknsoft.dao.crud.kved.SelectKvedCatalogByTreemark;
import ua.pp.darknsoft.dao.crud.kved.SelectKvedCatalogTop;
import ua.pp.darknsoft.entity.EntrepreneursKveds;
import ua.pp.darknsoft.entity.KvedCatalog;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class KvedDaoImpl implements KvedDao, Serializable {
    DataSource dataSource;
    InsertEntrepreneursKveds insertEntrepreneursKveds;
    SelectKvedCatalogTop selectKvedCatalogTop;
    SelectKvedCatalogByTreemark selectKvedCatalogByTreemark;
    SelectEntrepreneursKvedsByEntrepreneurLink selectEntrepreneursKvedsByEntrepreneurLink;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertEntrepreneursKveds = new InsertEntrepreneursKveds(dataSource);
        this.selectKvedCatalogTop = new SelectKvedCatalogTop(dataSource);
        this.selectKvedCatalogByTreemark = new SelectKvedCatalogByTreemark(dataSource);
        this.selectEntrepreneursKvedsByEntrepreneurLink = new SelectEntrepreneursKvedsByEntrepreneurLink(dataSource);
    }
    @PreAuthorize(value = "isAuthenticated()")
    @Override
    public void createEntrepreneursKveds(EntrepreneursKveds entrepreneursKveds) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("entrepreneur_link", entrepreneursKveds.getEntrepreneur_link());
        paramMap.put("kved_catalog_link", entrepreneursKveds.getKved_catalog_link());
        paramMap.put("owner", entrepreneursKveds.getOwner().toLowerCase());

        insertEntrepreneursKveds.updateByNamedParam(paramMap);
    }

    @Override
    public List<KvedCatalog> getKvedCatalogTop() {
        return selectKvedCatalogTop.execute();
    }

    @Override
    public List<KvedCatalog> getKvedCatalogByTreemark(String treemark, int level) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("treemark", treemark);
        paramMap.put("nlevel", level);
        List<KvedCatalog> downloc = selectKvedCatalogByTreemark.executeByNamedParam(paramMap);
        if (downloc.isEmpty()) {
            KvedCatalog lc = new KvedCatalog();
            lc.setId(0);
            lc.setTreemark("0.0");
            lc.setName("No data");
        }
        return downloc;
    }
@Override
    public List<EntrepreneursKveds> getEntrepreneursKvedsByEntrepreneurLink(long e_link){
        Map<String, Long> bind = new HashMap<>();
        bind.put("entrepreneur_link",e_link);
        return selectEntrepreneursKvedsByEntrepreneurLink.executeByNamedParam(bind);

    }
}
