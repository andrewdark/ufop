package ua.pp.darknsoft.entity;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import static ua.pp.darknsoft.support.StaticMethod.*;

/**
 * Created by Andrew on 30.01.2017.
 */
@Service
public class Contact {
    private long id;
    private String first_name;
    private String last_name;
    private String patronymic_name;
    private String rntc;
    private String series_of_passport;
    private String number_of_passport;
    private String a_stay_address;
    private String n_stay_address;
    private String f_stay_address;
    private String b_stay_address;
    private String tel;
    private String fax;
    private String email;
    private Timestamp datereg;
    private String creator_link;
    private String birthday;
    private long organization;
    private String sorganization;
    private String position;
    private String description;
    private int nav;

    public int getNav() {
        return nav;
    }

    public void setNav(int nav) {
        this.nav = nav;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = rejectHtml(first_name);
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = rejectHtml(last_name);
    }

    public String getPatronymic_name() {
        return patronymic_name;
    }

    public void setPatronymic_name(String patronymic_name) {
        this.patronymic_name = rejectHtml(patronymic_name);
    }

    public String getRntc() {
        return rntc;
    }

    public void setRntc(String rntc) {
        this.rntc = rejectHtml(rntc);
    }

    public String getSeries_of_passport() {
        return series_of_passport;
    }

    public void setSeries_of_passport(String series_of_passport) {
        this.series_of_passport = rejectHtml(series_of_passport);
    }

    public String getNumber_of_passport() {
        return number_of_passport;
    }

    public void setNumber_of_passport(String number_of_passport) {
        this.number_of_passport = rejectHtml(number_of_passport);
    }

    public String getA_stay_address() {
        return a_stay_address;
    }

    public void setA_stay_address(String a_stay_address) {
        this.a_stay_address = rejectHtml(a_stay_address);
    }

    public String getN_stay_address() {
        return n_stay_address;
    }

    public void setN_stay_address(String n_stay_address) {
        this.n_stay_address = rejectHtml(n_stay_address);
    }

    public String getF_stay_address() {
        return f_stay_address;
    }

    public void setF_stay_address(String f_stay_address) {
        this.f_stay_address = rejectHtml(f_stay_address);
    }

    public String getB_stay_address() {
        return b_stay_address;
    }

    public void setB_stay_address(String b_stay_address) {
        this.b_stay_address = rejectHtml(b_stay_address);
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = rejectHtml(tel);
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = rejectHtml(fax);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = rejectHtml(email);
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public String getCreator_link() {
        return creator_link;
    }

    public void setCreator_link(String creator_link) {
        this.creator_link = rejectHtml(creator_link);
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = rejectHtml(birthday);
    }

    public long getOrganization() {
        return organization;
    }

    public void setOrganization(long organization) {
        this.organization = organization;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = rejectHtml(position);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = rejectHtml(description);
    }

    public String getSorganization() {
        return sorganization;
    }

    public void setSorganization(String sorganization) {
        this.sorganization = rejectHtml(sorganization);
    }
}
