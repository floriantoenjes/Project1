package com.floriantoenjes.jargame;

import java.io.Serializable;

public class Score implements Serializable{
    private static final long serialVersionUID = 2219221442025174878L;
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
