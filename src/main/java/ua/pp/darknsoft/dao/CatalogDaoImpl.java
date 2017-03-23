package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.catalog.*;
import ua.pp.darknsoft.entity.*;

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
    private SelectCauseCatalog selectCauseCatalog;
    private SelectStructureCatalogByMyStatus selectStructureCatalogByMyStatus;
    private SelectGoodsTop selectGoodsTop;
    private SelectGoodsByTreemark selectGoodsByTreemark;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
        this.selectLocationType = new SelectLocationType(dataSource);
        this.selectLocationTop = new SelectLocationTop(dataSource);
        this.selectLocationByTreemark = new SelectLocationByTreemark(dataSource);
        this.selectParentLocationByTreemark = new SelectParentLocationByTreemark(dataSource);
        this.selectCauseCatalog = new SelectCauseCatalog(dataSource);
        this.selectStructureCatalogByMyStatus = new SelectStructureCatalogByMyStatus(dataSource);
        this.selectGoodsTop = new SelectGoodsTop(dataSource);
        this.selectGoodsByTreemark = new SelectGoodsByTreemark(dataSource);
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
    public List<BasicGroupOfGoodsCatalog> getGoodsTop(){
        return selectGoodsTop.execute();
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
            //downloc.add(lc);
        }
        return downloc;
    }
    @Override
    public List<BasicGroupOfGoodsCatalog> getGoodsByTreemark(String treemark, int level){
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("treemark", treemark.toLowerCase());
        paramMap.put("nlevel",level);
        List<BasicGroupOfGoodsCatalog> downloc = selectGoodsByTreemark.executeByNamedParam(paramMap);
        if(downloc.isEmpty()){
            BasicGroupOfGoodsCatalog lc = new BasicGroupOfGoodsCatalog();
            lc.setId(0); lc.setTreemark("0.0"); lc.setName("No data");
            //downloc.add(lc);
        }
        return downloc;
    }
    @Override
    public List<LocationCatalog> getParentLocationByTreemark(String treemark){
        Map<String, Object> bind = new HashMap<String, Object>();
        bind.put("treemark", treemark);
        return selectParentLocationByTreemark.executeByNamedParam(bind);
    }

    @Override
    public List<CauseCatalog> getCauseCatalogByType(short type){
        Map<String, Object> bind = new HashMap<String, Object>();
        bind.put("type", type);
        return selectCauseCatalog.executeByNamedParam(bind);
    }
    @Override
    public List<StructureCatalog> getMyStructureByMyStatus(String username){
        Map<String,String> bind = new HashMap<>(3);
        bind.put("user_link",username);
        return  selectStructureCatalogByMyStatus.executeByNamedParam(bind);
    }
}
