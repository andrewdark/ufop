package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.CheckEvent;
import ua.pp.darknsoft.entity.CheckEventSupplemented;
import ua.pp.darknsoft.entity.CheckingGroupOfGoods;
import ua.pp.darknsoft.entity.OffenseArticles;

import java.util.List;

/**
 * Created by Dark on 26.03.2017.
 */
public interface CheckEventDao {
    List<CheckEventSupplemented> getCheckEventByUfopLink(long ufop_link);

    List<CheckEventSupplemented> getCheckEventById(long checkEvent_link);

    void createCheckingGroupOfGoods(CheckingGroupOfGoods checkingGroupOfGoods);

    void createOffenseArticles(OffenseArticles offenseArticles);

    CheckEventSupplemented createEventSupplemented(CheckEventSupplemented checkEventSupplemented);
}
