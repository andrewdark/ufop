package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.*;

import java.util.List;

/**
 * Created by Dark on 26.03.2017.
 */
public interface CheckEventDao {
    List<Sanction> getSanctionEventByCheckEventLink(long check_event_link);

    List<CheckEventSupplemented> getCheckEventByUfopLink(long ufop_link);

    List<CheckEventSupplemented> getCheckEventById(long checkEvent_link);

    List<Precaution> getPrecautionByCheckEventLink(long check_event_link);

    List<PunishmentArticles> getPunishmentArticlesByCheckEventLink(long check_event_link);

    void createCheckingGroupOfGoods(CheckingGroupOfGoods checkingGroupOfGoods);

    void createPunishmentArticlesByCheckEventLink(PunishmentArticles punishmentArticles);

    void createPrecaution(Precaution precaution);

    void createSanction(Sanction sanction);

    void createOffenseArticles(OffenseArticles offenseArticles);

    CheckEventSupplemented createEventSupplemented(CheckEventSupplemented checkEventSupplemented);
}
