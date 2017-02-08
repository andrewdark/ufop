package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

/**
 * Created by Andrew on 08.02.2017.
 */
public class KvedsIndividual {
    private long id;
    private long link_individual;
    private long link_kved_catalog;
    private Timestamp datereg;
    private int owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLink_individual() {
        return link_individual;
    }

    public void setLink_individual(long link_individual) {
        this.link_individual = link_individual;
    }

    public long getLink_kved_catalog() {
        return link_kved_catalog;
    }

    public void setLink_kved_catalog(long link_kved_catalog) {
        this.link_kved_catalog = link_kved_catalog;
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
