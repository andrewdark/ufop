package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.IndividualEntrepreneur;

import java.util.List;

/**
 * Created by Andrew on 08.02.2017.
 */
public interface IndividualEntrepreneurDao {
    List<IndividualEntrepreneur> getEntrepreneur(int total, int pageid);

    String SelectIEByRntc(String rntc);

    long insertIE(IndividualEntrepreneur ie);
}
