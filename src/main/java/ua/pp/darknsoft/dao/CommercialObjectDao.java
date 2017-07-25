package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.BasicGroupOfGoodsCatalog;
import ua.pp.darknsoft.entity.CommercialObject;
import ua.pp.darknsoft.entity.CommercialObjectType;
import ua.pp.darknsoft.entity.GoodsOfCommObj;

import java.util.List;

/**
 * Created by Andrew on 20.02.2017.
 */
public interface CommercialObjectDao {
    CommercialObject createCommObj(CommercialObject commercialObject);

    CommercialObject updateCommObj(CommercialObject commercialObject);

    List<CommercialObjectType> getCommObjType();

    List<CommercialObject> getCommObjByUfop_link(long ufop_link);

    List<CommercialObject> getCommObjById(long id);

    List<CommercialObject> getCommObjByAdressAndGroupOfGoods(CommercialObject comobj, String goods_catalog_link,
                                                             int total, int pageid);

    List<CommercialObject> getCommObjByAdressWithOutGroupOfGoods(CommercialObject comobj, int total, int pageid);

    void addGoodsToCommObj(GoodsOfCommObj goods);

    List<GoodsOfCommObj> getCommObjGoodsByCommObjlink(long comm_obj_link);

    void deleteGoodsByCommObjLink(long comm_obj_link);
}
