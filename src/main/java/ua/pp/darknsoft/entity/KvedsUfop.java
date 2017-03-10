package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

/**
 * Created by Andrew on 09.03.2017.
 */
public class KvedsUfop {
    private long id;
    private long ufop_link;
    private String kved_catalog_link;
    private Timestamp datereg;
    private String creator_link;
    private String Kved_catalog_label;
    private String Kved_catalog_name;

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

    public String getKved_catalog_link() {
        return kved_catalog_link;
    }

    public void setKved_catalog_link(String kved_catalog_link) {
        this.kved_catalog_link = kved_catalog_link;
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

    public String getKved_catalog_label() {
        return Kved_catalog_label;
    }

    public void setKved_catalog_label(String kved_catalog_label) {
        Kved_catalog_label = kved_catalog_label;
    }

    public String getKved_catalog_name() {
        return Kved_catalog_name;
    }

    public void setKved_catalog_name(String kved_catalog_name) {
        Kved_catalog_name = kved_catalog_name;
    }
}
