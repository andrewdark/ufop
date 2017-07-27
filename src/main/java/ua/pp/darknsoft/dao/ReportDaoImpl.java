package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by Andrew on 27.07.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ReportDaoImpl implements ReportDao{
    private DataSource dataSource;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
