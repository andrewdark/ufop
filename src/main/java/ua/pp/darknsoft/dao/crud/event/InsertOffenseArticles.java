package ua.pp.darknsoft.dao.crud.event;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

import javax.sql.DataSource;
import java.sql.Types;

/**
 * Created by Dark on 26.03.2017.
 */
public class InsertOffenseArticles extends SqlUpdate{
    private static final String SQL_INSERT ="INSERT INTO offense_articles_table (check_event_link, articles_law_link) " +
            "VALUES (:check_event_link, :articles_law_link)";

    public InsertOffenseArticles(DataSource ds) {
        super(ds, SQL_INSERT);
        super.declareParameter(new SqlParameter("check_event_link", Types.BIGINT));
        super.declareParameter(new SqlParameter("articles_law_link", Types.VARCHAR));
    }
}
