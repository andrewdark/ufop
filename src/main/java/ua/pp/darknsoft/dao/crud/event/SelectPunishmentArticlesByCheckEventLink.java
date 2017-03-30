package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.PunishmentArticles;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 30.03.2017.
 */
public class SelectPunishmentArticlesByCheckEventLink extends MappingSqlQuery<PunishmentArticles>{
    private static final String SQL_SELECT_PUNISHMENT_ARTICLES = "SELECT pat.*,alt.caption FROM punishment_articles_table pat " +
            "INNER JOIN articles_law_catalog_table alt ON(alt.id=pat.articles_law_link) WHERE pat.check_event_link = :check_event_link";

    public SelectPunishmentArticlesByCheckEventLink(DataSource ds) {
        super(ds,SQL_SELECT_PUNISHMENT_ARTICLES);
        super.declareParameter(new SqlOutParameter("check_event_link", Types.BIGINT));
    }

    @Override
    protected PunishmentArticles mapRow(ResultSet resultSet, int i) throws SQLException {
        PunishmentArticles punishmentArticles = new PunishmentArticles();
        punishmentArticles.setId(resultSet.getLong("id"));
        punishmentArticles.setCheck_event_link(resultSet.getLong("check_event_link"));
        punishmentArticles.setArticles_law_link(resultSet.getInt("articles_law_link"));
        punishmentArticles.setName(resultSet.getString("caption"));
        return punishmentArticles;
    }
}
