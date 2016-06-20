package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.exc.EmptyJarException;
import com.floriantoenjes.jargame.model.Score;
import com.floriantoenjes.jargame.util.Prompter;

public class ConsoleJarGame extends JarGame{

    @Override
    public void play() {
        printHeader("ADMINISTRATOR");
        while (true) {
            int guessCount = 0;
            GuessState guess = GuessState.NOT_MADE;
            int amount;
            int maxAmount;
            int points;
            String content;
            String playerName;

            setupGame();

            content = jar.getContent();
            amount = jar.getAmount();
            maxAmount = jar.getMaxAmount();

            printHeader("PLAYER");
            System.out.printf("Guess how many %s are in the jar. It holds a maximum amount of %d.%n%n", content, maxAmount);

            while (guess != GuessState.CORRECT) {
                try {
                    guess = makeGuess(Prompter.promptInt("Guess: "));
                } catch (EmptyJarException e) {
                    e.printStackTrace();
                }

                switch (guess) {
                    case INVALID:
                        System.out.printf("It has to be in a range from 1 to %d.%n", maxAmount);
                        continue;
                    case TOO_LOW:
                        System.out.println("Too low.");
                        break;
                    case TOO_HIGH:
                        System.out.println("Too high.");
                        break;
                }
                guessCount++;
            }
            System.out.printf("%nCongratulations - You guessed right. There were %d %s in the jar. This took you %d guess(es).%n", amount, content, guessCount);

            points = maxAmount / guessCount;
            playerName = Prompter.prompt("You have %d points. Please enter your name: ", points);
            scores.add(new Score(playerName, points));

            printHeader("ADMINISTRATOR");
            printScores();
            if (!Prompter.promptForYes("Do you want to setup a new game? Y(es) to continue: ")) {
                break;
            }
        }
        System.out.println("Goodbye!");
    }

    private void setupGame() {
        String content;
        int maxAmount;

        content = Prompter.prompt("What is in the jar: ");
        while (true) {
            maxAmount = Prompter.promptInt("Total amount of %s that fit into the jar: ", content);
            if (maxAmount > 0) {
                break;
            } else {
                System.out.println("Enter a number greater than 0!");
            }
        }
        fillJar(content, maxAmount);
    }

    private void printHeader(String str) {
        System.out.printf("%n%s%n", str);
        StringBuilder underline = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            underline.append('-');
        }
        System.out.println(underline);
    }

    private void printScores() {
        int i = 1;

        printHeader("SCORES");
        for (Score score : scores) {
            System.out.printf("%d. %s%n", i++, score);
        }
        System.out.println();
    }

}