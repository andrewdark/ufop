package ua.pp.darknsoft.entity;

import java.sql.Timestamp;

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
    private String description;

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
        this.ufop_name = ufop_name;
    }

    public String getUfop_code() {
        return ufop_code;
    }

    public void setUfop_code(String ufop_code) {
        this.ufop_code = ufop_code;
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

    public String getF_place_of_reg() {
        return f_place_of_reg;
    }

    public void setF_place_of_reg(String f_place_of_reg) {
        this.f_place_of_reg = f_place_of_reg;
    }

    public String getB_place_of_reg() {
        return b_place_of_reg;
    }

    public void setB_place_of_reg(String b_place_of_reg) {
        this.b_place_of_reg = b_place_of_reg;
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
        this.description = description;
    }
}
