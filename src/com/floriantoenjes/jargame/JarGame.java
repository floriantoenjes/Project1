package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.exc.EmptyJarException;
import com.floriantoenjes.jargame.model.Jar;
import com.floriantoenjes.jargame.model.Score;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * Defines the bases for a Jar Game to be extended. A jar is filled with a random amount of an item and a player has to guess this amount.
 */
public abstract class JarGame {
    protected Jar jar;
    protected Set<Score> scores;
    private Random random;

    public JarGame() {
        random = new Random();
        scores = new TreeSet<>();
    }

    public abstract void play();

    /**
     * Fills the jar with items of type content and sets the maximum amount that can fit into the jar to maxAmount.
     * @param content The type of item for the jar to be filled with
     * @param maxAmount The maximum amount of items of type content to fit into the jar
     */
    protected final void fillJar(String content, int maxAmount) {
        if (maxAmount <= 0) throw new IllegalArgumentException("The maximum amount must be greater than 0!");
        int amount = random.nextInt(maxAmount) + 1;
        jar = new Jar(content, amount, maxAmount);
    }

    /**
     * Returns a GuessState depending on if the guess that was made equals, is lower, or higher than the amount of items
     * in the jar. If the guess is lesser than 1 or greater than the maximum amount of items in the jar an invalid
     * guess state is returned.
     * @param guess Integer
     * @return  A GuessState.
     * @throws EmptyJarException If jar has not been filled.
     */
    protected final GuessState makeGuess(int guess) throws EmptyJarException {
        if (jar == null) throw new EmptyJarException();

        int amount = jar.getAmount();
        int maxAmount = jar.getMaxAmount();

        if (guess <= 0 || guess > maxAmount) {
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
        INVALID, CORRECT, TOO_LOW, TOO_HIGH
    }

}