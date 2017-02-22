package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

/**
 * Created by Andrew on 30.01.2017.
 */
public class IndividualEntrepreneur {
    private long id;
    private long contact_link;
    private Contact contact;
    private String a_place_of_reg;
    private String n_place_of_reg;
    private String rntc;
    private String series_of_passport;
    private String number_of_passport;
    private int risk_group;
    private boolean if_pdv;
    private Timestamp datereg;
    private String Owner;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getContact_link() {
        return contact_link;
    }

    public void setContact_link(long contact_link) {
        this.contact_link = contact_link;
    }

    public String getA_place_of_reg() {
        return a_place_of_reg;
    }

    public void setA_place_of_reg(String a_place_of_reg) {
        this.a_place_of_reg = a_place_of_reg;
    }

    public String getN_place_of_reg() {
        return n_place_of_reg;
    }

    public void setN_place_of_reg(String n_place_of_reg) {
        this.n_place_of_reg = n_place_of_reg;
    }

    public String getRntc() {
        return rntc;
    }

    public void setRntc(String rntc) {
        this.rntc = rntc;
    }

    public String getSeries_of_passport() {
        return series_of_passport;
    }

    public void setSeries_of_passport(String series_of_passport) {
        this.series_of_passport = series_of_passport;
    }

    public String getNumber_of_passport() {
        return number_of_passport;
    }

    public void setNumber_of_passport(String number_of_passport) {
        this.number_of_passport = number_of_passport;
    }

    public int getRisk_group() {
        return risk_group;
    }

    public void setRisk_group(int risk_group) {
        this.risk_group = risk_group;
    }

    public boolean isIf_pdv() {
        return if_pdv;
    }

    public void setIf_pdv(boolean if_pdv) {
        this.if_pdv = if_pdv;
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Contact getContact() { return contact; }

    public void setContact(Contact contact) { this.contact = contact; }

}
