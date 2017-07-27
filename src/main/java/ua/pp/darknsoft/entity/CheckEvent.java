package ua.pp.darknsoft.entity;

import java.sql.Timestamp;
import static ua.pp.darknsoft.support.StaticMethod.*;

/**
 * Created by Dark on 26.03.2017.
 */
public class CheckEvent {
    private long id;
    private long ufop_link;
    private String event_number;
    private String event_date_begin;
    private String event_date_end;
    private int check_type;
    private int check_violation;
    private String event_result;
    private int check_sampling;
    private int result_sampling;
    private String creator_link;
    private int structure_catalog_link;
    private String structure_catalog_name;
    private Timestamp datereg;
    private int nav;
    private boolean checking;

    public int getNav() {
        return nav;
    }

    public void setNav(int nav) {
        this.nav = nav;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUfop_link() {
        return ufop_link;
    }

    public void setUfop_link(long ufop_link) {
        this.ufop_link = ufop_link;
    }

    public String getEvent_date_begin() {
        return event_date_begin;
    }

    public void setEvent_date_begin(String event_date_begin) {
        if (event_date_begin.isEmpty()) this.event_date_begin = "0001-01-01";
        else this.event_date_begin = rejectHtml(event_date_begin);
    }

    public String getEvent_date_end() {
        return event_date_end;
    }

    public void setEvent_date_end(String event_date_end) {
        if (event_date_end.isEmpty()) this.event_date_end = "0001-01-01";
        else this.event_date_end = rejectHtml(event_date_end);
    }

    public int getCheck_type() {
        return check_type;
    }

    public void setCheck_type(int check_type) {
        this.check_type = check_type;
    }

    public int getCheck_violation() {
        return check_violation;
    }

    public void setCheck_violation(int check_violation) {
        this.check_violation = check_violation;
    }

    public String getEvent_result() {
        return event_result;
    }

    public void setEvent_result(String event_result) {
        this.event_result = rejectHtml(event_result);
    }

    public int getCheck_sampling() {
        return check_sampling;
    }

    public void setCheck_sampling(int check_sampling) {
        this.check_sampling = check_sampling;
    }

    public int getResult_sampling() {
        return result_sampling;
    }

    public void setResult_sampling(int result_sampling) {
        this.result_sampling = result_sampling;
    }

    public String getCreator_link() {
        return creator_link;
    }

    public void setCreator_link(String creator_link) {
        this.creator_link = rejectHtml(creator_link);
    }

    public int getStructure_catalog_link() {
        return structure_catalog_link;
    }

    public void setStructure_catalog_link(int structure_catalog_link) {
        this.structure_catalog_link = structure_catalog_link;
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public String getEvent_number() {
        return event_number;
    }

    public void setEvent_number(String event_number) {
        this.event_number = rejectHtml(event_number);
    }

    public boolean isChecking() { return checking; }

    public void setChecking(boolean checking) { this.checking = checking; }

    public String getStructure_catalog_name() {
        return structure_catalog_name;
    }

    public void setStructure_catalog_name(String structure_catalog_name) {
        this.structure_catalog_name = rejectHtml(structure_catalog_name);
    }
}
