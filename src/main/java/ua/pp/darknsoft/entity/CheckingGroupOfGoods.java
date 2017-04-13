package ua.pp.darknsoft.entity;

/**
 * Created by Dark on 26.03.2017.
 */
public class CheckingGroupOfGoods {
    private long id;
    private long check_event_link;
    private String goods_catalog_link;
    private String s_goods_catalog_link;
    private boolean checking;
    private boolean additionalinformation;
    private int nav;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCheck_event_link() {
        return check_event_link;
    }

    public void setCheck_event_link(long check_event_link) {
        this.check_event_link = check_event_link;
    }

    public String getGoods_catalog_link() {
        return goods_catalog_link;
    }

    public void setGoods_catalog_link(String goods_catalog_link) {
        this.goods_catalog_link = goods_catalog_link;
    }

    public boolean isChecking() {
        return checking;
    }

    public void setChecking(boolean checking) {
        this.checking = checking;
    }

    public int getNav() { return nav; }

    public void setNav(int nav) { this.nav = nav; }

    public boolean isAdditionalinformation() {
        return additionalinformation;
    }

    public void setAdditionalinformation(boolean additionalinformation) {
        this.additionalinformation = additionalinformation;
    }

    public String getS_goods_catalog_link() {
        return s_goods_catalog_link;
    }

    public void setS_goods_catalog_link(String s_goods_catalog_link) {
        this.s_goods_catalog_link = s_goods_catalog_link;
    }
}
