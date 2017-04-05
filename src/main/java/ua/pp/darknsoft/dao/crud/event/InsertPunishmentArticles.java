package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Andrew on 30.03.2017.
 */
public class InsertPunishmentArticles extends SqlUpdate{
    private static final String SQL_INSERT_PUNISHMENT_ARTICLES="INSERT INTO punishment_articles_table " +
            "(check_event_link,articles_law_link) VALUES (:check_event_link,:articles_law_link)";

    public InsertPunishmentArticles(DataSource ds) {
        super(ds, SQL_INSERT_PUNISHMENT_ARTICLES);
        super.declareParameter(new SqlOutParameter("check_event_link", Types.BIGINT));
        super.declareParameter(new SqlOutParameter("articles_law_link", Types.VARCHAR));
        compile();
    }
}
