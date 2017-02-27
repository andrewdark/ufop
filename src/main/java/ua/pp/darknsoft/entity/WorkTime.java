package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

/**
 * Created by Andrew on 27.02.2017.
 */
public class WorkTime {
    private long id;
    private int str_org_link;
    private short type_of_action;
    private int cause_link;
    private Timestamp datereg;
    private boolean accepted;
    private int str_org_parent_accepted_link;
    private Timestamp dateaccept;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStr_org_link() {
        return str_org_link;
    }

    public void setStr_org_link(int str_org_link) {
        this.str_org_link = str_org_link;
    }

    public short getType_of_action() {
        return type_of_action;
    }

    public void setType_of_action(short type_of_action) {
        this.type_of_action = type_of_action;
    }

    public int getCause_link() {
        return cause_link;
    }

    public void setCause_link(int cause_link) {
        this.cause_link = cause_link;
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public int getStr_org_parent_accepted_link() {
        return str_org_parent_accepted_link;
    }

    public void setStr_org_parent_accepted_link(int str_org_parent_accepted_link) {
        this.str_org_parent_accepted_link = str_org_parent_accepted_link;
    }

    public Timestamp getDateaccept() {
        return dateaccept;
    }

    public void setDateaccept(Timestamp dateaccept) {
        this.dateaccept = dateaccept;
    }
}
