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
        items.add(new Item(title, price, quantity, type));
    }

    /**
     * Gets shopping info in organized format.
     */
    public String formatTicket() {
        ticket = new Ticket(items);
        return ticket.getPrintedFormat();
    }

}