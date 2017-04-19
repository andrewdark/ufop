package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.info.SelectArticlesById;
import ua.pp.darknsoft.entity.ArticlesLawCatalog;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 19.04.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class InfoDaoImpl implements InfoDao, Serializable{
    DataSource dataSource;
    SelectArticlesById selectArticlesById;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectArticlesById = new SelectArticlesById(dataSource);
    }

    @Override
    public List<ArticlesLawCatalog> getArticleById(String treemark){
        Map<String,String> bind = new HashMap<>();
        bind.put("treemark",treemark);
        return selectArticlesById.executeByNamedParam(bind);
    }
}
