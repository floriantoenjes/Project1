package com.floriantoenjes.jargame.model;

import com.floriantoenjes.jargame.view.GameView;

import java.io.*;
import java.util.*;


public class GameLogic implements Serializable{
    private static final long serialVersionUID = 3278543306890766237L;
    private final Random random = new Random();
    private final List<Score> scoreList = new ArrayList<>();
    private Jar jar;

    public void fillJar(String itemType, int maxAmount) {
        int amount = random.nextInt(maxAmount) + 1;
        if (jar == null) {
            jar = new Jar(itemType, amount, maxAmount);
        } else {
            jar.fill(itemType, amount, maxAmount);
        }
    }

    public String getJarContent() {
        return jar.getContent();
    }

    public int getJarMaxAmount() {
        return jar.getMaxAmount();
    }

    public void addScoreToList(String playerName, int guessCount) {
        Score score = new Score(playerName, jar.getMaxAmount(), guessCount);
        Collections.sort(scoreList);
        scoreList.add(score);
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public GuessState makeGuess(int guess) {
        int amount = jar.getAmount();
        if (guess == amount) {
            return GuessState.CORRECT;
        } else if (guess < amount) {
            return GuessState.TOO_LOW;
        } else {
            return GuessState.TOO_HIGH;
        }
    }

    public void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("JarGame.ser"))) {
            oos.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Optional<GameLogic> loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("JarGame.ser"))) {
            return Optional.of((GameLogic) ois.readObject());
        } catch (Exception e) {
            return Optional.empty();
        }

    }

    public enum GuessState{
        TOO_LOW, TOO_HIGH, CORRECT
    }
}
