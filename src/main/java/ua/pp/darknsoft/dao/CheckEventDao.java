package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.CheckEvent;
import ua.pp.darknsoft.entity.CheckEventSupplemented;

import java.util.List;

/**
 * Created by Dark on 26.03.2017.
 */
public interface CheckEventDao {
    List<CheckEventSupplemented> getCheckEventByUfopLink(long ufop_link);

    CheckEventSupplemented createEventSupplemented(CheckEventSupplemented checkEventSupplemented);
}
