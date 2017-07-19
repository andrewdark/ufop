package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.CheckEvent;

import java.util.List;

/**
 * Created by Andrew on 19.07.2017.
 */
public interface SearchUniversalDao {
    List<CheckEvent> checkEventList(CheckEvent checkEvent, int total, int pageid);
}
