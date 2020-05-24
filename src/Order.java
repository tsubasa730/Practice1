import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    enum Location {
        CALIFORNIA,
        NEWYORK
    }

    private Map<Location, Double> taxRateMap;
    private List<OrderEntry> orderEntries;
    private double taxRate;
    private Location location;

    public double getTaxRateByLocation(Location location) {
        return taxRateMap.get(location);
    }

    public void setTaxRateMap(Map<Location, Double> taxRateMap) {
        this.taxRateMap = taxRateMap;
    }

    public void setOrderEntries(List<OrderEntry> orderEntries) {
        this.orderEntries = orderEntries;
    }

    public List<OrderEntry> getOrderEntries() {
        return orderEntries;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        for (OrderEntry oe: orderEntries) {
            subtotal = subtotal.add(BigDecimal.valueOf(oe.getPrice()).multiply(BigDecimal.valueOf(oe.getQuantity())));
        }
        return subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public Order(Location location) {
        Map<Location, Double> taxRateMap = new HashMap<>();
        taxRateMap.put(Location.CALIFORNIA, 0.0975);
        taxRateMap.put(Location.NEWYORK, 0.08875);
        setTaxRateMap(taxRateMap);
        setLocation(location);
        setTaxRate(getTaxRateByLocation(location));
    }

}
