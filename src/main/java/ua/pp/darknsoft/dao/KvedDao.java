package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.EntrepreneursKveds;
import ua.pp.darknsoft.entity.KvedCatalog;

import java.util.List;

/**
 * Created by Andrew on 16.02.2017.
 */
public interface KvedDao {
    void createEntrepreneursKveds(EntrepreneursKveds entrepreneursKveds);

    List<KvedCatalog> getKvedTop();

    List<KvedCatalog> getKvedByTreemark(String treemark, int level);
}
