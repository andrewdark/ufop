package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 29.03.2017.
 */
public class Sanction {
    private long id;
    private long check_event_link;
    private String charged_amount;
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

    public String getCharged_amount() {
        return charged_amount;
    }

    public void setCharged_amount(String charged_amount) {
        this.charged_amount = charged_amount;
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
}
