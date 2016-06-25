package com.floriantoenjes.jargame.model;

public class Jar {
    private String content;
    private int amount;
    private int maxAmount;

    public Jar(String content, int amount, int maxAmount) {
        fill(content, amount, maxAmount);
    }

    public void fill(String content, int amount, int maxAmount) {
        if (amount < 1) {
            throw new IllegalArgumentException("The amount must be greater than 0!");
        } else if (amount > maxAmount) {
            throw new IllegalArgumentException("The amount cannot be greater than the maximum amount!");
        }

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
