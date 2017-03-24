package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.commercialobj.GetCommercialObjType;
import ua.pp.darknsoft.dao.crud.commercialobj.InsertCommercialObject;
import ua.pp.darknsoft.dao.crud.commercialobj.SelectCommObjByUfop_link;
import ua.pp.darknsoft.entity.CommercialObject;
import ua.pp.darknsoft.entity.CommercialObjectType;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 20.02.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CommercialObjectDaoImpl implements CommercialObjectDao, Serializable {
    DataSource dataSource;
    InsertCommercialObject insertCommercialObject;
    SelectCommObjByUfop_link selectCommObjByUfop_link;
    GetCommercialObjType getCommercialObjType;


    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertCommercialObject = new InsertCommercialObject(dataSource);
        this.selectCommObjByUfop_link = new SelectCommObjByUfop_link(dataSource);
        this.getCommercialObjType = new GetCommercialObjType(dataSource);
    }

    @Override
    public void createCommObj(CommercialObject entrepreneurCommercialObject) {
        Map<String, Object> bind = new HashMap<String, Object>();
        bind.put("ufop_link", entrepreneurCommercialObject.getUfop_link());
        bind.put("obj_type", entrepreneurCommercialObject.getObj_type());
        bind.put("obj_name", entrepreneurCommercialObject.getObj_name());
        bind.put("a_obj_location", entrepreneurCommercialObject.getA_place_of_reg());
        bind.put("n_obj_location", entrepreneurCommercialObject.getN_place_of_reg());
        bind.put("owner", entrepreneurCommercialObject.getCreator_link().toLowerCase());

        insertCommercialObject.updateByNamedParam(bind);
    }

    @Override
    public List<CommercialObjectType> getCommObjType() {
        return getCommercialObjType.execute();
    }

    @Override
    public List<CommercialObject> getCommObjEntrepreneurByUfop_link(long ufop_link){
        Map<String,Long> bind = new HashMap<>();
        bind.put("ufop_link",ufop_link);
        return selectCommObjByUfop_link.executeByNamedParam(bind);
    }

}
