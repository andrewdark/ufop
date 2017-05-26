package ua.pp.darknsoft.dao;

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

    private InsertCheckEventReturnId insertCheckEventReturnId;
    private InsertCheckingCommObj insertCheckingCommObj;
    private InsertOffenseArticles insertOffenseArticles;
    private InsertCheckingGoods insertCheckingGoods;
    private InsertSanction insertSanction;
    private InsertLawsuits insertLawsuits;
    private InsertPrecaution insertPrecaution;
    private SelectCheckEventById selectCheckEventById;
    private SelectCheckEventByUfop_link selectCheckEventByUfop_link;
    private SelectPrecautionById selectPrecautionById;
    private SelectPrecautionByCheck_event_link selectPrecautionByCheck_event_link;
    private SelectPrecautionByEventAndPrecaution_link selectPrecautionByEventAndPrecaution_link;
    private SelectSanctionById selectSanctionById;
    private SelectSanctionByCheckEventLink selectSanctionByCheckEventLink;
    private SelectLawsuitsByCheck_event_link selectLawsuitsByCheck_event_link;
    private SelectOffenseArticlesByCheck_event_link selectOffenseArticlesByCheck_event_link;
    private SelectCheckGoodsByEvent_link selectCheckGoodsByEvent_link;
    private DeleteCheckingGoodsById deleteCheckingGoodsById;
    private DeleteOffenseArticlesById deleteOffenseArticlesById;
    private DeletePrecautionById deletePrecautionById;
    private DeleteSanctionById deleteSanctionById;
    private SelectCheckingCommercialObject selectCheckingCommercialObject;
    private UpdateCheckEventReturnID updateCheckEventReturnID;
    private UpdateCheckingCommObj updateCheckingCommObj;
    private UpdatePrecautionById updatePrecautionById;
    private UpdateSanction updateSanction;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {

        this.dataSource = dataSource;
        this.selectCheckEventByUfop_link = new SelectCheckEventByUfop_link(dataSource);
        this.insertCheckEventReturnId = new InsertCheckEventReturnId(dataSource);
        this.insertCheckingCommObj = new InsertCheckingCommObj(dataSource);
        this.insertOffenseArticles = new InsertOffenseArticles(dataSource);
        this.insertCheckingGoods = new InsertCheckingGoods(dataSource);
        this.selectCheckEventById = new SelectCheckEventById(dataSource);
        this.insertPrecaution = new InsertPrecaution(dataSource);
        this.selectPrecautionById = new SelectPrecautionById(dataSource);
        this.selectPrecautionByCheck_event_link = new SelectPrecautionByCheck_event_link(dataSource);
        this.selectPrecautionByEventAndPrecaution_link = new SelectPrecautionByEventAndPrecaution_link(dataSource);
        this.selectSanctionById = new SelectSanctionById(dataSource);
        this.selectSanctionByCheckEventLink = new SelectSanctionByCheckEventLink(dataSource);
        this.insertSanction = new InsertSanction(dataSource);
        this.insertLawsuits = new InsertLawsuits(dataSource);
        this.selectLawsuitsByCheck_event_link = new SelectLawsuitsByCheck_event_link(dataSource);
        this.selectOffenseArticlesByCheck_event_link = new SelectOffenseArticlesByCheck_event_link(dataSource);
        this.selectCheckGoodsByEvent_link = new SelectCheckGoodsByEvent_link(dataSource);
        this.deleteCheckingGoodsById = new DeleteCheckingGoodsById(dataSource);
        this.deleteOffenseArticlesById = new DeleteOffenseArticlesById(dataSource);
        this.deletePrecautionById = new DeletePrecautionById(dataSource);
        this.deleteSanctionById = new DeleteSanctionById(dataSource);
        this.selectCheckingCommercialObject = new SelectCheckingCommercialObject(dataSource);
        this.updateCheckEventReturnID = new UpdateCheckEventReturnID(dataSource);
        this.updateCheckingCommObj = new UpdateCheckingCommObj(dataSource);
        this.updatePrecautionById = new UpdatePrecautionById(dataSource);
        this.updateSanction = new UpdateSanction(dataSource);
    }

    @Override
    public List<CheckingGroupOfGoods> getCheckingGroupOfGoodsByCheckEventLink(long check_event_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("check_event_link", check_event_link);
        return selectCheckGoodsByEvent_link.executeByNamedParam(bind);
    }

    @Override
    public List<CheckingCommObj> getCheckingCommercialObjectByEventLink(long check_event_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("check_event_link", check_event_link);
        return selectCheckingCommercialObject.executeByNamedParam(bind);
    }

    //this
    @Override
    public List<Sanction> getSanctionEventByCheckEventLink(long check_event_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("check_event_link", check_event_link);
        return selectSanctionByCheckEventLink.executeByNamedParam(bind);
    }

    @Override
    public List<Sanction> getSanctionById(long id) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("id", id);
        return selectSanctionById.executeByNamedParam(bind);
    }

    @Override
    public List<Lawsuits> getLawsuitsByCheckEventLink(long check_event_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("check_event_link", check_event_link);
        return selectLawsuitsByCheck_event_link.executeByNamedParam(bind);
    }

    @Override
    public List<CheckEventSupplemented> getCheckEventByUfopLink(long ufop_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("ufop_link", ufop_link);
        return selectCheckEventByUfop_link.executeByNamedParam(bind);
    }

    @Override
    public List<CheckEventSupplemented> getCheckEventById(long id) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("id", id);
        return selectCheckEventById.executeByNamedParam(bind);
    }

    @Override
    public List<Precaution> getPrecautionById(long id) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("id", id);
        return selectPrecautionById.executeByNamedParam(bind);
    }

    @Override
    public List<Precaution> getPrecautionByCheckEventLink(long check_event_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("check_event_link", check_event_link);
        return selectPrecautionByCheck_event_link.executeByNamedParam(bind);
    }

    @Override
    public List<Precaution> getPrecautionByEventAndPrecautionLink(long check_event_link, long precaution_catalog_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("check_event_link", check_event_link);
        bind.put("precaution_catalog_link", precaution_catalog_link);
        return selectPrecautionByEventAndPrecaution_link.executeByNamedParam(bind);
    }


    @Override
    public List<OffenseArticles> getOffenseArticlesByCheckEventLink(long check_event_link) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("check_event_link", check_event_link);
        return selectOffenseArticlesByCheck_event_link.executeByNamedParam(bind);
    }

    @Override
    public void createCheckingGroupOfGoods(CheckingGroupOfGoods checkingGroupOfGoods) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("check_event_link", checkingGroupOfGoods.getCheck_event_link());
        bind.put("goods_catalog_link", checkingGroupOfGoods.getGoods_catalog_link());
        bind.put("checking", checkingGroupOfGoods.isChecking());
        insertCheckingGoods.updateByNamedParam(bind);
    }

    @Override
    public void createPrecaution(Precaution precaution) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("check_event_link", precaution.getCheck_event_link());
        bind.put("precaution_catalog_link", precaution.getPrecaution_catalog_link());
        bind.put("decision_number", precaution.getDecision_number());
        bind.put("service_date", precaution.getService_date());
        bind.put("plan_date", precaution.getPlan_date());
        bind.put("fact_date", precaution.getFact_date());
        insertPrecaution.updateByNamedParam(bind);
    }

    //this
    @Override
    public void createSanction(Sanction sanction) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("check_event_link", sanction.getCheck_event_link());
        bind.put("articles_law_link", sanction.getArticles_law_link());
        bind.put("sanction_number", sanction.getSanction_number());
        bind.put("charged_amount", sanction.getCharged_amount());
        bind.put("service_date", sanction.getService_date());
        bind.put("plan_date", sanction.getPlan_date());
        bind.put("fact_date", sanction.getFact_date());
        bind.put("creator_link", sanction.getCreator_link());
        insertSanction.updateByNamedParam(bind);
    }

    @Override
    public void createOffenseArticles(OffenseArticles offenseArticles) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("check_event_link", offenseArticles.getCheck_event_link());
        bind.put("articles_law_link", offenseArticles.getArticles_law_link());
        insertOffenseArticles.updateByNamedParam(bind);
    }

    @Override
    public void createLawsuits(Lawsuits lawsuits) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("check_event_link", lawsuits.getCheck_event_link());
        bind.put("filed_on_action", lawsuits.getFiled_on_action());
        bind.put("filed_date", lawsuits.getFiled_date());
        bind.put("result_link", lawsuits.getResult_link());
        bind.put("description", lawsuits.getDescription());
        insertLawsuits.updateByNamedParam(bind);
    }

    @Override
    public CheckEventSupplemented createEventSupplemented(CheckEventSupplemented checkEventSupplemented) {
        Map<String, Object> bindEvent = new HashMap<>();
        Map<String, Object> bindCommObj = new HashMap<>();
        Map<String, Object> bindArticles = new HashMap<>();
        Map<String, Object> bindGoods = new HashMap<>();

        bindEvent.put("ufop_link", checkEventSupplemented.getUfop_link());
        bindEvent.put("event_number", checkEventSupplemented.getEvent_number());
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

    @Override
    public void createCheckingCommObj(CheckingCommObj checkingCommObj) {
        Map<String, Object> bindCommObj = new HashMap<>();
        bindCommObj.put("check_event_link", checkingCommObj.getCheck_event_link());
        bindCommObj.put("comm_obj_link", checkingCommObj.getComm_obj_link());
        bindCommObj.put("checking", checkingCommObj.isChecking());
        insertCheckingCommObj.updateByNamedParam(bindCommObj);
    }

    @Override
    public void deleteCheckingGroupOfGoods(long id) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("id", id);
        deleteCheckingGoodsById.updateByNamedParam(bind);
    }

    @Override
    public void deleteOffenseArticles(long id) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("id", id);
        deleteOffenseArticlesById.updateByNamedParam(bind);
    }

    @Override
    public void deletePrecaution(long id) {
        Map<String, Long> bind = new HashMap<>();
        bind.put("id", id);
        deletePrecautionById.updateByNamedParam(bind);
    }

    @Override
    public CheckEventSupplemented editEvent(CheckEventSupplemented eventSupplemented) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("id", eventSupplemented.getId());
        bind.put("event_number", eventSupplemented.getEvent_number());
        bind.put("event_date_begin", eventSupplemented.getEvent_date_begin());
        bind.put("event_date_end", eventSupplemented.getEvent_date_end());
        bind.put("check_type", eventSupplemented.getCheck_type());
        bind.put("is_checking", eventSupplemented.isChecking());
        bind.put("check_violation", eventSupplemented.getCheck_violation());
        bind.put("event_result", eventSupplemented.getEvent_result());
        bind.put("check_sampling", eventSupplemented.getCheck_sampling());
        bind.put("result_sampling", eventSupplemented.getResult_sampling());
        bind.put("creator_link", eventSupplemented.getCreator_link());
        bind.put("structure_catalog_link", eventSupplemented.getStructure_catalog_link());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        updateCheckEventReturnID.updateByNamedParam(bind, keyHolder);
        eventSupplemented.setId(keyHolder.getKey().longValue());
        return eventSupplemented;
    }

    @Override
    public void updateCheckingCommObjById(CheckingCommObj checkingCommObj) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("id", checkingCommObj.getId());
        bind.put("checking", checkingCommObj.isChecking());
        updateCheckingCommObj.updateByNamedParam(bind);
    }

    @Override
    public void updatePrecautionDate(Precaution precaution) {
        Map<String, Object> bind = new HashMap<>();
        bind.put("id", precaution.getId());
        bind.put("service_date", precaution.getService_date());
        bind.put("plan_date", precaution.getPlan_date());
        bind.put("fact_date", precaution.getFact_date());
        updatePrecautionById.updateByNamedParam(bind);
    }

    @Override
    public void updateSanctionById(Sanction sanction) {
        Map<String, Object> bind = new HashMap<String, Object>();
        bind.put("id", sanction.getId());
        bind.put("service_date", sanction.getService_date());
        bind.put("plan_date", sanction.getPlan_date());
        bind.put("fact_date", sanction.getFact_date());
        updateSanction.updateByNamedParam(bind);
    }

    @Override
    public void deleteSanctionById(long id) {
        Map<String, Object> bind = new HashMap<String, Object>();
        bind.put("id", id);
        deleteSanctionById.updateByNamedParam(bind);
    }
}
