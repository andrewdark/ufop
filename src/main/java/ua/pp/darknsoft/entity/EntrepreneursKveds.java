package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

/**
 * Created by Andrew on 15.02.2017.
 */
public class EntrepreneursKveds {
    private long id;
    private long entrepreneur_link;
    private String kved_catalog_link;
    private String kved_catalog_label;
    private String kved_catalog_name;
    private Timestamp datereg;
    private String owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEntrepreneur_link() {
        return entrepreneur_link;
    }

    public void setEntrepreneur_link(long entrepreneur_link) {
        this.entrepreneur_link = entrepreneur_link;
    }

    public String getKved_catalog_link() { return kved_catalog_link; }

    public void setKved_catalog_link(String kved_catalog_link) {
        this.kved_catalog_link = kved_catalog_link;
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

    public String getKved_catalog_label() {
        return kved_catalog_label;
    }

    public void setKved_catalog_label(String kved_catalog_label) {
        this.kved_catalog_label = kved_catalog_label;
    }

    public String getKved_catalog_name() {
        return kved_catalog_name;
    }

    public void setKved_catalog_name(String kved_catalog_name) {
        this.kved_catalog_name = kved_catalog_name;
    }
}
