package ua.pp.darknsoft.dao.crud.kved;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.KvedsUfop;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Dark on 26.02.2017.
 */
public class SelectEntrepreneursKvedsByEntrepreneurLink extends MappingSqlQuery<KvedsUfop> {
    private static final String SQL_SELECT_KVEDS="SELECT ek.*, kc.label, kc.name, u.username FROM kveds_ufop_table ek " +
            "INNER JOIN kved_catalog_table kc ON (kc.treemark=ek.kved_catalog_link::ltree) INNER JOIN user_table u ON(ek.creator_link=u.id) " +
            "WHERE ufop_link = :ufop_link";

    public SelectEntrepreneursKvedsByEntrepreneurLink(DataSource ds) {
        super(ds, SQL_SELECT_KVEDS);
        super.declareParameter(new SqlParameter("ufop_link", Types.BIGINT));
    }

    @Override
    protected KvedsUfop mapRow(ResultSet resultSet, int i) throws SQLException {
        KvedsUfop entrepreneursKveds = new KvedsUfop();
        entrepreneursKveds.setId(resultSet.getLong("id"));
        entrepreneursKveds.setUfop_link(resultSet.getLong("ufop_link"));
        entrepreneursKveds.setKved_catalog_link(resultSet.getString("kved_catalog_link"));
        entrepreneursKveds.setKved_catalog_label(resultSet.getString("label"));
        entrepreneursKveds.setKved_catalog_name(resultSet.getString("name"));
        entrepreneursKveds.setDatereg(resultSet.getTimestamp("datereg"));
        entrepreneursKveds.setCreator_link(resultSet.getString("username"));

        return  entrepreneursKveds;
    }
}
