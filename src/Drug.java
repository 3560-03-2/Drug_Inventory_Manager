public class Drug {

    private String name;
    private String category;
    private int stock;

    public Drug(String name, String category, int stock) {
        this.name = name;
        this.category = category;
        this.stock = stock;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for category
    public String getCategory() {
        return category;
    }

    // Getter for stock
    public int getStock() {
        return stock;
    }
}