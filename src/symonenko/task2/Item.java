package symonenko.task2;

public class Item {
    private String title;
    private double price;
    private int quantity;
    private ItemType type;

    public enum ItemType { NEW, REGULAR, SECOND_FREE, SALE };

    public Item(String title, double price, int quantity, ItemType type) {
        setTitle(title);
        setPrice(price);
        setQuantity(quantity);
        setType(type);
    }

    public Item() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
