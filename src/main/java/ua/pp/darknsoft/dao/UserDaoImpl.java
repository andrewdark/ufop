package ua.pp.darknsoft.dao;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import ua.pp.darknsoft.dao.crud.user.DeleteUser;
import ua.pp.darknsoft.dao.crud.user.InsertUser;
import ua.pp.darknsoft.dao.crud.user.SelectUser;
import ua.pp.darknsoft.dao.crud.user.UpdateUser;
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
@Repository
public class UserDaoImpl implements UserDao,Serializable{
    private DataSource dataSource;
    private InsertUser insertUser;
    private SelectUser selectUser;
    private UpdateUser updateUser;
    private DeleteUser deleteUser;


    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {  //      return selectUser.executeByNamedParam(paramMap);
        this.dataSource = dataSource;
        this.insertUser = new InsertUser(dataSource);
        this.selectUser = new SelectUser(dataSource);
        this.updateUser = new UpdateUser(dataSource);
        this.deleteUser = new DeleteUser(dataSource);
    }

    public void createUser(User user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("username", user.getUsername());
        paramMap.put("pwd", user.getPwd());
        paramMap.put("email", user.getEmail());

        insertUser.updateByNamedParam(paramMap);

    }

    public List<User> findUserByName(String username) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", username);
        return selectUser.executeByNamedParam(paramMap);
    }

    public void updateUser(User user) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("username", user.getUsername());
        //paramMap.put("useremail",user.getUseremail());
        paramMap.put("pwd", user.getPwd());
        updateUser.updateByNamedParam(paramMap);
    }
    @PreAuthorize(value = "authenticated")
    public void deleteUser(String username) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("username",username);
        deleteUser.updateByNamedParam(paramMap);

    }
}
