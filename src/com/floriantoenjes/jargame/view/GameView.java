package com.floriantoenjes.jargame.view;

import com.floriantoenjes.jargame.model.Score;
import com.floriantoenjes.util.Prompter;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GameView implements Serializable{
    private static final long serialVersionUID = 6444666024001777071L;

    public void showStartSetup() {
        System.out.println("Setting up game");
        System.out.println("Enter what type of item the jar should be filled with and the maximum amount that fits " +
                "into the jar.");
    }

    public String getItemType() {
        return Prompter.prompt("Item Type> ");
    }

    public int getMaxAmount() {
        return Prompter.promptInt("Max Amount> ");
    }

    public void showEndSetup() {
        System.out.printf("GameLogic has been setup%n%n");
    }

    public void showPlaying(String content, int maxAmount) {
        System.out.println("Playing Game");
        System.out.printf("A jar has been filled with %s. It can hold a maximum of %d. Guess how many are in the jar.%n",
                content, maxAmount);
    }

    public int getGuess() {
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

    public void showSucceeded(int guessCount) {
        System.out.printf("You won the game! It took you %d attempts.%n", guessCount);
    }

    public void showScores(List<Score> scoreList) {
        System.out.println("High Scores");
        scoreList.forEach(System.out::println);
    }

    public boolean isPlayAgain() {
        return Prompter.promptForYes("Do you want to play again? Y(es)> ");
    }

    public void exitGame() {
        System.out.println("Exiting...");
    }
}
