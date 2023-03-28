package com.example.camera;

import java.io.Serializable;

public class helperclass implements Serializable {
    String name,email,password;
    String messege;

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public helperclass() {

    }

    public helperclass(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.messege="opp";
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
