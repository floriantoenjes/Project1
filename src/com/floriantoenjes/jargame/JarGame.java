package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.model.Jar;
import com.floriantoenjes.jargame.model.Score;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public abstract class JarGame {
    protected Jar jar;
    protected Set<Score> scores;
    private Random random;

    public JarGame() {
        random = new Random();
        scores = new TreeSet<>();
    }

    public abstract void play();

    protected abstract void setup();

    protected abstract void startGuessing();

    protected final void fillJar(String content, int maxAmount) {
        int amount = random.nextInt(maxAmount) + 1;
        jar = new Jar(content, amount, maxAmount);
    }
    protected final Guess makeGuess(int guess) {
        int amount = jar.getAmount();
        int maxAmount = jar.getMaxAmount();

        if (guess <= 0 || guess > maxAmount) {
            return Guess.INVALID;
        } else if (guess < amount) {
            return Guess.TOO_LOW;
        } else if (guess > amount) {
            return Guess.TOO_HIGH;
        } else {
            return Guess.CORRECT;
        }

    }

    protected enum Guess {
        INVALID, CORRECT, TOO_LOW, TOO_HIGH

    }

}