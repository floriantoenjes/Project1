package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.exc.EmptyJarException;
import com.floriantoenjes.jargame.model.Score;
import com.floriantoenjes.jargame.util.Prompter;

public class ConsoleJarGame extends JarGame{
    private Prompter prompter;

    public ConsoleJarGame() {
        prompter = new Prompter();
    }

    @Override
    public void play() {
        while (true) {
            if (scores.size() > 0) {
                printScores();
            }

            setup();
            try {
                startGuessing();
            } catch (EmptyJarException e) {
                e.printStackTrace();
            }

            if (!prompter.promptYesNo("Do you want to setup a new game? Y(es) to continue: ")) {
                break;
            }
        }
        System.out.println("Goodbye!");
    }

    @Override
    protected void setup() {
        String content;
        int maxAmount;

        printHeader("ADMINISTRATOR");

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

    @Override
    protected void startGuessing() throws EmptyJarException {
        if (jar == null) throw new EmptyJarException();

        String content = jar.getContent();
        int amount = jar.getAmount();
        int maxAmount = jar.getMaxAmount();

        int guessCount = 0;
        Guess guess = null;

        int points;
        String playerName;

        printHeader("PLAYER");
        System.out.printf("Guess how many %s are in the jar. It holds a maximum amount of %d.%n%n", content, maxAmount);

        while (guess != Guess.CORRECT) {
            guess = makeGuess(prompter.promptInt("Guess: "));

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