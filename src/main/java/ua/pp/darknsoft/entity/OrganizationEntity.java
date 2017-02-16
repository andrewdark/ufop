package ua.pp.darknsoft.entity;

import java.time.LocalDate;

/**
 * Created by Andrew on 15.02.2017.
 */
public class OrganizationEntity {
    private long id;
    private String Name;
    private String code_edrpoy;
    private String a_place_of_reg;
    private String n_place_of_reg;
    private LocalDate datereg;
    private String Owner;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode_edrpoy() {
        return code_edrpoy;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setCode_edrpoy(String code_edrpoy) {
        this.code_edrpoy = code_edrpoy;
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
