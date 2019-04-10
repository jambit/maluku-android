package com.jambit.maluku.android.maluku.android.malukuandroidapp.model;

public class Foosball {

    private boolean isOccupied;
    private int id;
    private boolean invalid;
    private double averageTimeUsed;
    private String start;
    private String stop;

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public double getAverageTimeUsed() {
        return averageTimeUsed;
    }

    public void setAverageTimeUsed(double averageTimeUsed) {
        this.averageTimeUsed = averageTimeUsed;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }
}