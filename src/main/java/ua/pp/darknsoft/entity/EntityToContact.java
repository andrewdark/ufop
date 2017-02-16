package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 16.02.2017.
 */
public class EntityToContact {
    private long id;
    private long entity_link;
    private long contact_link;
    private String datereg;
    private int owner;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEntity_link() {
        return entity_link;
    }

    public void setEntity_link(long entity_link) {
        this.entity_link = entity_link;
    }

    public long getContact_link() {
        return contact_link;
    }

    public void setContact_link(long contact_link) {
        this.contact_link = contact_link;
    }

    public String getDatereg() {
        return datereg;
    }

    public void setDatereg(String datereg) {
        this.datereg = datereg;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }
}
