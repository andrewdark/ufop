package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.commercialobj.GetCommercialObjType;
import ua.pp.darknsoft.dao.crud.commercialobj.InsertCommercialObject;
import ua.pp.darknsoft.dao.crud.commercialobj.SelectCommObjEntrepreneurByUfop_link;
import ua.pp.darknsoft.entity.CommercialObjectType;
import ua.pp.darknsoft.entity.EntrepreneurCommercialObject;

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
    SelectCommObjEntrepreneurByUfop_link selectCommObjEntrepreneurByUfop_link;
    GetCommercialObjType getCommercialObjType;


    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertCommercialObject = new InsertCommercialObject(dataSource);
        this.selectCommObjEntrepreneurByUfop_link = new SelectCommObjEntrepreneurByUfop_link(dataSource);
        this.getCommercialObjType = new GetCommercialObjType(dataSource);
    }

    @Override
    public void createCommObj(EntrepreneurCommercialObject entrepreneurCommercialObject) {
        Map<String, Object> bind = new HashMap<String, Object>();
        bind.put("ufop_link", entrepreneurCommercialObject.getUfop_link());
        bind.put("obj_type", entrepreneurCommercialObject.getObj_type());
        bind.put("obj_name", entrepreneurCommercialObject.getObj_name());
        bind.put("a_obj_location", entrepreneurCommercialObject.getA_obj_location());
        bind.put("n_obj_location", entrepreneurCommercialObject.getN_obj_location());
        bind.put("owner", entrepreneurCommercialObject.getOwner().toLowerCase());

        insertCommercialObject.updateByNamedParam(bind);
    }

    @Override
    public List<CommercialObjectType> getCommObjType() {
        return getCommercialObjType.execute();
    }

    @Override
    public List<EntrepreneurCommercialObject> getCommObjEntrepreneurByUfop_link(long ufop_link){
        Map<String,Long> bind = new HashMap<>();
        bind.put("ufop_link",ufop_link);
        return selectCommObjEntrepreneurByUfop_link.executeByNamedParam(bind);
    }

}
