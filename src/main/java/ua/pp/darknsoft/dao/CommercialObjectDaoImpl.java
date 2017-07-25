package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.commercialobj.*;
import ua.pp.darknsoft.entity.CommercialObject;
import ua.pp.darknsoft.entity.CommercialObjectType;
import ua.pp.darknsoft.entity.GoodsOfCommObj;

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
    InsertCommercialObjectReturnId insertCommercialObjectReturnId;
    SelectCommObjByUfop_link selectCommObjByUfop_link;
    SelectCommObjById selectCommObjById;
    GetCommercialObjType getCommercialObjType;
    InsertGoodsToCommObj insertGoodsToCommObj;
    SelectCommObjGoodsByCommObj_link selectCommObjGoodsByCommObjlink;
    UpdateCommercialObjectReturnId updateCommercialObjectReturnId;
    DeleteGoodsByCommObjLink deleteGoodsByCommObjLink;
    SelectCommObjByAdressAndGroupOfGoods selectCommObjByAdressAndGroupOfGoods;
    SelectCommObjByAdressWIthOutGroupOfGoods selectCommObjByAdressWIthOutGroupOfGoods;


    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertCommercialObjectReturnId = new InsertCommercialObjectReturnId(dataSource);
        this.selectCommObjByUfop_link = new SelectCommObjByUfop_link(dataSource);
        this.selectCommObjById = new SelectCommObjById(dataSource);
        this.getCommercialObjType = new GetCommercialObjType(dataSource);
        this.insertGoodsToCommObj = new InsertGoodsToCommObj(dataSource);
        this.selectCommObjGoodsByCommObjlink = new SelectCommObjGoodsByCommObj_link(dataSource);
        this.updateCommercialObjectReturnId = new UpdateCommercialObjectReturnId(dataSource);
        this.deleteGoodsByCommObjLink = new DeleteGoodsByCommObjLink(dataSource);
        this.selectCommObjByAdressAndGroupOfGoods = new SelectCommObjByAdressAndGroupOfGoods(dataSource);
        this.selectCommObjByAdressWIthOutGroupOfGoods = new SelectCommObjByAdressWIthOutGroupOfGoods(dataSource);
    }

    @Override
    public CommercialObject createCommObj(CommercialObject commercialObject) {
        Map<String, Object> bind = new HashMap<String, Object>();
        bind.put("ufop_link", commercialObject.getUfop_link());
        bind.put("obj_type", commercialObject.getObj_type());
        bind.put("obj_name", commercialObject.getObj_name());
        bind.put("a_place_of_reg", commercialObject.getA_place_of_reg());
        bind.put("n_place_of_reg", commercialObject.getN_place_of_reg());
        bind.put("f_place_of_reg", commercialObject.getF_place_of_reg());
        bind.put("b_place_of_reg", commercialObject.getB_place_of_reg());
        bind.put("degree_risk_link",commercialObject.getDegree_risk_link());
        bind.put("description", commercialObject.getDescription());
        bind.put("creator_link", commercialObject.getCreator_link().toLowerCase());


        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertCommercialObjectReturnId.updateByNamedParam(bind, keyHolder);
        commercialObject.setId(keyHolder.getKey().longValue());
        return commercialObject;
    }

    @Override
    public CommercialObject updateCommObj(CommercialObject commercialObject) {
        Map<String, Object> bind = new HashMap<String, Object>();
        bind.put("id", commercialObject.getId());
        bind.put("ufop_link", commercialObject.getUfop_link());
        bind.put("obj_type", commercialObject.getObj_type());
        bind.put("obj_name", commercialObject.getObj_name());
        bind.put("a_place_of_reg", commercialObject.getA_place_of_reg());
        bind.put("n_place_of_reg", commercialObject.getN_place_of_reg());
        bind.put("f_place_of_reg", commercialObject.getF_place_of_reg());
        bind.put("b_place_of_reg", commercialObject.getB_place_of_reg());
        bind.put("degree_risk_link",commercialObject.getDegree_risk_link());
        bind.put("description", commercialObject.getDescription());
        bind.put("creator_link", commercialObject.getCreator_link().toLowerCase());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        updateCommercialObjectReturnId.updateByNamedParam(bind, keyHolder);
        commercialObject.setId(keyHolder.getKey().longValue());
        return commercialObject;
    }

    @Override
    public List<CommercialObjectType> getCommObjType() {
        return getCommercialObjType.execute();
    }

    @Override
    public List<CommercialObject> getCommObjByUfop_link(long ufop_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("ufop_link", ufop_link);
        return selectCommObjByUfop_link.executeByNamedParam(bind);
    }

    @Override
    public List<CommercialObject> getCommObjById(long id) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("id", id);
        return selectCommObjById.executeByNamedParam(bind);
    }
    @Override
    public List<CommercialObject> getCommObjByAdressAndGroupOfGoods(CommercialObject comobj, String goods_catalog_link,
                                                                    int total, int pageid) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("a_place_of_reg", comobj.getA_place_of_reg());
        if(comobj.getDegree_risk_link()==-1)bind.put("degree_risk_link", null);
        else bind.put("degree_risk_link", comobj.getDegree_risk_link());
        if(comobj.getObj_type()==-1) bind.put("obj_type", null);
        else bind.put("obj_type", comobj.getObj_type());
        if(goods_catalog_link.equals("-1"))bind.put("goods_catalog_link", null);
        else bind.put("goods_catalog_link", goods_catalog_link);
        bind.put("total", total);
        bind.put("pageid", pageid);
        return selectCommObjByAdressAndGroupOfGoods.executeByNamedParam(bind);
    }
    @Override
    public List<CommercialObject> getCommObjByAdressWithOutGroupOfGoods(CommercialObject comobj, int total, int pageid) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("a_place_of_reg", comobj.getA_place_of_reg());
        if(comobj.getDegree_risk_link()==-1)bind.put("degree_risk_link", null);
        else bind.put("degree_risk_link", comobj.getDegree_risk_link());
        if(comobj.getObj_type()==-1) bind.put("obj_type", null);
        else bind.put("obj_type", comobj.getObj_type());
        bind.put("total", total);
        bind.put("pageid", pageid);
        return selectCommObjByAdressWIthOutGroupOfGoods.executeByNamedParam(bind);
    }
    @Override
    public void addGoodsToCommObj(GoodsOfCommObj goods) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("comm_obj_link", goods.getComm_obj_link());
        bind.put("goods_catalog_link", goods.getGoods_catalog_link());
        insertGoodsToCommObj.updateByNamedParam(bind);
    }

    @Override
    public List<GoodsOfCommObj> getCommObjGoodsByCommObjlink(long comm_obj_link) {
        Map<String, Long> bind = new HashMap<>(3);
        bind.put("comm_obj_link", comm_obj_link);
        return selectCommObjGoodsByCommObjlink.executeByNamedParam(bind);
    }

    @Override
    public void deleteGoodsByCommObjLink(long comm_obj_link) {
        Map<String, Long> bind = new HashMap<String, Long>(3);
        bind.put("id", comm_obj_link);
        deleteGoodsByCommObjLink.updateByNamedParam(bind);
    }
}
