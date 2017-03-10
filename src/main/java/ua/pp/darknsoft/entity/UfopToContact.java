package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

/**
 * Created by Andrew on 09.03.2017.
 */
public class UfopToContact {
    private long id;
    private long ufop_link;
    private long contact_link;
    private Timestamp datereg;
    private int creator_link;

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

    public long getContact_link() {
        return contact_link;
    }

    public void setContact_link(long contact_link) {
        this.contact_link = contact_link;
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public int getCreator_link() {
        return creator_link;
    }

    public void setCreator_link(int creator_link) {
        this.creator_link = creator_link;
    }
}
