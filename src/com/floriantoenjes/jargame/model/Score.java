package com.floriantoenjes.jargame.model;

import java.io.Serializable;

public class Score implements Comparable<Score>, Serializable{
    static final long serialVersionUID = 1L;
    private int maxAmount;

    private int guesscount;
    private String name;
    private int points;
    public Score(String name, int maxAmount, int guessCount) {
        this.name = name;
        this.guesscount = guessCount;
        this.maxAmount = maxAmount;
        this.points = maxAmount / guessCount;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public int getGuesscount() {
        return guesscount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Score score = (Score) o;

        return points == score.points && name.equals(score.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + points;
        return result;
    }

    @Override
    public int compareTo(Score other) {
        int pointsOther = other.points;

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
        return String.format("%s - %d points", name, points);
    }
}
