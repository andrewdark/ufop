package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

/**
 * Created by Andrew on 15.02.2017.
 */
public class EntrepreneursKveds {
    private long id;
    private long entrepreneur_link;
    private long kved_catalog_link;
    private Timestamp datereg;
    private int owner;

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

    public long getKved_catalog_link() { return kved_catalog_link; }

    public void setKved_catalog_link(long kved_catalog_link) {
        this.kved_catalog_link = kved_catalog_link;
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
