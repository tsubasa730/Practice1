import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AssistantServiceTest {

    private AssitantService service;
    private Order order_CA;
    private Order order_NY;
    private OrderEntry food;
    private OrderEntry clothing;
    private OrderEntry other;
    private List<OrderEntry> orderEntryList;

    @Before
    public void setUp() {
        service = new AssitantService();
        order_CA = new Order(Order.Location.CALIFORNIA);
        order_NY = new Order(Order.Location.NEWYORK);
        food = new OrderEntry("potato chips", 3.99, 1, OrderEntry.Category.FOOD);
        clothing = new OrderEntry("shirt", 29.99, 1, OrderEntry.Category.CLOTHING);
        other = new OrderEntry("book", 17.99, 1, OrderEntry.Category.OTHER);
        orderEntryList = new ArrayList<>();
        orderEntryList.add(food);
        orderEntryList.add(clothing);
        orderEntryList.add(other);
        order_CA.setOrderEntries(orderEntryList);
        order_NY.setOrderEntries(orderEntryList);
    }

    @Test
    public void isTaxFreeForCA() {
        Assert.assertTrue(service.isTaxFree(order_CA.getLocation(), food.getCategory()));
        Assert.assertFalse(service.isTaxFree(order_CA.getLocation(), clothing.getCategory()));
        Assert.assertFalse(service.isTaxFree(order_CA.getLocation(), other.getCategory()));

    }

    @Test
    public void isTaxFreeForNY() {
        Assert.assertTrue(service.isTaxFree(order_NY.getLocation(), food.getCategory()));
        Assert.assertTrue(service.isTaxFree(order_NY.getLocation(), clothing.getCategory()));
        Assert.assertFalse(service.isTaxFree(order_NY.getLocation(), other.getCategory()));
    }

    @Test
    public void calculateTaxForCA() {
        BigDecimal tax = service.calculateTax(order_CA);
        Assert.assertEquals(BigDecimal.valueOf(4.75).setScale(2), tax);
    }

    @Test
    public void calculateTaxForNY() {
        BigDecimal tax = service.calculateTax(order_NY);
        Assert.assertEquals(BigDecimal.valueOf(1.60).setScale(2), tax);
    }
}