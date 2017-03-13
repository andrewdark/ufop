package ua.pp.darknsoft.dao.crud.user;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;
import ua.pp.darknsoft.dao.crud.worktime.SelectWorkTimeByUser_linkDESC;
import ua.pp.darknsoft.entity.OverUser;
import ua.pp.darknsoft.entity.User;
import ua.pp.darknsoft.entity.WorkTime;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 10.03.2017.
 */
public class SelectUserByStructure_link extends MappingSqlQuery<OverUser>{
    private SelectWorkTimeByUser_linkDESC selectWorkTimeByUser_linkDESC;
    private static final String SQL_SELECT_USERS = "SELECT ut.id,ut.username,ut.structure_link, ut.contact_link, ct.first_name,ct.last_name FROM user_table ut INNER JOIN contact_table ct ON(ct.id=ut.contact_link) WHERE ut.structure_link = (:structure_link)::LTREE";

    public SelectUserByStructure_link(DataSource ds) {
        super(ds, SQL_SELECT_USERS);
        super.declareParameter(new SqlParameter("structure_link", Types.VARCHAR));
        this.selectWorkTimeByUser_linkDESC = new SelectWorkTimeByUser_linkDESC(ds);
    }

    @Override
    protected OverUser mapRow(ResultSet resultSet, int i) throws SQLException {
        OverUser user = new OverUser();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setContact_link(resultSet.getLong("contact_link"));
        user.setStructure_link(resultSet.getString("structure_link"));
        user.setCt_fn(resultSet.getString("first_name"));
        user.setCt_ln(resultSet.getString("last_name"));
        user.setWorkTime(getMyWorkTime(resultSet.getInt("id")));
        return user;
    }

    private WorkTime getMyWorkTime(int id){
        WorkTime myWorkTime = new WorkTime();
        Map<String,Integer> bind = new HashMap<>();
        bind.put("user_link",id);
        bind.put("limit",1);
        try{
            myWorkTime = selectWorkTimeByUser_linkDESC.executeByNamedParam(bind).get(0);
        }catch (IndexOutOfBoundsException ex){
            myWorkTime.setId(0);
            myWorkTime.setType_of_action((short)2);
            myWorkTime.setS_cause_link("Не працював в системі");
        }
        catch (Exception ex){
            myWorkTime.setId(0);
            myWorkTime.setType_of_action((short)2);
            myWorkTime.setS_cause_link("Error: "+ex);
        }

        return  myWorkTime;
    }
}
