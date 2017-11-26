package tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import symonenko.task2.Item;
import symonenko.task2.ShoppingCart;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalculateDiscountTest {

    private Item.ItemType type;
    private int quantity;
    private int expected;

    public CalculateDiscountTest(int expected, Item.ItemType type, int quantity) {
        this.type = type;
        this.quantity = quantity;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                /*---------- Discount for REGULAR type ----------*/
                {0, Item.ItemType.REGULAR,  1},
                {0, Item.ItemType.REGULAR,  9},
                {1, Item.ItemType.REGULAR,  10},
                {20, Item.ItemType.REGULAR,  200},
                /*---------- Discount for SECOND FREE type ----------*/
                {0, Item.ItemType.SECOND_FREE,  1},
                {50, Item.ItemType.SECOND_FREE,  9},
                {51, Item.ItemType.SECOND_FREE,  15},
                /*---------- Discount for NEW items tests ----------*/
                {0, Item.ItemType.NEW,  1},
                {0, Item.ItemType.NEW,  100},
                /*---------- Discount for FOR SALE items ----------*/
                { 70, Item.ItemType.SALE,  1},
                { 72, Item.ItemType.SALE,  20},
                { 75, Item.ItemType.SALE,  50},
                { 80, Item.ItemType.SALE,  200},
                /*---------- Max possible discount test ----------*/
                { 80, Item.ItemType.SALE,  1000}
        });
    }

    @Test
    public void discountTest()
    {
        Assert.assertEquals(expected, ShoppingCart.calculateDiscount(type, quantity));
    }

}
