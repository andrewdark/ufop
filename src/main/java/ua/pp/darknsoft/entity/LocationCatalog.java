package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 30.01.2017.
 */
public class LocationCatalog {
    private int id;
    private String ltree;
    private String name;
    private int type;
    private String note;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLtree() {
        return ltree;
    }

    public void setLtree(String ltree) {
        this.ltree = ltree;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
