package ua.pp.darknsoft.entity;

/**
 * Created by Dark on 26.03.2017.
 */
public class OffenseArticles {
    private long id;
    private long check_event_link;
    private String articles_law_link;
    private String caption;

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
        this.articles_law_link = articles_law_link;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
