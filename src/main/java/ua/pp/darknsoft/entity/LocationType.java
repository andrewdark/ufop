package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 16.01.2017.
 */
public class LocationType {
    int id;
    String type;
    String note;
    short level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public short getLevel() {
        return level;
    }

    public void setLevel(short level) {
        this.level = level;
    }
}
