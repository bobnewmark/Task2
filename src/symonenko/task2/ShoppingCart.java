package symonenko.task2;

import java.util.ArrayList;
import java.util.List;

/**
 * Containing items and calculating price.
 */
public class ShoppingCart {

    /**
     * Container for added items
     */
    private List<Item> items = new ArrayList<>();
    private Ticket ticket;

    /**
     * Adds new item.
     *
     * @param title    item title 1 to 32 symbols
     * @param price    item ptice in USD, > 0
     * @param quantity item quantity, from 1
     * @param type     item type
     * @throws IllegalArgumentException if some value is wrong
     */
    public void addItem(String title, double price, int quantity, Item.ItemType type) {
        if (title == null || title.length() == 0 || title.length() > 32)
            throw new IllegalArgumentException("Illegal title");
        if (price < 0.01)
            throw new IllegalArgumentException("Illegal price");
        if (quantity <= 0)
            throw new IllegalArgumentException("Illegal quantity");
        items.add(new Item(title, price, quantity, type));
    }

    /**
     * Gets shopping info in organized format.
     */
    public String formatTicket() {
        ticket = new Ticket(items);
        return ticket.getPrintedFormat();
    }

    /**
     * Calculates item's discount.
     * For NEW item discount is 0%;
     * For SECOND_FREE item discount is 50% if quantity > 1
     * For SALE item discount is 70%
     * For each full 10 not NEW items item gets additional 1% discount,
     * but not more than 80% total
     */
    public static int calculateDiscount(Item.ItemType type, int quantity) {
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
}