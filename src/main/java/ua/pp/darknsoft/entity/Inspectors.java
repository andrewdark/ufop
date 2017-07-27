package ua.pp.darknsoft.entity;

import java.sql.Timestamp;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Andrew on 29.05.2017.
 */
public class Inspectors {
    private long id;
    private long check_event_link;
    private int user_link;
    private String user_name;
    private String structure_link;
    private String structure_name;
    private boolean accepted;
    private Timestamp datereg;
    private int creator_link;
    private String creator_name;

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

    public int getUser_link() {
        return user_link;
    }

    public void setUser_link(int user_link) {
        this.user_link = user_link;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = rejectHtml(user_name);
    }

    public String getStructure_link() {
        return structure_link;
    }

    public void setStructure_link(String structure_link) {
        this.structure_link = rejectHtml(structure_link);
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public int getCreator_link() {
        return creator_link;
    }

    public void setCreator_link(int creator_link) {
        this.creator_link = creator_link;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = rejectHtml(creator_name);
    }

    public String getStructure_name() {
        return structure_name;
    }

    public void setStructure_name(String structure_name) {
        this.structure_name = rejectHtml(structure_name);
    }
}
