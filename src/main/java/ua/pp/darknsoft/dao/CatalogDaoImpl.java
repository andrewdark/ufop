package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.catalog.SelectLocationByTreemark;
import ua.pp.darknsoft.dao.crud.catalog.SelectLocationTop;
import ua.pp.darknsoft.dao.crud.catalog.SelectLocationType;
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

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
        this.selectLocationType = new SelectLocationType(dataSource);
        this.selectLocationTop = new SelectLocationTop(dataSource);
    }
    @Override
    public List<LocationType> getLocationType(){
        return selectLocationType.execute();
    }

    @Override
    public List<LocationCatalog> getLocationTop(){
        return selectLocationTop.execute();
    }

    public List<LocationCatalog> getLocationByTreemark(String treemark){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("treemark", treemark.toLowerCase());

        return selectLocationByTreemark.executeByNamedParam(paramMap);
    }
}
