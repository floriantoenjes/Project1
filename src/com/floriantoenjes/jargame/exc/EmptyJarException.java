package com.floriantoenjes.jargame.exc;

public class EmptyJarException extends Exception {
    public EmptyJarException() {
    }

    public EmptyJarException(String message) {
        super(message);
    }
}
