package com.jambit.maluku.android.maluku.android.malukuandroidapp.model;

public class Person {

    private String name;
    private String floor;

    public Person(String name, String floor) {
        this.name = name;
        this.floor = floor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
