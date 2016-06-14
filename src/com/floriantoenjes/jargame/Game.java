package com.floriantoenjes.jargame;

import com.floriantoenjes.jargame.model.Jar;
import com.floriantoenjes.jargame.model.Score;
import com.floriantoenjes.jargame.util.Prompter;

import java.util.*;

public class Game {
    private Jar jar;
    private List<Score> scores;
    private Prompter prompter;
    private Random random;

    private Game() {
        prompter = new Prompter();
        random = new Random();
        scores = new ArrayList<>();
    }

    public static void main(String[] args) {
        new Game().play();
    }

    private void play() {
        boolean playAgain = true;
        while (playAgain) {
            if (scores.size() > 0) {
                printScores();
            }

            fillJar();
            startGuessing();

            playAgain = prompter.promptYesNo("Do you want to setup a new game? Y(es) to continue | N(o) to exit: ");
        }
        System.out.println("Goodbye!");
    }


    private void fillJar() {
        String content;
        int amount;
        int maxAmount;

        printHeading("ADMINISTRATOR");

        content = prompter.prompt("What is in the jar: ");
        while (true) {
            maxAmount = prompter.promptInt("Total amount of %s that fit into the jar: ", content);
            if (maxAmount > 0) {
                break;
            }
            System.out.println("Enter a number greater than 0!");
        }
        amount = random.nextInt(maxAmount) + 1;

        jar = new Jar(content, amount, maxAmount);

        System.out.println();
    }

    private void startGuessing() {
        String content = jar.getContent();
        int amount = jar.getAmount();
        int maxAmount = jar.getMaxAmount();

        int guess = 0;
        int guessCount = 0;

        int points;
        String playerName;

        printHeading("PLAYER");
        System.out.printf("Guess how many %s are in the jar. It holds a maximum amount of %d.%n%n", content, maxAmount);

        while (guess != amount) {
            guess = prompter.promptInt("Guess: ");

            if (guess < 1 || guess > maxAmount){
                System.out.printf("You can only guess in a range from 1 to %d.%n", maxAmount);
                continue;
            } else if (guess < amount) {
                System.out.println("Your guess was too low.");
            } else if (guess > amount){
                System.out.println("Your guess was too high.");
            }

            guessCount++;
        }

        points = maxAmount / guessCount;

        System.out.printf("%nCongratulations - You guessed right. There were %d %s in the jar. This took you %d guess(es).%n", amount, content, guessCount);
        playerName = prompter.prompt("You have %d points. Please enter your name: ", points);
        addScore(new Score(playerName, points));

        System.out.println();
    }

    private void addScore(Score score) {
        scores.add(score);
        Collections.sort(scores);
    }

    private void printHeading(String str) {
        System.out.println(str);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            sb.append("-");
        }
        System.out.println(sb.toString());
    }

    private void printScores() {
        printHeading("SCORES");
        for (int i = 1; i <= scores.size(); i++) {
            System.out.printf("%d. %s%n", i, scores.get(i-1));
        }
        System.out.printf("%n%n");
    }
}