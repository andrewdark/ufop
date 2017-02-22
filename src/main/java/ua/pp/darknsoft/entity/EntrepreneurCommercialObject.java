package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

/**
 * Created by Andrew on 30.01.2017.
 */
public class EntrepreneurCommercialObject {
    private long id;
    private long ufop_link;
    private int obj_type;
    private String obj_name;
    private String a_obj_location;
    private String n_obj_location;
    private Timestamp datereg;
    private String owner;
    private String description;

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

    public String getA_obj_location() {
        return a_obj_location;
    }

    public void setA_obj_location(String a_obj_location) {
        this.a_obj_location = a_obj_location;
    }

    public String getN_obj_location() {
        return n_obj_location;
    }

    public void setN_obj_location(String n_obj_location) {
        this.n_obj_location = n_obj_location;
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
