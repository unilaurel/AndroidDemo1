package com.example.testmodule.model;

import java.io.Serializable;

public class User implements Serializable {

    String title;

    public User(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public class BasicInfo implements Serializable {
    }
}
