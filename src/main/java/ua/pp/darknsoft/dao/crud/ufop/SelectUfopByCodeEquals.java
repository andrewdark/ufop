package ua.pp.darknsoft.dao.crud.ufop;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.entity.Ufop;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by Andrew on 15.03.2017.
 */
public class SelectUfopByCodeEquals extends MappingSqlQuery<Ufop>{
private static final String SQL = "SELECT * FROM ufop_table WHERE ufop_code=:ufop_code";
    public SelectUfopByCodeEquals(DataSource dataSource) {
    super(dataSource,SQL);
    super.declareParameter(new SqlParameter("ufop_code",Types.VARCHAR));
    }

    @Override
    protected Ufop mapRow(ResultSet resultSet, int i) throws SQLException {
        Ufop ufop = new Ufop();
        ufop.setId(resultSet.getLong("id"));
        ufop.setUfop_is(resultSet.getShort("ufop_is"));
        ufop.setUfop_name(resultSet.getString("ufop_name"));
        ufop.setUfop_code(resultSet.getString("ufop_code"));
        ufop.setA_place_of_reg(resultSet.getString("a_place_of_reg"));
        ufop.setA_place_of_reg(resultSet.getString("n_place_of_reg"));
        ufop.setA_place_of_reg(resultSet.getString("f_place_of_reg"));
        ufop.setA_place_of_reg(resultSet.getString("b_place_of_reg"));
        ufop.setSeries_of_passport(resultSet.getString("series_of_passport"));
        ufop.setNumber_of_passport(resultSet.getString("number_of_passport"));
        return ufop;
    }
}
