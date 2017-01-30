package ua.pp.darknsoft.entity;

/**
 * Created by Andrew on 30.01.2017.
 */
public class KvedUfop {
    private int id;
    private int individual_enterpreneur_link;
    private int kved_catalog_link;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIndividual_enterpreneur_link() {
        return individual_enterpreneur_link;
    }

    public void setIndividual_enterpreneur_link(int individual_enterpreneur_link) {
        this.individual_enterpreneur_link = individual_enterpreneur_link;
    }

    public int getKved_catalog_link() {
        return kved_catalog_link;
    }

    public void setKved_catalog_link(int kved_catalog_link) {
        this.kved_catalog_link = kved_catalog_link;
    }
}
