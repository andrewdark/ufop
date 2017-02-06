package ua.pp.darknsoft.entity;

import java.sql.Timestamp;
import java.sql.Date;

/**
 * Created by Andrew on 30.01.2017.
 */
public class Contact {
    private Long id;
    private String first_name;
    private String last_name;
    private String patronymic_name;
    private String rntc;
    private String series_of_passport;
    private String number_of_passport;
    private String a_stay_address;
    private String n_stay_address;
    private String tel;
    private String fax;
    private String email;
    private Timestamp datereg;
    private String owner;
    private String birthday;
    private String organization;
    private String position;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic_name() {
        return patronymic_name;
    }

    public void setPatronymic_name(String patronymic_name) {
        this.patronymic_name = patronymic_name;
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

    public String getA_stay_address() {
        return a_stay_address;
    }

    public void setA_stay_address(String a_stay_address) {
        this.a_stay_address = a_stay_address;
    }

    public String getN_stay_address() {
        return n_stay_address;
    }

    public void setN_stay_address(String n_stay_address) {
        this.n_stay_address = n_stay_address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public String getOwner() { return owner; }

    public void setOwner(String owner) { this.owner = owner; }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
