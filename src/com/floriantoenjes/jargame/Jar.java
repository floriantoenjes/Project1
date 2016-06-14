package com.floriantoenjes.jargame;

public class Jar {
    private String content;
    private int amount;
    private int maxAmount;

    public String getContent() {
        return content;
    }

    public int getAmount() {
        return amount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public Jar(String itemType, int itemAmount, int itemAmountTotal) {
        this.content = itemType;
        this.amount = itemAmount;
        this.maxAmount = itemAmountTotal;
    }
}
