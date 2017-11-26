import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import symonenko.task2.ShoppingCart;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalculateDiscountTest {

    private ShoppingCart.ItemType type;
    private int quantity;
    private int expected;

    public CalculateDiscountTest(int expected,ShoppingCart.ItemType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                /*---------- Discount for REGULAR type ----------*/
                {0, ShoppingCart.ItemType.REGULAR,  1},
                {0, ShoppingCart.ItemType.REGULAR,  9},
                {1, ShoppingCart.ItemType.REGULAR,  10},
                {20, ShoppingCart.ItemType.REGULAR,  200},
                /*---------- Discount for SECOND FREE type ----------*/
                {0, ShoppingCart.ItemType.SECOND_FREE,  1},
                {50, ShoppingCart.ItemType.SECOND_FREE,  9},
                {51, ShoppingCart.ItemType.SECOND_FREE,  15},
                /*---------- Discount for NEW items tests ----------*/
                {0, ShoppingCart.ItemType.NEW,  1},
                {0, ShoppingCart.ItemType.NEW,  100},
                /*---------- Discount for FOR SALE items ----------*/
                { 70, ShoppingCart.ItemType.SALE,  1},
                { 72, ShoppingCart.ItemType.SALE,  20},
                { 75, ShoppingCart.ItemType.SALE,  50},
                { 80, ShoppingCart.ItemType.SALE,  200},
                /*---------- Max possible discount test ----------*/
                { 80, ShoppingCart.ItemType.SALE,  1000}
        });
    }

    @Test
    public void discountTest()
    {
        Assert.assertEquals(expected, ShoppingCart.calculateDiscount(type, quantity));
    }

}
