package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.commercialobj.InsertCommercialObject;
import ua.pp.darknsoft.dao.crud.commercialobj.SelectCommercialObjectByUfop;
import ua.pp.darknsoft.entity.CommercialObject;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrew on 20.02.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CommercialObjectDaoImpl implements CommercialObjectDao, Serializable {
    DataSource dataSource;
    InsertCommercialObject insertCommercialObject;
    SelectCommercialObjectByUfop selectCommercialObjectByUfop;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertCommercialObject = new InsertCommercialObject(dataSource);
        this.selectCommercialObjectByUfop = new SelectCommercialObjectByUfop(dataSource);
    }
@Override
    public void createCommObj(CommercialObject commercialObject) {
        Map<String, Object> bind = new HashMap<String, Object>();
        bind.put("ufop_link", commercialObject.getUfop_link());
        bind.put("obj_type", commercialObject.getObj_type());
        bind.put("obj_name", commercialObject.getObj_name());
        bind.put("a_obj_location", commercialObject.getA_obj_location());
        bind.put("n_obj_location", commercialObject.getN_obj_location());
        bind.put("owner", commercialObject.getOwner());

        insertCommercialObject.updateByNamedParam(bind);
    }

}
