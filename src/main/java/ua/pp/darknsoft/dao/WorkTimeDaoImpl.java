package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.user.DeleteUser;
import ua.pp.darknsoft.dao.crud.user.InsertUser;
import ua.pp.darknsoft.dao.crud.user.SelectUser;
import ua.pp.darknsoft.dao.crud.user.UpdateUser;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;

/**
 * Created by Andrew on 27.02.2017.
 */
@Service
@Scope(value="session",proxyMode= ScopedProxyMode.TARGET_CLASS)
public class WorkTimeDaoImpl implements WorkTimeDao, Serializable {
    private DataSource dataSource;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;

    }


}
