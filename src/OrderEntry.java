public class OrderEntry {
    private String productName;
    private double price;
    private int quantity;
    private Category category;

    enum Category {
        FOOD,
        CLOTHING,
        OTHER
    }

    public OrderEntry(String productName, double price, int quantity, Category category) {
        setProductName(productName);
        setPrice(price);
        setQuantity(quantity);
        setCategory(category);
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
