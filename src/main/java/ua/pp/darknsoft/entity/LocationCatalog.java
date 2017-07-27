package ua.pp.darknsoft.entity;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Andrew on 30.01.2017.
 */
public class LocationCatalog {
    private int id;
    private String ltree;
    private String name;
    private int type;
    private String stype;
    private String note;
    private int nlevel; //depth

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
        this.ltree = rejectHtml(ltree);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = rejectHtml(name);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStype() { return stype; }

    public void setStype(String stype) { this.stype = rejectHtml(stype); }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = rejectHtml(note);
    }

    public int getNlevel() { return nlevel; }

    public void setNlevel(int nlevel) { this.nlevel = nlevel; }
}
