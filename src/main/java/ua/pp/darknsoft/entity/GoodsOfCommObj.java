package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 23.03.2017.
 */
public class GoodsOfCommObj {
    private long id;
    private long comm_obj_link;
    private int goods_catalog_link;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getComm_obj_link() {
        return comm_obj_link;
    }

    public void setComm_obj_link(long comm_obj_link) {
        this.comm_obj_link = comm_obj_link;
    }

    public int getGoods_catalog_link() {
        return goods_catalog_link;
    }

    public void setGoods_catalog_link(int goods_catalog_link) {
        this.goods_catalog_link = goods_catalog_link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
