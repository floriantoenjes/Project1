package com.floriantoenjes.jargame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prompter {
    private BufferedReader reader;

    public Prompter() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String promptForString(String prompt, Object... args) {
        String str = "";

        while (true) {
            System.out.printf(prompt, args);
            try {
                str = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (str.trim().isEmpty()) {
                System.out.println("Please enter something!");
            } else {
                break;
            }
        }
        return str;
    }

    public int promptForInt(String prompt, Object... args) {
        int x;

        while (true) {
            try {
                x = Integer.parseInt(promptForString(prompt, args));
                if (x == 0) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException iae) {
                System.out.println("Please enter a whole number greater than zero!");
            }
        }
        return x;
    }
}
