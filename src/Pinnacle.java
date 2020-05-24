import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class Pinnacle {

    public static void printResult(List<OrderEntry> orderEntryList, BigDecimal tax, BigDecimal subtotal) {
        Formatter formatter = new Formatter(System.out);
        formatter.format("%-15s %15s %15s\n\n", "item", "price", "qty");
        for (OrderEntry oe: orderEntryList) {
            formatter.format("%-15s %15s %15s\n", oe.getProductName(), "$"+oe.getPrice(), oe.getQuantity());
        }
        formatter.format("%-15s %15s %15s\n", "subtotal:", "", "$"+subtotal);
        formatter.format("%-15s %15s %15s\n", "tax:", "", "$"+tax);
        formatter.format("%-15s %15s %15s\n", "total:", "", "$"+tax.add(subtotal));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter Location of the Order (CALIFORNIA or NEWYORK)");
        String location = reader.readLine();
        System.out.println("Please enter number of items you will purchase");
        int itemsNum = Integer.parseInt(reader.readLine());
        // create order with entered order entry
        Order order = new Order(Order.Location.valueOf(location));
        AssitantService service = new AssitantService();
        List<OrderEntry> orderEntryList = new ArrayList<>();
        for(int i=0; i<itemsNum; i++) {
            System.out.println("Please enter information of item " + (i + 1) + ": item name, price, quantity and category(FOOD, CLOTHING or OTHER in order");
            orderEntryList.add(service.purchaseItem(reader.readLine(),
                    Double.parseDouble(reader.readLine()),
                    Integer.parseInt(reader.readLine()),
                    OrderEntry.Category.valueOf(reader.readLine())));
        }
        order.setOrderEntries(orderEntryList);

        // print result
        printResult(orderEntryList, service.calculateTax(order), order.getSubtotal());
    }
}
