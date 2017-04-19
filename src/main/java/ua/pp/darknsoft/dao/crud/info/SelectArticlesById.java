package ua.pp.darknsoft.dao.crud.info;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.ArticlesLawCatalog;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 19.04.2017.
 */
public class SelectArticlesById extends MappingSqlQuery<ArticlesLawCatalog> {
    private static final String SQL_SELECT="SELECT t0.*,t1.caption head1,t2.caption head2 FROM articles_law_catalog_table t0 " +
            "INNER JOIN articles_law_catalog_table t1 ON (t1.treemark @> t0.treemark AND nlevel(t1.treemark) = 1) " +
            "INNER JOIN articles_law_catalog_table t2 ON (t2.treemark @> t0.treemark AND nlevel(t2.treemark) = 2) " +
            "WHERE t0.treemark = :treemark::ltree";

    public SelectArticlesById(DataSource ds) {
        super(ds, SQL_SELECT);
        super.declareParameter(new SqlParameter("treemark", Types.VARCHAR));
    }

    @Override
    protected ArticlesLawCatalog mapRow(ResultSet resultSet, int i) throws SQLException {
        ArticlesLawCatalog articlesLawCatalog = new ArticlesLawCatalog();
        articlesLawCatalog.setId(resultSet.getInt("id"));
        articlesLawCatalog.setArticle(resultSet.getString("article"));
        articlesLawCatalog.setTreemark(resultSet.getString("treemark"));
        articlesLawCatalog.setCaption(resultSet.getString("caption"));
        articlesLawCatalog.setPunishable(resultSet.getString("punishable"));
        articlesLawCatalog.setPenalty(resultSet.getString("penalty"));
        articlesLawCatalog.setLink(resultSet.getString("link"));
        articlesLawCatalog.setIs_active(resultSet.getBoolean("is_active"));
        articlesLawCatalog.setDescription(resultSet.getString("description"));
        articlesLawCatalog.setHead1(resultSet.getString("head1"));
        articlesLawCatalog.setHead2(resultSet.getString("head2"));
        return articlesLawCatalog;
    }
}
