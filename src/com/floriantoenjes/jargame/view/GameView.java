package com.floriantoenjes.jargame.view;

import com.floriantoenjes.jargame.model.Score;
import com.floriantoenjes.util.Prompter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GameView implements Serializable{
    static final long serialVersionUID = 1L;

    public int getMaxAmount() {
        return Prompter.promptInt("Max Amount> ");
    }

    public void showStartSetup() {
        System.out.println("Setting up game");
        System.out.println("Enter what type of item the jar should be filled with and the maximum amount that fits " +
                "into the jar.");
    }

    public String getItemType() {
        return Prompter.prompt("Item Type> ");
    }

    public void showEndSetup() {
        System.out.println("Game has been setup");
    }

    public void showPlaying() {
        System.out.println("Playing Game");
    }

    public int makeGuess() {
        return Prompter.promptInt("Guess> ");
    }

    public void showTooLow() {
        System.out.println("Too low!");
    }

    public void showTooHigh() {
        System.out.println("Too high!");
    }

    public String getPlayerName() {
        return Prompter.prompt("Your name> ");
    }

    public void showSucceeded(int guessCount, int maxAmount) {
        System.out.printf("You won the game! It took you %d attempts.%n", guessCount);
    }

    public void showScores(List<Score> scoreList) {
        System.out.println("High Scores");
        Collections.sort(scoreList);
        scoreList.forEach(System.out::println);
    }

    public boolean isPlayAgain() {
        return Prompter.promptForYes("Do you want to play again? Y(es)> ");
    }

    public void exitGame() {
        System.out.println("Exiting...");
    }
}
