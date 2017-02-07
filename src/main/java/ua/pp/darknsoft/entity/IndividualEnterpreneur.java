package ua.pp.darknsoft.entity;

import java.time.LocalDate;

/**
 * Created by Andrew on 30.01.2017.
 */
public class IndividualEnterpreneur {
    private Long id;
    private Long contact_link;
    private String a_place_of_reg;
    private String n_place_of_reg;
    private String rntc;
    private String series_of_passport;
    private String number_of_passport;
    private int risk_group;
    private boolean if_pdf;
    private LocalDate datereg;
    private String Owner;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContact_link() {
        return contact_link;
    }

    public void setContact_link(Long contact_link) {
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

    public boolean isIf_pdf() {
        return if_pdf;
    }

    public void setIf_pdf(boolean if_pdf) {
        this.if_pdf = if_pdf;
    }

    public LocalDate getDatereg() {
        return datereg;
    }

    public void setDatereg(LocalDate datereg) {
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
}
