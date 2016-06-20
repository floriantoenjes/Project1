package com.floriantoenjes.jargame.model;

public class Jar {
    private final String content;
    private final int amount;
    private final int maxAmount;

    public Jar(String content, int amount, int maxAmount) {
        this.content = content;
        this.amount = amount;
        this.maxAmount = maxAmount;
    }

    public String getContent() {
        return content;
    }

    public int getAmount() {
        return amount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

}
