package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 27.02.2017.
 */
public class CauseCatalog {
    private int id;
    private short type;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
