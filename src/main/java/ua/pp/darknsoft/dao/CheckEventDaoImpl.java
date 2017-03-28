package ua.pp.darknsoft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import ua.pp.darknsoft.dao.crud.event.*;
import ua.pp.darknsoft.entity.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Dark on 26.03.2017.
 */
@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CheckEventDaoImpl implements CheckEventDao, Serializable {
    private DataSource dataSource;
    private SelectCheckEventByUfop_link selectCheckEventByUfop_link;
    private InsertCheckEventReturnId insertCheckEventReturnId;
    private InsertCheckingCommObj insertCheckingCommObj;
    private InsertOffenseArticles insertOffenseArticles;
    private InsertCheckingGoods insertCheckingGoods;
    private SelectCheckEventById selectCheckEventById;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
        this.selectCheckEventByUfop_link = new SelectCheckEventByUfop_link(dataSource);
        this.insertCheckEventReturnId = new InsertCheckEventReturnId(dataSource);
        this.insertCheckingCommObj = new InsertCheckingCommObj(dataSource);
        this.insertOffenseArticles = new InsertOffenseArticles(dataSource);
        this.insertCheckingGoods = new InsertCheckingGoods(dataSource);
        this.selectCheckEventById = new SelectCheckEventById(dataSource);
    }

    @Override
    public List<CheckEventSupplemented> getCheckEventByUfopLink(long ufop_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("ufop_link", ufop_link);
        return selectCheckEventByUfop_link.executeByNamedParam(bind);
    }
    @Override
    public List<CheckEventSupplemented> getCheckEventById(long checkEvent_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("id", checkEvent_link);
        return selectCheckEventById.executeByNamedParam(bind);
    }
    @Override
    public void createCheckingGroupOfGoods(CheckingGroupOfGoods checkingGroupOfGoods){
        Map<String, Object> bind = new HashMap<>();
        bind.put("check_event_link",checkingGroupOfGoods.getCheck_event_link());
        bind.put("goods_catalog_link",checkingGroupOfGoods.getGoods_catalog_link());
        bind.put("checking",checkingGroupOfGoods.isChecking());
        insertCheckingGoods.updateByNamedParam(bind);
    }
    @Override
    public void createOffenseArticles(OffenseArticles offenseArticles){
        Map<String, Object> bind = new HashMap<>();
        bind.put("check_event_link",offenseArticles.getCheck_event_link());
        bind.put("articles_law_link",offenseArticles.getArticles_law_link());
        insertOffenseArticles.updateByNamedParam(bind);
    }
    @Override
    public CheckEventSupplemented createEventSupplemented(CheckEventSupplemented checkEventSupplemented) {
        Map<String, Object> bindEvent = new HashMap<>();
        Map<String, Object> bindCommObj = new HashMap<>();
        Map<String, Object> bindArticles = new HashMap<>();
        Map<String, Object> bindGoods = new HashMap<>();

        bindEvent.put("ufop_link", checkEventSupplemented.getUfop_link());
        bindEvent.put("event_number",checkEventSupplemented.getEvent_number());
        bindEvent.put("event_date_begin", checkEventSupplemented.getEvent_date_begin());
        bindEvent.put("event_date_end", checkEventSupplemented.getEvent_date_end());
        bindEvent.put("check_type", checkEventSupplemented.getCheck_type());
        bindEvent.put("check_violation", checkEventSupplemented.getCheck_violation());
        bindEvent.put("event_result", checkEventSupplemented.getEvent_result());
        bindEvent.put("check_sampling", checkEventSupplemented.getCheck_sampling());
        bindEvent.put("result_sampling", checkEventSupplemented.getResult_sampling());
        bindEvent.put("creator_link", checkEventSupplemented.getCreator_link());
        bindEvent.put("structure_catalog_link", checkEventSupplemented.getStructure_catalog_link());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertCheckEventReturnId.updateByNamedParam(bindEvent, keyHolder);
        checkEventSupplemented.setId(keyHolder.getKey().longValue());
        // next
        for (CheckingCommObj items : checkEventSupplemented.getCommobj_list()) {
            bindCommObj.put("check_event_link", checkEventSupplemented.getId());
            bindCommObj.put("comm_obj_link", items.getComm_obj_link());
            bindCommObj.put("checking", items.isChecking());
            insertCheckingCommObj.updateByNamedParam(bindCommObj);
        }

        return checkEventSupplemented;
    }
}
