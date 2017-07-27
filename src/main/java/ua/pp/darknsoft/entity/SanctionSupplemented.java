package ua.pp.darknsoft.entity;

import java.util.List;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Andrew on 30.03.2017.
 */
public class SanctionSupplemented extends Sanction{
    private List<PunishmentArticles> punishmentArticlesList;

    public List<PunishmentArticles> getPunishmentArticlesList() {
        return punishmentArticlesList;
    }

    public void setPunishmentArticlesList(List<PunishmentArticles> punishmentArticlesList) {
        this.punishmentArticlesList = punishmentArticlesList;
    }
}
