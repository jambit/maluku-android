package com.jambit.maluku.android.maluku.android.malukuandroidapp.model;

import java.util.UUID;

public class User {

    private String name;
    private String room;
    private String id;

    public User(String name, String room) {
        this.name = name;
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void generateUserId(){
        id = UUID.randomUUID().toString();
    }
}
