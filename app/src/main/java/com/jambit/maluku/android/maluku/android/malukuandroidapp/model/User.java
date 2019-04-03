package com.jambit.maluku.android.maluku.android.malukuandroidapp.model;


public class User {

    private String name;
    private String id;
    private String raum;

    public User() {
        // Required empty public constructor
    }

    public User(String name, String id, String raum) {
        this.name = name;
        this.id = id;
        this.raum = raum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRaum() {
        return raum;
    }

    public void setRaum(String raum) {
        this.raum = raum;
    }
}