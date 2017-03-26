package ua.pp.darknsoft.entity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Dark on 26.03.2017.
 */
public class CheckEvent {
    private long id;
    private long ufop_link;
    private String event_date_begin;
    private String event_date_end;
    private int check_type;
    private int check_violation;
    private String event_result;
    private int check_sampling;
    private int result_sampling;
    private String creator_link;
    private int structure_catalog_link;
    private Timestamp datereg;
    private List<CheckingCommObj> commobj_list;
    private List<CheckingGroupOfGoods> groupofgoods_list;
    private List<OffenseArticles> offensearticles_list;

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
        if(event_date_begin.isEmpty()) this.event_date_begin = "0001-01-01";
        else this.event_date_begin = event_date_begin;
    }

    public String getEvent_date_end() {
        return event_date_end;
    }

    public void setEvent_date_end(String event_date_end) {
        if(event_date_end.isEmpty()) this.event_date_end = "0001-01-01";
        else this.event_date_end = event_date_end;
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
        this.event_result = event_result;
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
        this.creator_link = creator_link;
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
    public List<CheckingCommObj> getCommobj_list() {
        return commobj_list;
    }

    public void setCommobj_list(List<CheckingCommObj> commobj_list) {
        this.commobj_list = commobj_list;
    }

    public List<CheckingGroupOfGoods> getGroupofgoods_list() {
        return groupofgoods_list;
    }

    public void setGroupofgoods_list(List<CheckingGroupOfGoods> groupofgoods_list) {
        this.groupofgoods_list = groupofgoods_list;
    }

    public List<OffenseArticles> getOffensearticles_list() {
        return offensearticles_list;
    }

    public void setOffensearticles_list(List<OffenseArticles> offensearticles_list) {
        this.offensearticles_list = offensearticles_list;
    }
}
