package ua.pp.darknsoft.entity;

import java.sql.Timestamp;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Andrew on 09.03.2017.
 */
public class Ufop {
    private long id;
    private short ufop_is;
    private String ufop_name;
    private String ufop_code;
    private String series_of_passport;
    private String number_of_passport;
    private String a_place_of_reg;
    private String n_place_of_reg;
    private String f_place_of_reg;
    private String b_place_of_reg;
    private Timestamp datereg;
    private int creator_link;
    private String screator_link;
    private String description;
    private boolean additionalinformation;
    private int ufop_nav;
    private String last_event;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public short getUfop_is() {
        return ufop_is;
    }

    public void setUfop_is(short ufop_is) {
        this.ufop_is = ufop_is;
    }

    public String getUfop_name() {
        return ufop_name;
    }

    public void setUfop_name(String ufop_name) {
        this.ufop_name = rejectHtml(ufop_name);
    }

    public String getUfop_code() {
        return ufop_code;
    }

    public void setUfop_code(String ufop_code) {
        this.ufop_code = rejectHtml(ufop_code);
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
        this.number_of_passport = rejectHtml(number_of_passport).toString();
    }

    public String getA_place_of_reg() {
        return a_place_of_reg;
    }

    public void setA_place_of_reg(String a_place_of_reg) {
        this.a_place_of_reg = rejectHtml(a_place_of_reg);
    }

    public String getN_place_of_reg() {
        return n_place_of_reg;
    }

    public void setN_place_of_reg(String n_place_of_reg) {
        this.n_place_of_reg = rejectHtml(n_place_of_reg);
    }

    public String getF_place_of_reg() {
        return f_place_of_reg;
    }

    public void setF_place_of_reg(String f_place_of_reg) {
        this.f_place_of_reg = rejectHtml(f_place_of_reg);
    }

    public String getB_place_of_reg() {
        return b_place_of_reg;
    }

    public void setB_place_of_reg(String b_place_of_reg) {
        this.b_place_of_reg = rejectHtml(b_place_of_reg);
    }

    public Timestamp getDatereg() {
        return datereg;
    }

    public void setDatereg(Timestamp datereg) {
        this.datereg = datereg;
    }

    public int getCreator_link() {
        return creator_link;
    }

    public void setCreator_link(int creator_link) {
        this.creator_link = creator_link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = rejectHtml(description);
    }

    public boolean isAdditionalinformation() {
        return additionalinformation;
    }

    public void setAdditionalinformation(boolean additionalinformation) {
        this.additionalinformation = additionalinformation;
    }

    public String getScreator_link() {
        return screator_link;
    }

    public void setScreator_link(String screator_link) {
        this.screator_link = rejectHtml(screator_link);
    }

    public int getUfop_nav() { return ufop_nav; }

    public void setUfop_nav(int ufop_nav) { this.ufop_nav = ufop_nav; }

    public String getLast_event() {
        return last_event;
    }

    public void setLast_event(String last_event) {
        this.last_event = rejectHtml(last_event);
    }


}
