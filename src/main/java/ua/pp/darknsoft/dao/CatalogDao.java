package ua.pp.darknsoft.dao;

import ua.pp.darknsoft.entity.*;

import java.util.List;

/**
 * Created by Andrew on 16.01.2017.
 */
public interface CatalogDao {
    List<User> getInspectorsBySelectorStructureLink(String str);

    List<DegreeRisk> getDegreeRiskCatalog();

    List<PrecautionCatalog> getPrecautionCatalog();

    List<LawsuitsResultCatalog> getLawsuitsResultCatalog();

    List<LocationType> getLocationType();

    List<ArticlesLawCatalog> getArticleTop();

    List<LocationCatalog> getLocationTop();

    List<BasicGroupOfGoodsCatalog> getGoodsTop();

    List<LocationCatalog> getLocationByTreemark(String treemark, int level);

    List<ArticlesLawCatalog> getArticlesByTreemark(String treemark, int level);

    List<BasicGroupOfGoodsCatalog> getGoodsByTreemark(String treemark, int level);

    List<LocationCatalog> getParentLocationByTreemark(String treemark);

    List<CauseCatalog> getCauseCatalogByType(short type);

    List<StructureCatalog> getMyStructureByMyStatus(String username);
}
