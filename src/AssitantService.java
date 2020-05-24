import java.math.BigDecimal;
import java.util.List;

public class AssitantService {

    public BigDecimal calculateTax(Order order) {
        BigDecimal tax = BigDecimal.ZERO;
        Order.Location location = order.getLocation();
        List<OrderEntry> orderEntryList = order.getOrderEntries();
        for (OrderEntry oe: orderEntryList) {
            if (!isTaxFree(location, oe.getCategory())) {
                tax = tax.add(BigDecimal.valueOf(Math.ceil(oe.getPrice() * oe.getQuantity() * order.getTaxRate() * 20.0) / 20.0));
            }
        }
        return tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public boolean isTaxFree(Order.Location location, OrderEntry.Category category) {
        boolean result = false;
        if (location.equals(Order.Location.CALIFORNIA)) {
            if (category.equals(OrderEntry.Category.FOOD))
                result = true;
        } else {
            if (category.equals(OrderEntry.Category.FOOD) || category.equals(OrderEntry.Category.CLOTHING))
                result = true;
        }
        return result;
    }

    public OrderEntry purchaseItem(String productName, double price, int quantity, OrderEntry.Category category) {
        return new OrderEntry(productName, price, quantity, category);
    }
}
