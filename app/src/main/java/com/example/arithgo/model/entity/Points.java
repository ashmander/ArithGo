package com.example.arithgo.model.entity;

public class Points {

    private String id;
    private int points;

    public Points(String id, int points) {
        this.id = id;
        this.points = points;
    }

    public Points() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
