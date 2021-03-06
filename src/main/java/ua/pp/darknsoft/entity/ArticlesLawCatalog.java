package ua.pp.darknsoft.entity;

import static ua.pp.darknsoft.support.StaticMethod.*;

/**
 * Created by Dark on 26.03.2017.
 */
public class ArticlesLawCatalog {
    private long id;
    private String treemark;
    private String caption;
    private String article;
    private String punishable;
    private String penalty;
    private String link;
    private boolean is_active;
    private String description;
    private String head1;
    private String head2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTreemark() {
        return treemark;
    }

    public void setTreemark(String treemark) {
        this.treemark = rejectHtml(treemark);
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = rejectHtml(caption);
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = rejectHtml(article);
    }

    public String getPunishable() {
        return punishable;
    }

    public void setPunishable(String punishable) {
        this.punishable = rejectHtml(punishable);
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = rejectHtml(penalty);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = rejectHtml(link);
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = rejectHtml(description);
    }

    public String getHead1() {
        return head1;
    }

    public void setHead1(String head1) {
        this.head1 = rejectHtml(head1);
    }

    public String getHead2() {
        return head2;
    }

    public void setHead2(String head2) {
        this.head2 = rejectHtml(head2);
    }
}
