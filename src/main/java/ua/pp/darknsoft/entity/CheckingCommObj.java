package ua.pp.darknsoft.entity;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Dark on 26.03.2017.
 */
public class CheckingCommObj {
    private long id;
    private long check_event_link;
    private long comm_obj_link;
    private boolean checking;
    private String comm_obj_name;

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

    public long getComm_obj_link() {
        return comm_obj_link;
    }

    public void setComm_obj_link(long comm_obj_link) {
        this.comm_obj_link = comm_obj_link;
    }

    public boolean isChecking() {
        return checking;
    }

    public void setChecking(boolean checking) {
        this.checking = checking;
    }

    public String getComm_obj_name() { return comm_obj_name; }

    public void setComm_obj_name(String comm_obj_name) { this.comm_obj_name = rejectHtml(comm_obj_name); }
}
