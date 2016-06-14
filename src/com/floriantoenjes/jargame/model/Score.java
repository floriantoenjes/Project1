package com.floriantoenjes.jargame.model;

public class Score implements Comparable<Score>{
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
    public int compareTo(Score other) {
        int points = this.getPoints();
        int pointsOther = other.getPoints();

        if (points < pointsOther) {
            return 1;
        } else if (points > pointsOther) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return String.format("%s - %d points", getName(), getPoints());
    }
}
