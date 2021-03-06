package ua.pp.darknsoft.entity;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Andrew on 29.03.2017.
 */
public class PunishmentArticles {
    private long id;
    private long check_event_link;
    private String articles_law_link;
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

    public String getArticles_law_link() {
        return articles_law_link;
    }

    public void setArticles_law_link(String articles_law_link) {
        this.articles_law_link = rejectHtml(articles_law_link);
    }

    public boolean isAdditionalinformation() {
        return additionalinformation;
    }

    public void setAdditionalinformation(boolean additionalinformation) {
        this.additionalinformation = additionalinformation;
    }

    public int getNav() {
        return nav;
    }

    public void setNav(int nav) {
        this.nav = nav;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = rejectHtml(caption);
    }
}
