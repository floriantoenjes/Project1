package com.floriantoenjes.jargame.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prompter {

    public static String prompt(String prompt, Object... args) {
        String str = "";

        System.out.printf(prompt, args);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            str = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static int promptInt(String prompt, Object... args) {
        try {
            return Integer.parseInt(prompt(prompt, args).trim());
        } catch (NumberFormatException nfe) {
            return 0;
        }
    }


    public static boolean promptForYes(String prompt, Object... args) {
        String input;

        input = prompt(prompt)
                .trim()
                .toLowerCase();

        if (input.length() > 0) {
            if (input.charAt(0) == 'y') {
                return true;
            }
        }
        return false;
    }
}
