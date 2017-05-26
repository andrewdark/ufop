package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.*;

import java.util.List;

/**
 * Created by Dark on 26.03.2017.
 */
public interface CheckEventDao {
    List<CheckingGroupOfGoods> getCheckingGroupOfGoodsByCheckEventLink(long check_event_link);

    List<CheckingCommObj> getCheckingCommercialObjectByEventLink(long check_event_link);

    List<Sanction> getSanctionEventByCheckEventLink(long check_event_link);

    List<Sanction> getSanctionById(long id);

    List<Lawsuits> getLawsuitsByCheckEventLink(long check_event_link);

    List<CheckEventSupplemented> getCheckEventByUfopLink(long ufop_link);

    List<CheckEventSupplemented> getCheckEventById(long checkEvent_link);

    List<Precaution> getPrecautionById(long id);

    List<Precaution> getPrecautionByCheckEventLink(long check_event_link);

    List<Precaution> getPrecautionByEventAndPrecautionLink(long check_event_link, long precaution_catalog_link);

    List<OffenseArticles> getOffenseArticlesByCheckEventLink(long check_event_link);

    void createCheckingGroupOfGoods(CheckingGroupOfGoods checkingGroupOfGoods);

    void createPrecaution(Precaution precaution);

    void createSanction(Sanction sanction);

    void createOffenseArticles(OffenseArticles offenseArticles);

    void createLawsuits(Lawsuits lawsuits);

    CheckEventSupplemented createEventSupplemented(CheckEventSupplemented checkEventSupplemented);

    void createCheckingCommObj(CheckingCommObj checkingCommObj);

    void deleteCheckingGroupOfGoods(long id);

    void deleteOffenseArticles(long id);

    void deletePrecaution(long id);

    CheckEventSupplemented editEvent(CheckEventSupplemented eventSupplemented);

    void updateCheckingCommObjById(CheckingCommObj checkingCommObj);

    void updatePrecautionDate(Precaution precaution);

    void updateSanctionById(Sanction sanction);

    void deleteSanctionById(long id);
}
