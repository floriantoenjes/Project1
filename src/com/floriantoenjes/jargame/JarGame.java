package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.exc.EmptyJarException;
import com.floriantoenjes.jargame.model.Jar;
import com.floriantoenjes.jargame.model.Score;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

public abstract class JarGame {
    private final Random random = new Random();
    protected final Set<Score> scores = new TreeSet<>();
    protected final Jar jar = new Jar();

    public abstract void play();

    protected final void fillJar(String content, int maxAmount) {
        if (maxAmount < 1) {
            throw new IllegalArgumentException("The maximum amount must be greater than 0!");
        }
        int amount = random.nextInt(maxAmount) + 1;
            jar.fill(content, amount, maxAmount);
    }

    protected final GuessState makeGuess(int guess) throws EmptyJarException {
        jar.getContent().orElseThrow(() -> new EmptyJarException("The jar has to be filled to make a guess"));

        int amount = jar.getAmount();
        int maxAmount = jar.getMaxAmount();

        if (guess < 1 || guess > maxAmount) {
            return GuessState.INVALID;
        } else if (guess < amount) {
            return GuessState.TOO_LOW;
        } else if (guess > amount) {
            return GuessState.TOO_HIGH;
        } else {
            return GuessState.CORRECT;
        }

    }

    protected enum GuessState {
        NOT_MADE, CORRECT, TOO_LOW, TOO_HIGH, INVALID
    }

}