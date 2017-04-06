package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.KvedCatalog;
import ua.pp.darknsoft.entity.KvedsUfop;

import java.util.List;

/**
 * Created by Andrew on 16.02.2017.
 */
public interface KvedDao {
    void createEntrepreneursKveds(KvedsUfop kvedsUfop);

    List<KvedCatalog> getKvedCatalogTop();

    List<KvedCatalog> getKvedCatalogByTreemark(String treemark, int level);

    List<KvedsUfop> getKvedsByUfopLink(long ufop_link);

    void deleteKvedsByUfopLink(long id);
}
