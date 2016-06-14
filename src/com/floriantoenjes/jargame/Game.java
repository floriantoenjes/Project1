package com.floriantoenjes.jargame;

import java.io.*;
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

        loadScores(scoreFilePath);

        while(true) {
            printScores();
            fillJar();
            startGuessing();

            input = prompter.promptForString("Do you want to set up a new game? Type Y(es) to continue: ");
            if (input.trim().toLowerCase().equals("y")) {
                continue;
            }
            break;
        }

        saveScores(scoreFilePath);
    }


    private void fillJar() {
        String itemType;
        int itemAmount;
        int itemAmountTotal;

        System.out.println("ADMINISTRATOR");
        System.out.println("-------------");

        itemType = prompter.promptForString("What is in the jar: ");
        itemAmountTotal = prompter.promptForInt("Total amount of %s that fit into the jar: ", itemType);
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
        System.out.printf("Guess how many %s are in the jar. It holds a maximum amount of %d.%n", itemType, itemAmountTotal);

        while (guess != itemAmount) {
            guess = prompter.promptForInt("Guess: ");

            if (guess > itemAmountTotal){
                System.out.println("You cannot guess higher than the maximum amount!");
                continue;
            } else if (guess < itemAmount) {
                System.out.println("Your guess was too low!");
            } else if (guess > itemAmount){
                System.out.println("Your guess was too high!");
            }

            guessCount++;
        }

        points = itemAmountTotal / guessCount;

        System.out.printf("%nCongratulations - You guessed right. There were %d %s in the jar. This took you %d guess(es).%n", itemAmount, itemType, guessCount);

        playerName = prompter.promptForString("You have %d points. Please enter your name: ", points);
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

    private void saveScores(String scoreFilePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(scoreFilePath))) {
            oos.writeObject(scores);
            System.out.printf("Scores were saved to %s", scoreFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadScores(String scoreFilePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(scoreFilePath))) {
            scores = (List<Score>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.printf("No score file at path \"%s\" found.%n%n", scoreFilePath);
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

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