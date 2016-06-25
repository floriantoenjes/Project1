package com.floriantoenjes.jargame.model;

import java.util.Optional;

public class Jar {
    private String content;
    private int amount;
    private int maxAmount;

    public Jar() {
    }

    public Jar(String content, int amount, int maxAmount) {
        fill(content, amount, maxAmount);
    }

    public void fill(String content, int amount, int maxAmount) {
        this.content = content;
        this.amount = amount;
        this.maxAmount = maxAmount;
    }

    public Optional<String> getContent() {
        return Optional.ofNullable(content);
    }

    public int getAmount() {
        return amount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

}
