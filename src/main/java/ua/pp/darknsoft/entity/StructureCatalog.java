package ua.pp.darknsoft.entity;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Andrew on 27.02.2017.
 */
public class StructureCatalog {
    private int id;
    private String treemark;
    private String name;
    private String description;

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
}
