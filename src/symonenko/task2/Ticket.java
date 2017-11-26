package symonenko.task2;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Ticket {

    // --- private section -----------------------------------------------------
    private static final NumberFormat MONEY;
    private List<Item> items;
    private int[] align = new int[]{1, -1, 1, 1, 1, 1};
    private String[] header = {"#", "Item", "Price", "Quan.", "Discount", "Total"};
    private String[] footer;
    private List<String[]> lines = new ArrayList<>();
    private int[] width = new int[]{0, 0, 0, 0, 0, 0};
    private int lineLength;
    private StringBuilder sb = new StringBuilder();

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        MONEY = new DecimalFormat("$#.00", symbols);
    }

    // constructor
    public Ticket(List<Item> items) {
        this.items = items;
    }

    /**
     * Formats shopping price.
     *
     * @return string as lines, separated with \n,
     * first line: # Item Price Quan. Discount Total
     * second line: ---------------------------------------------------------
     * next lines: NN Title $PP.PP Q DD% $TT.TT
     * 1 Some title $.30 2 - $.60
     * 2 Some very long $100.00 1 50% $50.00
     * ...
     * 31 Item 42 $999.00 1000 - $999000.00
     * end line: ---------------------------------------------------------
     * last line: 31 $999050.60
     * <p>
     * if no items in cart returns "No items." string.
     */
    public String getPrintedFormat() {
        if (items.size() == 0)
            return "No items.";

        prepareLines();
        countColumnLengths();
        countLineLength();

        formatHeader();
        formatLines();
        formatFooter();

        return sb.toString();
    }

    // column max length
    private void countColumnLengths() {
        for (String[] line : lines)
            for (int i = 0; i < line.length; i++)
                width[i] = (int) Math.max(width[i], line[i].length());
        for (int i = 0; i < header.length; i++)
            width[i] = (int) Math.max(width[i], header[i].length());
        for (int i = 0; i < footer.length; i++)
            width[i] = (int) Math.max(width[i], footer[i].length());
    }

    // line length
    private void countLineLength() {
        lineLength = width.length - 1;
        for (int w : width)
            lineLength += w;
    }

    // header
    private void formatHeader() {
        for (int i = 0; i < header.length; i++)
            appendFormatted(sb, header[i], align[i], width[i]);
        sb.append("\n");
        addSeparator(sb, lineLength);
    }

    // lines
    private void formatLines() {
        for (String[] line : lines) {
            for (int i = 0; i < line.length; i++)
                appendFormatted(sb, line[i], align[i], width[i]);
            sb.append("\n");
        }
    }

    // footer
    private void formatFooter() {
        addSeparator(sb, lineLength);
        for (int i = 0; i < footer.length; i++)
            appendFormatted(sb, footer[i], align[i], width[i]);
    }

    // formatting each line
    private void prepareLines() {
        double total = 0.00;
        int index = 0;
        for (Item item : items) {
            int discount = ShoppingCart.calculateDiscount(item.getType(), item.getQuantity());
            double itemTotal = item.getPrice() * item.getQuantity() * (100.00 - discount) / 100.00;
            lines.add(new String[]{
                    String.valueOf(++index),
                    item.getTitle(),
                    MONEY.format(item.getPrice()),
                    String.valueOf(item.getQuantity()),
                    (discount == 0) ? "-" : (String.valueOf(discount) + "%"),
                    MONEY.format(itemTotal)
            });
            total += itemTotal;
        }
        footer = new String[]{String.valueOf(index), "", "", "", "", MONEY.format(total)};
    }

    // separator
    private static void addSeparator(StringBuilder sb, int lineLength) {
        for (int i = 0; i < lineLength; i++)
            sb.append("-");
        sb.append("\n");
    }

    /**
     * Appends to sb formatted value.
     * Trims string if its length > width.
     *
     * @param align -1 for align left, 0 for center and +1 for align right.
     */
    public static void appendFormatted(StringBuilder sb, String value, int align, int width) {
        if (value.length() > width)
            value = value.substring(0, width);
        int before = (align == 0)
                ? (width - value.length()) / 2
                : (align == -1) ? 0 : width - value.length();
        int after = width - value.length() - before;
        while (before-- > 0)
            sb.append(" ");
        sb.append(value);
        while (after-- > 0)
            sb.append(" ");
        sb.append(" ");
    }


}
