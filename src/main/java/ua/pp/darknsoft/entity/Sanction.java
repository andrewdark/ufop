package ua.pp.darknsoft.entity;

import java.math.BigDecimal;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Andrew on 29.03.2017.
 */
public class Sanction {
    private long id;
    private long check_event_link;
    private BigDecimal charged_amount;
    private String charged_amount_str;
    private String sanction_number;
    private String articles_law_link;
    private String articles_law_caption;
    private String service_date;
    private String plan_date;
    private String fact_date;
    private String creator_link;
    private boolean additionalinformation;
    private String caption;
    private int nav;

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

    public BigDecimal getCharged_amount() {
        return charged_amount;
    }

    public void setCharged_amount(BigDecimal charged_amount) {
        this.charged_amount = charged_amount;
    }

    public String getSanction_number() {
        return sanction_number;
    }

    public void setSanction_number(String sanction_number) {
        this.sanction_number = rejectHtml(sanction_number);
    }

    public String getArticles_law_link() {
        return articles_law_link;
    }

    public void setArticles_law_link(String articles_law_link) {
        this.articles_law_link = rejectHtml(articles_law_link);
    }

    public String getArticles_law_caption() {
        return articles_law_caption;
    }

    public void setArticles_law_caption(String articles_law_caption) {
        this.articles_law_caption = rejectHtml(articles_law_caption);
    }

    public boolean isAdditionalinformation() {
        return additionalinformation;
    }

    public void setAdditionalinformation(boolean additionalinformation) {
        this.additionalinformation = additionalinformation;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = rejectHtml(caption);
    }

    public int getNav() {
        return nav;
    }

    public void setNav(int nav) {
        this.nav = nav;
    }

    public String getService_date() {
        return service_date;
    }

    public String getCreator_link() {
        return creator_link;
    }

    public void setCreator_link(String creator_link) {
        this.creator_link = rejectHtml(creator_link);
    }

    public String getCharged_amount_str() {
        return charged_amount_str;
    }

    public void setCharged_amount_str(String charged_amount_str) {
        this.charged_amount_str = rejectHtml(charged_amount_str);
    }

    public void setService_date(String service_date) {
        if (service_date.isEmpty()) this.service_date = "0001-01-01";
        else this.service_date = rejectHtml(service_date);
    }

    public String getPlan_date() {
        return plan_date;
    }

    public void setPlan_date(String plan_date) {
        if (plan_date.isEmpty()) this.plan_date = "0001-01-01";
        else this.plan_date = rejectHtml(plan_date);
    }

    public String getFact_date() {
        return fact_date;
    }

    public void setFact_date(String fact_date) {
        if (fact_date.isEmpty()) this.fact_date = "0001-01-01";
        else this.fact_date = rejectHtml(fact_date);
    }


}
