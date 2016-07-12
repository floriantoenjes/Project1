package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.model.GameLogic;
import com.floriantoenjes.jargame.view.GameView;

public class Game {
    GameLogic gameLogic = GameLogic.loadGame().orElse(new GameLogic());
    GameView view = new GameView();


    private void start() {
        do {
            setup();
            play();
            view.showScores(gameLogic.getScoreList());
        } while (view.isPlayAgain());
        gameLogic.saveGame();
    }

    private void setup() {
        view.showStartSetup();
        gameLogic.fillJar(view.getItemType(), view.getMaxAmount());
        view.showEndSetup();
    }

    private void play() {
        int guessCount = 1;
        int result = gameLogic.makeGuess(view.getGuess());
        while (result != 0) {
            if (result < 0) {
                view.showTooLow();
            } else {
                view.showTooHigh();
            }
            guessCount++;
        }
        view.showSucceeded(guessCount);
        gameLogic.addScoreToList(view.getPlayerName(), guessCount);
    }
}
