package ua.pp.darknsoft.dao;

/**
 * Created by Andrew on 16.02.2017.
 */

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.kved.*;
import ua.pp.darknsoft.entity.KvedCatalog;
import ua.pp.darknsoft.entity.KvedsUfop;

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
    InsertKvedsUfop insertKvedsUfop;
    SelectKvedCatalogTop selectKvedCatalogTop;
    SelectKvedCatalogByTreemark selectKvedCatalogByTreemark;
    SelectEntrepreneursKvedsByEntrepreneurLink selectEntrepreneursKvedsByEntrepreneurLink;
    DeleteKvedsByUfopLink deleteKvedsByUfopLink;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertKvedsUfop = new InsertKvedsUfop(dataSource);
        this.selectKvedCatalogTop = new SelectKvedCatalogTop(dataSource);
        this.selectKvedCatalogByTreemark = new SelectKvedCatalogByTreemark(dataSource);
        this.selectEntrepreneursKvedsByEntrepreneurLink = new SelectEntrepreneursKvedsByEntrepreneurLink(dataSource);
        this.deleteKvedsByUfopLink = new DeleteKvedsByUfopLink(dataSource);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @Override
    public void createEntrepreneursKveds(KvedsUfop kvedsUfop) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("ufop_link", kvedsUfop.getUfop_link());
        paramMap.put("kved_catalog_link", kvedsUfop.getKved_catalog_link());
        paramMap.put("creator_link", kvedsUfop.getCreator_link().toLowerCase());

        insertKvedsUfop.updateByNamedParam(paramMap);
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
    public List<KvedsUfop> getKvedsByUfopLink(long ufop_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("ufop_link", ufop_link);
        return selectEntrepreneursKvedsByEntrepreneurLink.executeByNamedParam(bind);

    }

    @Override
    public void deleteKvedsByUfopLink(long id) {
        Map<String, Long> bind = new HashMap<>(3);
        bind.put("id", id);
        deleteKvedsByUfopLink.updateByNamedParam(bind);

    }
}
