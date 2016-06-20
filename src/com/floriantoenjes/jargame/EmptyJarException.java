package com.floriantoenjes.jargame;

public class EmptyJarException extends Exception {
    public EmptyJarException() {
    }

    public EmptyJarException(String message) {
        super(message);
    }
}
