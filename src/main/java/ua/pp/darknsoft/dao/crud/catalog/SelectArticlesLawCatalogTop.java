package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.ArticlesLawCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andrew on 28.03.2017.
 */

public class SelectArticlesLawCatalogTop extends MappingSqlQuery<ArticlesLawCatalog> {
    private static final String SQL_SELECT_ARTICLES_CATALOG = "SELECT *,nlevel(treemark) FROM articles_law_catalog_table WHERE nlevel(treemark) <=1 ORDER BY id";

    public SelectArticlesLawCatalogTop(DataSource ds) {
        super(ds, SQL_SELECT_ARTICLES_CATALOG);
    }

    @Override
    protected ArticlesLawCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        ArticlesLawCatalog articlesLawCatalog = new ArticlesLawCatalog();
        articlesLawCatalog.setId(resultSet.getInt("id"));
        articlesLawCatalog.setTreemark(resultSet.getString("treemark"));
        articlesLawCatalog.setCaption(resultSet.getString("caption"));
        articlesLawCatalog.setArticle(resultSet.getString("article"));

        return articlesLawCatalog;
    }
}
