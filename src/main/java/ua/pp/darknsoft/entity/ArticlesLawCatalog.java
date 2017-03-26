package ua.pp.darknsoft.entity;

/**
 * Created by Dark on 26.03.2017.
 */
public class ArticlesLawCatalog {
    private long id;
    private String treemark;
    private String caption;
    private String article;
    private String description;

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
        this.treemark = treemark;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
