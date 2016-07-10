package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.model.Jar;
import com.floriantoenjes.jargame.model.Score;
import com.floriantoenjes.jargame.view.GameView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Game {
    private Jar jar;
    private GameView view = new GameView();
    private Random random = new Random();
    List<Score> scoreList = new ArrayList<>();

    public static void main(String[] args) {
        new Game().start();
    }

    public void start() {
        do {
            setup();
            play();
            view.showScores(scoreList);
        } while (view.playAgain());
        view.exitGame();
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

        Score score = new Score(view.setPlayerName(), maxAmount, guessCount);
        scoreList.add(score);
        view.showSucceeded(score);
    }
}
