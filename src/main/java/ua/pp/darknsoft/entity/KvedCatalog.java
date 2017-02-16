package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 30.01.2017.
 */
public class KvedCatalog {
    private int id;
    private String ltree;
    private String label;
    private String name;
    private String description;
    private int nlevel;

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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNlevel() { return nlevel; }

    public void setNlevel(int nlevel) { this.nlevel = nlevel; }
}
