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

    public void addScoreToList(String playerName, int guessCount) {
        Score score = new Score(playerName, jar.getMaxAmount(), guessCount);
        Collections.sort(scoreList);
        scoreList.add(score);
    }

    public List<Score> getScoreList() {
        return scoreList;
    }

    public int makeGuess(int guess) {
        int amount = jar.getAmount();
        if (guess == amount) {
            return 0;
        } else if (guess < amount) {
            return -1;
        } else {
            return 1;
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
}
