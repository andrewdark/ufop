package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.CauseCatalog;
import ua.pp.darknsoft.entity.LocationCatalog;
import ua.pp.darknsoft.entity.LocationType;

import java.util.List;

/**
 * Created by Andrew on 16.01.2017.
 */
public interface CatalogDao {
    List<LocationType> getLocationType();

    List<LocationCatalog> getLocationTop();

    List<LocationCatalog> getLocationByTreemark(String treemark, int level);

    List<LocationCatalog> getParentLocationByTreemark(String treemark);

    List<CauseCatalog> getCauseCatalogByType(short type);
}
