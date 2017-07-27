package ua.pp.darknsoft.entity;

import java.io.Serializable;
import java.time.LocalDate;
import static ua.pp.darknsoft.support.StaticMethod.*;
/**
 * Created by Dark on 11.01.2017.
 */
public class User implements Serializable{
    private int id;
    private String username;
    private String email;
    private String pwd;
    private String confirm_pwd;
    private LocalDate datereg;
    private boolean isEnabled;
    private long contact_link;
    private String structure_link;
    private String role_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getConfirm_pwd() {
        return confirm_pwd;
    }

    public void setConfirm_pwd(String confirm_pwd) {
        this.confirm_pwd = confirm_pwd;
    }

    public LocalDate getDatereg() {
        return datereg;
    }

    public void setDatereg(LocalDate datereg) {
        this.datereg = datereg;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public long getContact_link() {
        return contact_link;
    }

    public void setContact_link(long contact_link) {
        this.contact_link = contact_link;
    }

    public String getStructure_link() {
        return structure_link;
    }

    public void setStructure_link(String structure_link) {
        this.structure_link = rejectHtml(structure_link);
    }

    public String getRole_name() { return role_name; }

    public void setRole_name(String role_name) { this.role_name = rejectHtml(role_name); }
}
