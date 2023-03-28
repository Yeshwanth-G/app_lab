package com.example.camera;

import java.io.Serializable;

public class Messege implements Serializable {
    String messege;

    public Messege(String messege) {
        this.messege = messege;
    }
    public Messege(){

    }
    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }
}
