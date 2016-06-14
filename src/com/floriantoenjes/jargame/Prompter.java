package com.floriantoenjes.jargame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prompter {
    private BufferedReader reader;

    public Prompter() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String prompt(String prompt, Object... args) {
        String str = "";

        System.out.printf(prompt, args);
        try {
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public int promptInt(String prompt, Object... args) {
        while (true) {
            try {
                return Integer.parseInt(prompt(prompt, args));
            } catch (NumberFormatException nfe) {
                System.out.println("Enter a number!");
            }
        }
    }

    public boolean promptYesNo (String prompt, Object... args) {
        String input;

        while (true) {
            input = prompt(prompt)
                    .trim()
                    .toLowerCase();

            if (input.length() > 0) {
                if (input.charAt(0) == 'y') {
                    return true;
                } else if (input.charAt(0) == 'n') {
                    return false;
                }
            }
        }
    }
}
