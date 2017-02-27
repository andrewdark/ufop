package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.CommercialObjectType;
import ua.pp.darknsoft.entity.EntrepreneurCommercialObject;

import java.util.List;

/**
 * Created by Andrew on 20.02.2017.
 */
public interface CommercialObjectDao {
    void createCommObj(EntrepreneurCommercialObject entrepreneurCommercialObject);

    List<CommercialObjectType> getCommObjType();

    List<EntrepreneurCommercialObject> getCommObjEntrepreneurByUfop_link(long ufop_link);
}
