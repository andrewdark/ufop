package ua.pp.darknsoft.entity;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Dark on 11.01.2017.
 */
public class User implements Serializable{
    private String username;
    private String email;
    private String pwd;
    private String confirm_pwd;
    private LocalDate datereg;
    private boolean isEnabled;

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
}
