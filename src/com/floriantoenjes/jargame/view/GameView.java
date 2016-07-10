package com.floriantoenjes.jargame.view;

import com.floriantoenjes.jargame.model.Score;
import com.floriantoenjes.util.Prompter;

import java.util.Collections;
import java.util.List;

public class GameView {
    String playerName;

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

    public String setPlayerName() {
        playerName = Prompter.prompt("Your name> ");
        return playerName;
    }

    public void showSucceeded(Score score) {
        System.out.printf("%s won the game! It took him %d attempts with a score of %d%n", playerName, score.getGuesscount(), score.getPoints());
    }

    public void showScores(List<Score> scoreList) {
        System.out.println("High Scores");
        Collections.sort(scoreList);
        scoreList.forEach(System.out::println);
    }

    public boolean playAgain() {
        return Prompter.promptForYes("Do you want to play again? Y(es)> ");
    }

    public void exitGame() {
        System.out.println("Exiting...");
        System.exit(0);
    }
}
