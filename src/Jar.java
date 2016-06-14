public class Jar {
    private String itemType;
    private int itemAmount;
    private int itemAmountTotal;

    public String getItemType() {
        return itemType;
    }

    public int getItemAmount() {
        return itemAmount;
    }

    public int getItemAmountTotal() {
        return itemAmountTotal;
    }

    public Jar(String itemType, int itemAmount, int itemAmountTotal) {
        this.itemType = itemType;
        this.itemAmount = itemAmount;
        this.itemAmountTotal = itemAmountTotal;
    }
}
