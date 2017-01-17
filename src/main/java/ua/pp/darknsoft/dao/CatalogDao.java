package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.LocationType;

import java.util.List;

/**
 * Created by Andrew on 16.01.2017.
 */
public interface CatalogDao {
    List<LocationType> getLocationType();
}
