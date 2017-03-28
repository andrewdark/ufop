package ua.pp.darknsoft.dao.crud.catalog;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.ArticlesLawCatalog;
import ua.pp.darknsoft.entity.BasicGroupOfGoodsCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 03.02.2017.
 */
public class SelectArticlesByTreemark extends MappingSqlQuery<ArticlesLawCatalog> {
    private static final String SQL_SELECT_ARTICLES_CATALOG="SELECT l.*, nlevel(l.treemark) FROM articles_law_catalog_table l WHERE l.treemark<@:treemark::ltree AND nlevel(l.treemark) = :nlevel ORDER BY l.id";

    public SelectArticlesByTreemark(DataSource ds){
        super(ds, SQL_SELECT_ARTICLES_CATALOG);
        super.declareParameter(new SqlParameter("treemark", Types.VARCHAR));
        super.declareParameter(new SqlParameter("nlevel", Types.INTEGER));

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