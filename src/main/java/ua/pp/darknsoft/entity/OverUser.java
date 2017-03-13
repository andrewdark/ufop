package ua.pp.darknsoft.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Dark on 11.01.2017.
 */
public class OverUser extends User implements Serializable{
    private WorkTime workTime;

    private String ct_fn;
    private String ct_ln;
    private String ct_pn;

    private String cct_name;

    public WorkTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(WorkTime workTime) {
        this.workTime = workTime;
    }

    public String getCt_fn() {
        return ct_fn;
    }

    public void setCt_fn(String ct_fn) {
        this.ct_fn = ct_fn;
    }

    public String getCt_ln() {
        return ct_ln;
    }

    public void setCt_ln(String ct_ln) {
        this.ct_ln = ct_ln;
    }

    public String getCt_pn() {
        return ct_pn;
    }

    public void setCt_pn(String ct_pn) {
        this.ct_pn = ct_pn;
    }

    public String getCct_name() {
        return cct_name;
    }

    public void setCct_name(String cct_name) {
        this.cct_name = cct_name;
    }
}
