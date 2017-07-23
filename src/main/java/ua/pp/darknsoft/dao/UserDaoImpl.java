package ua.pp.darknsoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.user.*;
import ua.pp.darknsoft.entity.OverUser;
import ua.pp.darknsoft.entity.User;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dark on 11.01.2017.
 */

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserDaoImpl implements UserDao, Serializable {
    private DataSource dataSource;
    private InsertUser insertUser;
    private SelectUser selectUser;
    private UpdateUser updateUser;
    private DeleteUser deleteUser;
    private SelectUserByStructure_link selectUserByStructure_link;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private SelectUserListByUserNameLike selectUserListByUserNameLike;


    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.insertUser = new InsertUser(dataSource);
        this.selectUser = new SelectUser(dataSource);
        this.updateUser = new UpdateUser(dataSource);
        this.deleteUser = new DeleteUser(dataSource);
        this.selectUserByStructure_link = new SelectUserByStructure_link(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.selectUserListByUserNameLike = new SelectUserListByUserNameLike(dataSource);
    }

    public void createUser(User user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("username", user.getUsername().toLowerCase());
        paramMap.put("pwd", user.getPwd());
        paramMap.put("email", user.getEmail());

        insertUser.updateByNamedParam(paramMap);

    }

    public List<User> findUserByName(String username) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", username);
        return selectUser.executeByNamedParam(paramMap);
    }

    @PreAuthorize(value = "authenticated")
    public void updateUser(User user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", user.getUsername().toLowerCase());
        //paramMap.put("useremail",user.getUseremail());
        paramMap.put("pwd", user.getPwd());
        updateUser.updateByNamedParam(paramMap);
    }

    @PreAuthorize(value = "authenticated")
    public void deleteUser(String username) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", username.toLowerCase());
        deleteUser.updateByNamedParam(paramMap);

    }

    @Override
    public String getUserNameById(int id) {
        String sql = "SELECT username FROM user_table WHERE id=:id";
        Map<String, Object> bind = new HashMap<>();
        bind.put("id", id);

        return (String) namedParameterJdbcTemplate.queryForObject(sql, bind, String.class).toLowerCase();
    }

    @Override
    public int getUserIdByUserName(String username) {
        String sql = "SELECT id FROM user_table WHERE username=:username";
        Map<String, Object> bind = new HashMap<>();
        bind.put("username", username);

        return (int) namedParameterJdbcTemplate.queryForObject(sql, bind, Integer.class);
    }

    @Override
    public String getUserStructureNameByUserName(String username) {
        String sql = "SELECT name FROM structure_catalog_table WHERE treemark=(SELECT structure_link FROM user_table WHERE LOWER (username) = LOWER (:username) )";
        Map<String, String> bind = new HashMap<>();
        bind.put("username", username);
        try {
            return (String) namedParameterJdbcTemplate.queryForObject(sql, bind, String.class);
        } catch (Exception ex) {
            return ex + "";
        }

    }

    @Override
    public List<OverUser> getUsersByStructureLink(String treemark) {
        Map<String, String> bind = new HashMap<>(3);
        bind.put("structure_link", treemark);
        return selectUserByStructure_link.executeByNamedParam(bind);
    }

    @Override
    public List<User> getUsersByUserNameLike(String username) {
        Map<String, String> bind = new HashMap<>();
        bind.put("username", username);
        return selectUserListByUserNameLike.executeByNamedParam(bind);
    }
}
