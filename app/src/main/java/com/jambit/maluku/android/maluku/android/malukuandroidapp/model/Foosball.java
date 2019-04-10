package com.jambit.maluku.android.maluku.android.malukuandroidapp.model;

public class Foosball {

    private boolean isOccupied;
    private int id;
    private boolean invalid;
    private double averageTimeUsed;
    private double start;
    private double stop;

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

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getStop() {
        return stop;
    }

    public void setStop(double stop) {
        this.stop = stop;
    }
}