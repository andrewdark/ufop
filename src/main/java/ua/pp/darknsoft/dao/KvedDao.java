package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.EntrepreneursKveds;
import ua.pp.darknsoft.entity.KvedCatalog;

import java.util.List;

/**
 * Created by Andrew on 16.02.2017.
 */
public interface KvedDao {
    void createEntrepreneursKveds(EntrepreneursKveds entrepreneursKveds);

    List<KvedCatalog> getKvedCatalogTop();

    List<KvedCatalog> getKvedCatalogByTreemark(String treemark, int level);

    List<EntrepreneursKveds> getEntrepreneursKvedsByEntrepreneurLink(long e_link);
}
