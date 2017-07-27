package ua.pp.darknsoft.entity;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Andrew on 08.02.2017.
 */
public class DegreeRisk {
    private int id;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = rejectHtml(title);
    }
}
