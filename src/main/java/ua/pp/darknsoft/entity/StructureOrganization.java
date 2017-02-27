package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

/**
 * Created by Andrew on 27.02.2017.
 */
public class StructureOrganization {
    private int id;
    private String treemark;
    private int structure_catalog_link;
    private long user_link;
    private int spokesman_link;
    private Timestamp datereg;
    private String owner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTreemark() {
        return treemark;
    }

    public void setTreemark(String treemark) {
        this.treemark = treemark;
    }

    public int getStructure_catalog_link() {
        return structure_catalog_link;
    }

    public void setStructure_catalog_link(int structure_catalog_link) {
        this.structure_catalog_link = structure_catalog_link;
    }

    public long getUser_link() {
        return user_link;
    }

    public void setUser_link(long user_link) {
        this.user_link = user_link;
    }

    public int getSpokesman_link() {
        return spokesman_link;
    }

    public void setSpokesman_link(int spokesman_link) {
        this.spokesman_link = spokesman_link;
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
}
