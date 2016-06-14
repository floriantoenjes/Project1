package com.floriantoenjes.jargame;

import java.io.Serializable;

public class Score {
    private String name;
    private int points;

    public Score(String name, int points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return String.format("%s - %d points", getName(), getPoints());
    }
}
