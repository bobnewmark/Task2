import org.junit.Before;
import org.junit.Test;
import symonenko.task2.ShoppingCart;

public class AppendFormattedTest extends junit.framework.TestCase {

    private ShoppingCart cart;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
        cart.addItem("Apple", 0.99, 5, ShoppingCart.ItemType.NEW);
        cart.addItem("Banana", 20.00, 4, ShoppingCart.ItemType.SECOND_FREE);
        cart.addItem("A long piece of toilet paper", 17.20, 1, ShoppingCart.ItemType.SALE);
        cart.addItem("Nails", 2.00, 500, ShoppingCart.ItemType.REGULAR);
    }

    @Test
    public void testAppendFormattedTicket() {
        assertEquals("# Item                          Price Quan. Discount   Total \n" +
                        "------------------------------------------------------------\n" +
                        "1 Apple                          $.99     5        -   $4.95 \n" +
                        "2 Banana                       $20.00     4      50%  $40.00 \n" +
                        "3 A long piece of toilet paper $17.20     1      70%   $5.16 \n" +
                        "4 Nails                         $2.00   500      50% $500.00 \n" +
                        "------------------------------------------------------------\n" +
                        "4                                                    $550.11 ",
                cart.formatTicket());
    }

    @Test
    public void testAppendFormattedLine() {
        StringBuilder sb = new StringBuilder();
        String number = "1";
        String itemName = "Apple";
        String price = "$.99";
        String quantity = "5";
        String discount = "-";
        String total = "$4.95";
        ShoppingCart.appendFormatted(sb, number, -1, number.length());
        assertEquals("1 ", sb.toString());
        ShoppingCart.appendFormatted(sb, itemName, 1, itemName.length());
        assertEquals("1 Apple ", sb.toString());
        ShoppingCart.appendFormatted(sb, price, 1, price.length());
        assertEquals("1 Apple $.99 ", sb.toString());
        ShoppingCart.appendFormatted(sb, quantity, 1, quantity.length());
        assertEquals("1 Apple $.99 5 ", sb.toString());
        ShoppingCart.appendFormatted(sb, discount, 1, discount.length());
        assertEquals("1 Apple $.99 5 - ", sb.toString());
        ShoppingCart.appendFormatted(sb, total, 1, total.length());
        assertEquals("1 Apple $.99 5 - $4.95 ", sb.toString());
    }

    @Test
    public void testAppendToLeft() {
        StringBuilder sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "left", -1, 10);
        assertEquals(11, sb.toString().length());
        assertEquals(true, sb.toString().startsWith("left"));
        assertEquals(true, sb.toString().endsWith("     "));
    }

    @Test
    public void testAppendToRight() {
        StringBuilder sb = new StringBuilder();
        ShoppingCart.appendFormatted(sb, "right", 1, 10);
        assertEquals(11, sb.toString().length());
        assertEquals(true, sb.toString().endsWith("right "));
        assertEquals(true, sb.toString().startsWith("    "));
    }

}