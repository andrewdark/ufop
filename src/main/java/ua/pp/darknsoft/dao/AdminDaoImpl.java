package ua.pp.darknsoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.administrator.UpdateUser;
import ua.pp.darknsoft.entity.User;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Created by Andrew on 16.01.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AdminDaoImpl implements AdminDao, Serializable {
    private DataSource dataSource;
    private UpdateUser updateUser;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.updateUser = new UpdateUser(dataSource);

    }

    @Override
    public void updateUser(User user) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("id", user.getId());
        bind.put("structure_link", user.getStructure_link());
        try{
            bind.put("role", Integer.parseInt(user.getRole_name()));
        }catch (Exception ex){
            bind.put("role", 1);
        }
        bind.put("email", user.getEmail());
        bind.put("enabled", user.isEnabled());
        updateUser.updateByNamedParam(bind);

    }

}
