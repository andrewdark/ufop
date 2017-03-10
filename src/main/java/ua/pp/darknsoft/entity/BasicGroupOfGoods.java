package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 09.03.2017.
 */
public class BasicGroupOfGoods {
    private int id;
    private String treemark;
    private String name;
    private short degree_of_a_risk_link;
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
        this.treemark = treemark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getDegree_of_a_risk_link() {
        return degree_of_a_risk_link;
    }

    public void setDegree_of_a_risk_link(short degree_of_a_risk_link) {
        this.degree_of_a_risk_link = degree_of_a_risk_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
