package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

/**
 * Created by Andrew on 27.02.2017.
 */
public class WorkTime {
    private long id;
    private int user_link;
    private short type_of_action;
    private int cause_link;
    private String s_cause_link;
    private Timestamp datereg;
    private boolean accepted;
    private int user_accepted_link;
    private String s_user_accepted_link;
    private Timestamp dateaccept;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUser_link() {
        return user_link;
    }

    public void setUser_link(int user_link) {
        this.user_link = user_link;
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

    public int getUser_accepted_link() {
        return user_accepted_link;
    }

    public void setUser_accepted_link(int user_accepted_link) {
        this.user_accepted_link = user_accepted_link;
    }

    public Timestamp getDateaccept() {
        return dateaccept;
    }

    public void setDateaccept(Timestamp dateaccept) {
        this.dateaccept = dateaccept;
    }

    public String getS_cause_link() {
        return s_cause_link;
    }

    public void setS_cause_link(String s_cause_link) {
        this.s_cause_link = s_cause_link;
    }

    public String getS_user_accepted_link() {
        return s_user_accepted_link;
    }

    public void setS_user_accepted_link(String s_user_accepted_link) {
        this.s_user_accepted_link = s_user_accepted_link;
    }
}
