package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.ArticlesLawCatalog;

import java.util.List;

/**
 * Created by Andrew on 19.04.2017.
 */
public interface InfoDao {
    List<ArticlesLawCatalog> getArticleById(String treemark);
}
