package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 29.03.2017.
 */
public class Precaution {
    private long id;
    private long check_event_link;
    private int precaution_catalog_link;
    private String decision_number;
    private String precaution_name;
    private String service_date;
    private String plan_date;
    private String fact_date;

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

    public int getPrecaution_catalog_link() {
        return precaution_catalog_link;
    }

    public void setPrecaution_catalog_link(int precaution_catalog_link) {
        this.precaution_catalog_link = precaution_catalog_link;
    }

    public String getDecision_number() {
        return decision_number;
    }

    public void setDecision_number(String decision_number) {
        this.decision_number = decision_number;
    }

    public String getService_date() {
        return service_date;
    }

    public void setService_date(String service_date) {
        if(service_date.isEmpty())this.service_date = "0001-01-01";
        else this.service_date = service_date;
    }

    public String getPlan_date() {
        return plan_date;
    }

    public void setPlan_date(String plan_date) {
        if(plan_date.isEmpty()) this.plan_date = "0001-01-01";
        else this.plan_date = plan_date;
    }

    public String getFact_date() {
        return fact_date;
    }

    public void setFact_date(String fact_date) {
        if(fact_date.isEmpty()) this.fact_date = "0001-01-01";
        else this.fact_date = fact_date;
    }

    public String getPrecaution_name() {
        return precaution_name;
    }

    public void setPrecaution_name(String precaution_name) {
        this.precaution_name = precaution_name;
    }
}
