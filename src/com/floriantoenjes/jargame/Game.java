package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.model.Jar;
import com.floriantoenjes.jargame.model.Score;
import com.floriantoenjes.jargame.view.GameView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game implements Serializable{
    public static final long serialVersionUID = 1L;
    private Jar jar;
    private GameView view = new GameView();
    private Random random = new Random();
    private List<Score> scoreList = new ArrayList<>();

    public static void main(String[] args) {
        Game game = loadGame();
        if (game == null) {
            game = new Game();
        }
        game.start();
    }

    public void start() {
        do {
            setup();
            play();
            view.showScores(scoreList);
        } while (view.isPlayAgain());
        saveGame();
        view.exitGame();
        System.exit(0);
    }

    public void setup() {
        view.showStartSetup();
        String itemType = view.getItemType();
        int maxAmount = view.getMaxAmount();
        fillJar(itemType, maxAmount);
        view.showEndSetup();
    }

    public void fillJar(String itemType, int maxAmount) {
        int amount = random.nextInt(maxAmount) + 1;
        jar = new Jar(itemType, amount,maxAmount);
    }

    public void play() {
        int amount = jar.getAmount();
        int maxAmount = jar.getMaxAmount();
        int guessCount = 1;

        view.showPlaying();
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

    public void saveGame() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("JarGame.ser"))) {
            oos.writeObject(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Game loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("JarGame.ser"))) {
            return (Game) ois.readObject();
        } catch (Exception e) {
            return null;
        }

    }
}
