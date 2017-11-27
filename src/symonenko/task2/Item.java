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

    /**
     * Calculates item's discount.
     * For NEW item discount is 0%;
     * For SECOND_FREE item discount is 50% if quantity > 1
     * For SALE item discount is 70%
     * For each full 10 not NEW items item gets additional 1% discount,
     * but not more than 80% total
     */
    public int calculateDiscount() {
        int discount = 0;
        switch (type) {
            case NEW:
                return 0;
            case REGULAR:
                discount = 0;
                break;
            case SECOND_FREE:
                if (quantity > 1)
                    discount = 50;
                break;
            case SALE:
                discount = 70;
                break;
        }
        discount += quantity / 10;
        if (discount > 80)
            discount = 80;
        return discount;
    }

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
