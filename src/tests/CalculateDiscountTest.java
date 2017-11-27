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

    private Item item;
    private int expected;

    public CalculateDiscountTest(int expected, Item item) {
        this.item = item;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection testData() {
        return Arrays.asList(new Object[][]{
                /*---------- Discount for REGULAR type ----------*/
                {0, new Item("", 1, 1, Item.ItemType.REGULAR)},
                {0, new Item("", 1, 9, Item.ItemType.REGULAR)},
                {1, new Item("", 1, 10, Item.ItemType.REGULAR)},
                {20, new Item("", 1, 200, Item.ItemType.REGULAR)},
                /*---------- Discount for SECOND FREE type ----------*/
                {0, new Item("", 1, 1, Item.ItemType.SECOND_FREE)},
                {50, new Item("", 1, 9, Item.ItemType.SECOND_FREE)},
                {52, new Item("", 1, 25, Item.ItemType.SECOND_FREE)},
                /*---------- Discount for NEW items tests ----------*/
                {0, new Item("", 1, 1, Item.ItemType.NEW)},
                {0, new Item("", 1, 100, Item.ItemType.NEW)},
                /*---------- Discount for FOR SALE items ----------*/
                { 70, new Item("", 1, 1, Item.ItemType.SALE)},
                { 72, new Item("", 1, 20, Item.ItemType.SALE)},
                { 75, new Item("", 1, 50, Item.ItemType.SALE)},
                { 80, new Item("", 1, 200, Item.ItemType.SALE)},
                /*---------- Max possible discount test ----------*/
                { 80, new Item("", 1, 1000, Item.ItemType.SALE)}
        });
    }

    @Test
    public void discountTest()
    {
        Assert.assertEquals(expected, item.calculateDiscount());
    }

}
