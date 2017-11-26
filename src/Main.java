import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import symonenko.task2.Item;
import symonenko.task2.ShoppingCart;
import symonenko.task2.Ticket;
import tests.AppendFormattedTest;
import tests.CalculateDiscountTest;

import javax.security.auth.callback.TextInputCallback;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Result discountTestResult = new JUnitCore().run(CalculateDiscountTest.class);
        if (!discountTestResult.wasSuccessful()) {
            List<Failure> failureList1 = discountTestResult.getFailures();
            failureList1.forEach(failure -> System.out.println("Failure: " + failure.getMessage()));
        }
        Result appendFormatedTest = new JUnitCore().run(AppendFormattedTest.class);
        if (!appendFormatedTest.wasSuccessful()) {
            List<Failure> failureList1 = appendFormatedTest.getFailures();
            failureList1.forEach(failure -> System.out.println("Failure: " + failure.getMessage()));
        }

        ShoppingCart cart = new ShoppingCart();
        cart.addItem("Apple", 0.99, 5, Item.ItemType.NEW);
        cart.addItem("Banana", 20.00, 4, Item.ItemType.SECOND_FREE);
        cart.addItem("A long piece of toilet paper", 17.20, 1, Item.ItemType.SALE);
        cart.addItem("Nails", 2.00, 500, Item.ItemType.REGULAR);
        System.out.println(cart.formatTicket());

    }
}
