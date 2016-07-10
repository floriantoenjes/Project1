package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.model.Jar;
import com.floriantoenjes.jargame.model.Score;
import com.floriantoenjes.jargame.view.GameView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;


public class Game implements Serializable{
    private static final long serialVersionUID = 3278543306890766237L;
    private Jar jar;
    private final GameView view = new GameView();
    private final Random random = new Random();
    private final List<Score> scoreList = new ArrayList<>();

    public static void main(String[] args) {
        Game game = loadGame().orElse(new Game());
        game.start();
    }

    private void start() {
        do {
            setup();
            play();
            view.showScores(scoreList);
        } while (view.isPlayAgain());
        saveGame();
        view.exitGame();
        System.exit(0);
    }

    private void setup() {
        view.showStartSetup();
        String itemType = view.getItemType();
        int maxAmount = view.getMaxAmount();
        fillJar(itemType, maxAmount);
        view.showEndSetup();
    }

    private void fillJar(String itemType, int maxAmount) {
        int amount = random.nextInt(maxAmount) + 1;
        if (jar == null) {
            jar = new Jar(itemType, amount, maxAmount);
        } else {
            jar.fill(itemType, amount, maxAmount);
        }
    }

    private void play() {
        String content = jar.getContent();
        int amount = jar.getAmount();
        int maxAmount = jar.getMaxAmount();
        int guessCount = 1;

        view.showPlaying(content, maxAmount);
        int guess = view.makeGuess();
        while (guess != amount) {
            if (guess < amount) {
                view.showTooLow();
            } else {
                view.showTooHigh();
            }
            guess = view.makeGuess();
            guessCount++;
        }

        view.showSucceeded(guessCount, maxAmount);
        Score score = new Score(view.getPlayerName(), maxAmount, guessCount);
        scoreList.add(score);
    }

    private void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("JarGame.ser"))) {
            oos.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Optional<Game> loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("JarGame.ser"))) {
            return Optional.of((Game) ois.readObject());
        } catch (Exception e) {
            return Optional.empty();
        }

    }
}
