package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.catalog.SelectLocationType;
import ua.pp.darknsoft.entity.LocationType;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Andrew on 16.01.2017.
 */
@Service
@Scope(value="session",proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CatalogDaoImpl implements CatalogDao,Serializable{
    private DataSource dataSource;
    private SelectLocationType selectLocationType;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource){
        this.dataSource=dataSource;
        this.selectLocationType = new SelectLocationType(dataSource);
    }
    @Override
    public List<LocationType> getLocationType(){
        return selectLocationType.execute();
    }
}
