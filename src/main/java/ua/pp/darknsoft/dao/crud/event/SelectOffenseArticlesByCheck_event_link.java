package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.OffenseArticles;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 31.03.2017.
 */
public class SelectOffenseArticlesByCheck_event_link extends MappingSqlQuery<OffenseArticles>{
    private static final String SQL_SELECT_OFFENSE_ARTICLES = "SELECT oat.*,alt.caption FROM offense_articles_table oat " +
            "INNER JOIN articles_law_catalog_table alt ON(alt.id=oat.articles_law_link) WHERE oat.check_event_link = :check_event_link";

    public SelectOffenseArticlesByCheck_event_link(DataSource ds) {
        super(ds, SQL_SELECT_OFFENSE_ARTICLES);
        super.declareParameter(new SqlOutParameter("check_event_link", Types.BIGINT));
    }

    @Override
    protected OffenseArticles mapRow(ResultSet resultSet, int i) throws SQLException {
        OffenseArticles offenseArticles = new OffenseArticles();
        offenseArticles.setId(resultSet.getLong("id"));
        offenseArticles.setCheck_event_link(resultSet.getLong("check_event_link"));
        offenseArticles.setArticles_law_link(resultSet.getInt("Articles_law_link"));
        offenseArticles.setCaption(resultSet.getString("caption"));
        return offenseArticles;
    }
}
