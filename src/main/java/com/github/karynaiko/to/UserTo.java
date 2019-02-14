package com.github.karynaiko.to;

import com.github.karynaiko.model.User;

import java.io.Serializable;
import java.util.Date;

public class UserTo implements Serializable {
    private int id;

    private String firstName;

    private String secondName;

    private String password;

    private String email;

    private String token;

    public UserTo() {

    }

    public UserTo(User user) {
        id = user.getId();
        firstName = user.getFirstName();
        secondName = user.getSecondName();
        email = user.getEmail();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
