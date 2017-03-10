package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.CommercialObject;
import ua.pp.darknsoft.entity.CommercialObjectType;

import java.util.List;

/**
 * Created by Andrew on 20.02.2017.
 */
public interface CommercialObjectDao {
    void createCommObj(CommercialObject commercialObject);

    List<CommercialObjectType> getCommObjType();

    List<CommercialObject> getCommObjEntrepreneurByUfop_link(long ufop_link);
}
