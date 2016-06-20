package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.exc.EmptyJarException;
import com.floriantoenjes.jargame.model.Score;
import com.floriantoenjes.jargame.util.Prompter;

public class ConsoleJarGame extends JarGame{
    private Prompter prompter;

    public ConsoleJarGame() {
        prompter = new Prompter();
    }

    /**
     * Starts the Console Jar Game by first letting an administrator setup the game and then prompting a player for guesses. The tries of the player are counted until the guess is correct.
     * The player is then asked for his/her name and a score is added to the score list. Finally the score list is shown to the administrator and he/she is prompted if he/she wants to setup a new game.
     */
    @Override
    public void play() {
        printHeader("ADMINISTRATOR");
        while (true) {
            setupGame();

            String content = jar.getContent();
            int amount = jar.getAmount();
            int maxAmount = jar.getMaxAmount();

            int guessCount = 0;
            Guess guess = Guess.INVALID;

            int points;
            String playerName;

            printHeader("PLAYER");
            System.out.printf("Guess how many %s are in the jar. It holds a maximum amount of %d.%n%n", content, maxAmount);

            while (guess != Guess.CORRECT) {
                try {
                    guess = makeGuess(prompter.promptInt("Guess: "));
                } catch (EmptyJarException e) {
                    e.printStackTrace();
                }

                switch (guess) {
                    case INVALID:
                        System.out.printf("You can only guess in a range from 1 to %d.%n", maxAmount);
                        continue;
                    case TOO_LOW:
                        System.out.println("Your guess was too low.");
                        break;
                    case TOO_HIGH:
                        System.out.println("Your guess was too high.");
                        break;
                }
                guessCount++;
            }

            points = maxAmount / guessCount;

            System.out.printf("%nCongratulations - You guessed right. There were %d %s in the jar. This took you %d guess(es).%n", amount, content, guessCount);
            playerName = prompter.prompt("You have %d points. Please enter your name: ", points);
            scores.add(new Score(playerName, points));
            System.out.println();

            printHeader("ADMINISTRATOR");
            printScores();
            if (!prompter.promptForYes("Do you want to setup a new game? Y(es) to continue: ")) {
                break;
            }
        }
        System.out.println("Goodbye!");
    }

    /**
     * Sets up the Jar Game by prompting for jar content and maximum fill-amount.
     */
    private void setupGame() {
        String content;
        int maxAmount;

        content = prompter.prompt("What is in the jar: ");
        while (true) {
            maxAmount = prompter.promptInt("Total amount of %s that fit into the jar: ", content);
            if (maxAmount > 0) {
                break;
            }
            System.out.println("Enter a number greater than 0!");
        }

        fillJar(content, maxAmount);
    }

    private void printHeader(String str) {
        System.out.println(str);
        StringBuilder underline = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            underline.append('-');
        }
        System.out.println(underline);
    }

    private void printScores() {
        printHeader("SCORES");

        int i = 1;
        for (Score score : scores) {
            System.out.printf("%d. %s%n", i++, score);
        }
        System.out.printf("%n%n");
    }

}