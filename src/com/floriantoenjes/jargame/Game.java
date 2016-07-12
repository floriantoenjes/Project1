package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.model.GameLogic;
import com.floriantoenjes.jargame.model.GameLogic.GuessState;
import com.floriantoenjes.jargame.view.GameView;

public class Game {
    private static final GameLogic gameLogic = GameLogic.loadGame().orElse(new GameLogic());
    private static final GameView view = new GameView();

    public static void main(String[] args) {
        Game.start();
    }

    private static void start() {
        do {
            setup();
            play();
            view.showScores(gameLogic.getScoreList());
        } while (view.isPlayAgain());
        gameLogic.saveGame();
        view.showExitGame();
        System.exit(0);
    }

    private static void setup() {
        view.showStartSetup();
        gameLogic.fillJar(view.getItemType(), view.getMaxAmount());
        view.showEndSetup();
    }

    private static void play() {
        view.showPlaying(gameLogic.getJarContent(), gameLogic.getJarMaxAmount());
        int guessCount = 1;
        GuessState result = gameLogic.makeGuess(view.getGuess());
        while (result != GuessState.CORRECT) {
            switch (result) {
                case TOO_LOW:
                    view.showTooLow();
                    break;
                case TOO_HIGH:
                    view.showTooHigh();
                    break;
            }
            result = gameLogic.makeGuess(view.getGuess());
            guessCount++;
        }
        view.showSucceeded(guessCount);
        gameLogic.addScoreToList(view.getPlayerName(), guessCount);
    }
}
