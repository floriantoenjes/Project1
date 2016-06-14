package com.floriantoenjes.jargame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        System.out.println("ADMINISTRATOR");
        System.out.println("-------------");

        content = prompter.prompt("What is in the jar: ");
        maxAmount = prompter.promptInt("Total amount of %s that fit into the jar: ", content);
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

        System.out.println("PLAYER");
        System.out.println("------");
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
        scores.sort((score1, score2) -> {
            int points1 = score1.getPoints();
            int points2 = score2.getPoints();

            if (points1 < points2) {
                return 1;
            } else if (points1 > points2) {
                return -1;
            } else {
                return 0;
            }
        });
    }

    private void printScores() {
        System.out.println("SCORES");
        System.out.println("------");
        for (int i = 1; i <= scores.size(); i++) {
            System.out.printf("%d. %s%n", i, scores.get(i-1));
        }
        System.out.printf("%n%n");
    }
}