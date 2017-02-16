package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.IndividualEntrepreneur;

/**
 * Created by Andrew on 08.02.2017.
 */
public interface IndividualEntrepreneurDao {
    String SelectIEByRntc(String rntc);

    long insertIE(IndividualEntrepreneur ie);
}
