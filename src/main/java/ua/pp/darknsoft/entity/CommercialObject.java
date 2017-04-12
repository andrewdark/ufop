package ua.pp.darknsoft.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Andrew on 09.03.2017.
 */
public class CommercialObject {
    private long id;
    private long ufop_link;
    private int obj_type;
    private String obj_name;
    private String a_place_of_reg;
    private String n_place_of_reg;
    private String f_place_of_reg;
    private String b_place_of_reg;
    private int degree_risk_link;
    private String s_degree_risk_link;
    private Timestamp datereg;
    private String creator_link;
    private String description;
    private String s_obj_type;
    private List<LocationCatalog> locationCatalog;
    private boolean additionalinformation;
    private int nav;
    private List<GoodsOfCommObj> goodsList;

    public int getDegree_risk_link() { return degree_risk_link; }

    public void setDegree_risk_link(int degree_risk_link) { this.degree_risk_link = degree_risk_link; }

    public String getS_degree_risk_link() { return s_degree_risk_link; }

    public void setS_degree_risk_link(String s_degree_risk_link) { this.s_degree_risk_link = s_degree_risk_link; }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUfop_link() {
        return ufop_link;
    }

    public void setUfop_link(long ufop_link) {
        this.ufop_link = ufop_link;
    }

    public int getObj_type() {
        return obj_type;
    }

    public void setObj_type(int obj_type) {
        this.obj_type = obj_type;
    }

    public String getObj_name() {
        return obj_name;
    }

    public void setObj_name(String obj_name) {
        this.obj_name = obj_name;
    }

    public String getA_place_of_reg() {
        return a_place_of_reg;
    }

    public void setA_place_of_reg(String a_place_of_reg) {
        this.a_place_of_reg = a_place_of_reg;
    }

    public String getN_place_of_reg() {
        return n_place_of_reg;
    }

    public void setN_place_of_reg(String n_place_of_reg) {
        this.n_place_of_reg = n_place_of_reg;
    }

    public String getF_place_of_reg() {
        return f_place_of_reg;
    }

    public void setF_place_of_reg(String f_place_of_reg) {
        this.f_place_of_reg = f_place_of_reg;
    }

    public String getB_place_of_reg() {
        return b_place_of_reg;
    }

    public void setB_place_of_reg(String b_place_of_reg) {
        this.b_place_of_reg = b_place_of_reg;
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public String getCreator_link() {
        return creator_link;
    }

    public void setCreator_link(String creator_link) {
        this.creator_link = creator_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getS_obj_type() {
        return s_obj_type;
    }

    public void setS_obj_type(String s_obj_type) {
        this.s_obj_type = s_obj_type;
    }

    public List<LocationCatalog> getLocationCatalog() {
        return locationCatalog;
    }

    public void setLocationCatalog(List<LocationCatalog> locationCatalog) {
        this.locationCatalog = locationCatalog;
    }

    public boolean isAdditionalinformation() {
        return additionalinformation;
    }

    public void setAdditionalinformation(boolean additionalinformation) {
        this.additionalinformation = additionalinformation;
    }

    public List<GoodsOfCommObj> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<GoodsOfCommObj> goodsList) {
        this.goodsList = goodsList;
    }

    public int getNav() {
        return nav;
    }

    public void setNav(int nav) {
        this.nav = nav;
    }
}
