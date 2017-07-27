package ua.pp.darknsoft.entity;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Andrew on 29.03.2017.
 */
public class Lawsuits {
    private long id;
    private long check_event_link;
    private int filed_on_action;
    private String filed_date;
    private int result_link;
    private String description;
    private String sresult_link;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCheck_event_link() {
        return check_event_link;
    }

    public void setCheck_event_link(long check_event_link) {
        this.check_event_link = check_event_link;
    }

    public int getFiled_on_action() {
        return filed_on_action;
    }

    public void setFiled_on_action(int filed_on_action) {
        this.filed_on_action = filed_on_action;
    }

    public String getFiled_date() {
        return filed_date;
    }

    public void setFiled_date(String filed_date) {
        this.filed_date = rejectHtml(filed_date);
    }

    public int getResult_link() {
        return result_link;
    }

    public void setResult_link(int result_link) {
        this.result_link = result_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = rejectHtml(description);
    }

    public String getSresult_link() {
        return sresult_link;
    }

    public void setSresult_link(String sresult_link) {
        this.sresult_link = rejectHtml(sresult_link);
    }
}
