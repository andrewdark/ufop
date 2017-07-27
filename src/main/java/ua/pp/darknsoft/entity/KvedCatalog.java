package ua.pp.darknsoft.entity;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Andrew on 30.01.2017.
 */
public class KvedCatalog {
    private int id;
    private String treemark;
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

    public String getTreemark() {
        return treemark;
    }

    public void setTreemark(String treemark) {
        this.treemark = rejectHtml(treemark);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = rejectHtml(label);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = rejectHtml(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = rejectHtml(description);
    }

    public int getNlevel() { return nlevel; }

    public void setNlevel(int nlevel) { this.nlevel = nlevel; }
}
