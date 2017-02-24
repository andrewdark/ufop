package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.catalog.SelectLocationByTreemark;
import ua.pp.darknsoft.dao.crud.catalog.SelectLocationTop;
import ua.pp.darknsoft.dao.crud.catalog.SelectLocationType;
import ua.pp.darknsoft.dao.crud.catalog.SelectParentLocationByTreemark;
import ua.pp.darknsoft.entity.LocationCatalog;
import ua.pp.darknsoft.entity.LocationType;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.awt.SystemColor.text;

/**
 * Created by Andrew on 16.01.2017.
 */
@Service
@Scope(value="session",proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CatalogDaoImpl implements CatalogDao,Serializable{
    private DataSource dataSource;
    private SelectLocationType selectLocationType;
    private SelectLocationTop selectLocationTop;
    private SelectLocationByTreemark selectLocationByTreemark;
    private SelectParentLocationByTreemark selectParentLocationByTreemark;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
        this.selectLocationType = new SelectLocationType(dataSource);
        this.selectLocationTop = new SelectLocationTop(dataSource);
        this.selectLocationByTreemark = new SelectLocationByTreemark(dataSource);
        this.selectParentLocationByTreemark = new SelectParentLocationByTreemark(dataSource);
    }
    @Override
    public List<LocationType> getLocationType(){
        return selectLocationType.execute();
    }

    @Override
    public List<LocationCatalog> getLocationTop(){
        return selectLocationTop.execute();
    }

    @Override
    public List<LocationCatalog> getLocationByTreemark(String treemark, int level){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("treemark", treemark.toLowerCase());
        paramMap.put("nlevel",level);
        List<LocationCatalog> downloc = selectLocationByTreemark.executeByNamedParam(paramMap);
        if(downloc.isEmpty()){
            LocationCatalog lc = new LocationCatalog();
            lc.setId(0); lc.setLtree("0.0"); lc.setName("No data");
        }
        return downloc;
    }
    @Override
    public List<LocationCatalog> getParentLocationByTreemark(String treemark){
        Map<String, Object> bind = new HashMap<String, Object>();
        bind.put("treemark", treemark);
        return selectParentLocationByTreemark.executeByNamedParam(bind);
    }
}
