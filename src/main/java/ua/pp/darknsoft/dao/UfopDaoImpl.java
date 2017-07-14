package ua.pp.darknsoft.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.ufop.*;
import ua.pp.darknsoft.dao.crud.ufop.search.SelectUfopsWhereUnitAndTimeByPaginator;
import ua.pp.darknsoft.dao.crud.ufop.search.SelectUfopsWithoutEventByPaginator;
import ua.pp.darknsoft.dao.crud.ufop.search.SelectUfopsWithoutEventForOneYearByPaginator;
import ua.pp.darknsoft.entity.Ufop;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrew on 09.03.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UfopDaoImpl implements UfopDao, Serializable {
    private DataSource dataSource;
    private SelectUfopByCodeEquals selectUfopByCodeEquals;
    private InsertUfopReturnId insertUfopReturnId;
    private SelectUfopByIdEquals selectUfopByIdEquals;
    private SelectUfopsByPaginator selectUfopsByPaginator;
    private UpdateUfopReturnId updateUfopReturnId;
    private SelectUfopsByPaginatorMultiple selectUfopsByPaginatorMultiple;
    private SelectUfopByCreator_link selectUfopByCreator_link;
    private SelectUfopsWithoutEventByPaginator selectUfopsWithoutEventByPaginator;
    private SelectUfopsWithoutEventForOneYearByPaginator selectUfopsWithoutEventForOneYearByPaginator;
    private SelectUfopsWhereUnitAndTimeByPaginator selectUfopsWhereUnitAndTimeByPaginator;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectUfopByCodeEquals = new SelectUfopByCodeEquals(dataSource);
        this.insertUfopReturnId = new InsertUfopReturnId(dataSource);
        this.selectUfopByIdEquals = new SelectUfopByIdEquals(dataSource);
        this.selectUfopsByPaginator = new SelectUfopsByPaginator(dataSource);
        this.updateUfopReturnId = new UpdateUfopReturnId(dataSource);
        this.selectUfopsByPaginatorMultiple = new SelectUfopsByPaginatorMultiple(dataSource);
        this.selectUfopByCreator_link = new SelectUfopByCreator_link(dataSource);
        this.selectUfopsWithoutEventByPaginator = new SelectUfopsWithoutEventByPaginator(dataSource);
        this.selectUfopsWithoutEventForOneYearByPaginator = new SelectUfopsWithoutEventForOneYearByPaginator(dataSource);
        this.selectUfopsWhereUnitAndTimeByPaginator = new SelectUfopsWhereUnitAndTimeByPaginator(dataSource);
    }

    @Override
    public List<Ufop> searchUfopByCode(String ufop_code) {
        Map<String, String> bind = new HashMap<>();
        bind.put("ufop_code", ufop_code);
        return selectUfopByCodeEquals.executeByNamedParam(bind);
    }

    @Override
    public List<Ufop> searchUfopById(long ufop_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("ufop_link", ufop_link);
        return selectUfopByIdEquals.executeByNamedParam(bind);
    }

    @PreAuthorize(value = "isAuthenticated()")
    @Override
    public Ufop createUfop(Ufop ufop) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("ufop_is", ufop.getUfop_is());
        bind.put("ufop_name", ufop.getUfop_name());
        bind.put("ufop_code", ufop.getUfop_code());
        bind.put("a_place_of_reg", ufop.getA_place_of_reg());
        bind.put("n_place_of_reg", ufop.getN_place_of_reg());
        bind.put("f_place_of_reg", ufop.getF_place_of_reg());
        bind.put("b_place_of_reg", ufop.getB_place_of_reg());
        bind.put("creator_link", SecurityContextHolder.getContext().getAuthentication().getName().toString());
        bind.put("description", ufop.getDescription());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertUfopReturnId.updateByNamedParam(bind, keyHolder);
        ufop.setId(keyHolder.getKey().longValue());
        return ufop;
    }

    @Override
    public List<Ufop> getEntrepreneurByPaginator(int total, int pageid, short ufop_is) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("total", total);
        bind.put("pageid", pageid);
        bind.put("ufop_is", ufop_is);
        return selectUfopsByPaginator.executeByNamedParam(bind);
    }
    @Override
    public List<Ufop> getUfopByPaginatorMultiple(int total, int pageid, String stext) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("total", total);
        bind.put("pageid", pageid);
        bind.put("stext", "%"+stext+"%");
        return selectUfopsByPaginatorMultiple.executeByNamedParam(bind);
    }
    @Override
    public List<Ufop> getUfopByCreatorLink(int total, int pageid, int creator_link) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("total", total);
        bind.put("pageid", pageid);
        bind.put("creator_link", creator_link);
        return selectUfopByCreator_link.executeByNamedParam(bind);
    }
    @PreAuthorize(value = "isAuthenticated()")
    @Override
    public Ufop editUfop(Ufop ufop) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("id",ufop.getId());
        bind.put("ufop_is", ufop.getUfop_is());
        bind.put("ufop_name", ufop.getUfop_name());
        bind.put("ufop_code", ufop.getUfop_code());
        bind.put("a_place_of_reg", ufop.getA_place_of_reg());
        bind.put("n_place_of_reg", ufop.getN_place_of_reg());
        bind.put("f_place_of_reg", ufop.getF_place_of_reg());
        bind.put("b_place_of_reg", ufop.getB_place_of_reg());
        bind.put("creator_link", SecurityContextHolder.getContext().getAuthentication().getName().toString());
        bind.put("description", ufop.getDescription());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        updateUfopReturnId.updateByNamedParam(bind, keyHolder);
        ufop.setId(keyHolder.getKey().longValue());
        return ufop;
    }

    @Override
    public List<Ufop> getUfopByWithoutEvent(int total, int pageid) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("total", total);
        bind.put("pageid", pageid);
        return selectUfopsWithoutEventByPaginator.executeByNamedParam(bind);
    }
    @Override
    public List<Ufop> getUfopByWithoutEventForOneYear(int total, int pageid) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("total", total);
        bind.put("pageid", pageid);
        return selectUfopsWithoutEventForOneYearByPaginator.executeByNamedParam(bind);
    }
    @Override
    public List<Ufop> getUfopByUnitAndTime(int total, int pageid, String unit, String utime) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("total", total);
        bind.put("pageid", pageid);
        bind.put("unit", unit);
        bind.put("utime", utime);
        return selectUfopsWhereUnitAndTimeByPaginator.executeByNamedParam(bind);
    }
}
