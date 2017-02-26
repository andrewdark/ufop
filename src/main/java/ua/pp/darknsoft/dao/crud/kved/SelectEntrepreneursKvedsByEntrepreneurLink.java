package ua.pp.darknsoft.dao.crud.kved;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.EntrepreneursKveds;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Dark on 26.02.2017.
 */
public class SelectEntrepreneursKvedsByEntrepreneurLink extends MappingSqlQuery<EntrepreneursKveds> {
    private static final String SQL_SELECT_KVEDS="SELECT ek.*, kc.label, kc.name, u.username FROM kveds_entrepreneur_table ek INNER JOIN kved_catalog_table kc ON (kc.treemark=ek.kved_catalog_link::ltree) INNER JOIN user_table u ON(ek.owner=u.id) WHERE entrepreneur_link = :entrepreneur_link";

    public SelectEntrepreneursKvedsByEntrepreneurLink(DataSource ds) {
        super(ds, SQL_SELECT_KVEDS);
        super.declareParameter(new SqlParameter("entrepreneur_link", Types.BIGINT));
    }

    @Override
    protected EntrepreneursKveds mapRow(ResultSet resultSet, int i) throws SQLException {
        EntrepreneursKveds entrepreneursKveds = new EntrepreneursKveds();
        entrepreneursKveds.setId(resultSet.getLong("id"));
        entrepreneursKveds.setEntrepreneur_link(resultSet.getLong("entrepreneur_link"));
        entrepreneursKveds.setKved_catalog_link(resultSet.getString("kved_catalog_link"));
        entrepreneursKveds.setKved_catalog_label(resultSet.getString("label"));
        entrepreneursKveds.setKved_catalog_name(resultSet.getString("name"));
        entrepreneursKveds.setDatereg(resultSet.getTimestamp("datereg"));
        entrepreneursKveds.setOwner(resultSet.getString("username"));

        return  entrepreneursKveds;
    }
}
