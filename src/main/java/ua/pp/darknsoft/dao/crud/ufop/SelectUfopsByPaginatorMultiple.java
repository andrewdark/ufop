package ua.pp.darknsoft.dao.crud.ufop;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.Ufop;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Dark on 25.03.2017.
 * Поиск либо по коду либо по имени
 */
public class SelectUfopsByPaginatorMultiple extends MappingSqlQuery<Ufop> {
    private static final String SQL_SelectUfopsByPaginator = "SELECT ufop.*,u.username FROM ufop_table ufop " +
            "INNER JOIN user_table u ON(u.id = ufop.creator_link) WHERE lower(ufop_code) like lower(:stext) OR " +
            "lower(ufop_name) like lower(:stext) ORDER BY ufop.id LIMIT :total OFFSET :pageid";

    public SelectUfopsByPaginatorMultiple(DataSource ds) {
        super(ds, SQL_SelectUfopsByPaginator);
        super.declareParameter(new SqlParameter("total", Types.INTEGER));
        super.declareParameter(new SqlParameter("pageid", Types.INTEGER));
        super.declareParameter(new SqlParameter("stext", Types.VARCHAR));
    }

    @Override
    protected Ufop mapRow(ResultSet resultSet, int i) throws SQLException {
        Ufop ufop = new Ufop();
        ufop.setId(resultSet.getLong("id"));
        ufop.setUfop_is(resultSet.getShort("ufop_is"));
        ufop.setUfop_name(resultSet.getString("ufop_name"));
        ufop.setUfop_code(resultSet.getString("ufop_code"));
        ufop.setA_place_of_reg(resultSet.getString("a_place_of_reg"));
        ufop.setN_place_of_reg(resultSet.getString("n_place_of_reg"));
        ufop.setF_place_of_reg(resultSet.getString("f_place_of_reg"));
        ufop.setB_place_of_reg(resultSet.getString("b_place_of_reg"));
        ufop.setSeries_of_passport(resultSet.getString("series_of_passport"));
        ufop.setNumber_of_passport(resultSet.getString("number_of_passport"));
        ufop.setScreator_link(resultSet.getString("username"));

        return ufop;
    }
}
