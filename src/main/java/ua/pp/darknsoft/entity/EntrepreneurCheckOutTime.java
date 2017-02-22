package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 16.02.2017.
 */
public class EntrepreneurCheckOutTime {
    private long id;
    private long ufop_link;
    private String inspection_plane_date;
    private String inspection_fact_date;
    private String expiration_date;
    private String inspection_cs_link;

    public long getId() { return id; }

    public void setId(long id) {
        this.id = id;
    }

    public long getUfop_link() {
        return ufop_link;
    }

    public void setUfop_link(long ufop_link) {
        this.ufop_link = ufop_link;
    }

    public String getInspection_plane_date() {
        return inspection_plane_date;
    }

    public void setInspection_plane_date(String inspection_plane_date) {
        this.inspection_plane_date = inspection_plane_date;
    }

    public String getInspection_fact_date() {
        return inspection_fact_date;
    }

    public void setInspection_fact_date(String inspection_fact_date) {
        this.inspection_fact_date = inspection_fact_date;
    }

    public String getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(String expiration_date) {
        this.expiration_date = expiration_date;
    }

    public String getInspection_cs_link() {
        return inspection_cs_link;
    }

    public void setInspection_cs_link(String inspection_cs_link) {
        this.inspection_cs_link = inspection_cs_link;
    }
}
