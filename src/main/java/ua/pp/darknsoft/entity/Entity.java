package ua.pp.darknsoft.entity;

import java.time.LocalDate;

/**
 * Created by Andrew on 21.02.2017.
 */
public class Entity {
    private long id;
    private String name;
    private String code_edrpoy;
    private String a_place_of_reg;
    private String character;
    private LocalDate datereg;
    private String owner;
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode_edrpoy() {
        return code_edrpoy;
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

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public LocalDate getDatereg() {
        return datereg;
    }

    public void setDatereg(LocalDate datereg) {
        this.datereg = datereg;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
