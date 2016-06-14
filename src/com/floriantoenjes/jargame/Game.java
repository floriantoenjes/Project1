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
        String scoreFilePath = "./scores.ser";
        String input;

        while (true) {
            printScores();
            fillJar();
            startGuessing();

            while (true) {
                input = prompter.prompt("Do you want to set up a new game? Y(es) to continue | N(o) to exit: ")
                        .trim()
                        .toLowerCase();

                if (input.length() > 0) {
                    if (input.charAt(0) == 'y') {
                        break;
                    } else if (input.charAt(0) == 'n') {
                        System.out.println("Goodbye!");
                        return;
                    }
                }
            }
        }
    }


    private void fillJar() {
        String itemType;
        int itemAmount;
        int itemAmountTotal;

        System.out.println("ADMINISTRATOR");
        System.out.println("-------------");

        itemType = prompter.prompt("What is in the jar: ");
        itemAmountTotal = prompter.promptInt("Total amount of %s that fit into the jar: ", itemType);
        itemAmount = random.nextInt(itemAmountTotal) + 1;

        jar = new Jar(itemType, itemAmount, itemAmountTotal);
    }

    private void startGuessing() {
        String itemType = jar.getItemType();
        int itemAmount = jar.getItemAmount();
        int itemAmountTotal = jar.getItemAmountTotal();

        int guess = 0;
        int guessCount = 0;

        int points;
        String playerName;

        System.out.println("PLAYER");
        System.out.println("------");
        System.out.printf("Guess how many %s are in the jar. It holds a maximum amount of %d.%n%n", itemType, itemAmountTotal);

        while (guess != itemAmount) {
            guess = prompter.promptInt("Guess: ");

            if (guess < 1 || guess > itemAmountTotal){
                System.out.printf("You can only guess in a range from 1 to %d.%n", itemAmountTotal);
                continue;
            } else if (guess < itemAmount) {
                System.out.println("Your guess was too low.");
            } else if (guess > itemAmount){
                System.out.println("Your guess was too high.");
            }

            guessCount++;
        }

        points = itemAmountTotal / guessCount;

        System.out.printf("%nCongratulations - You guessed right. There were %d %s in the jar. This took you %d guess(es).%n", itemAmount, itemType, guessCount);

        playerName = prompter.prompt("You have %d points. Please enter your name: ", points);
        System.out.println();

        addScore(new Score(playerName, points));
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
        int i = 1;

        if (scores.size() == 0) {
            return;
        }

        System.out.println("SCORES");
        System.out.println("------");
        for (Score score : scores) {
            System.out.printf("%d. %s%n", i, score);
            i++;
        }
        System.out.printf("%n%n");
    }
}