public class Drug {

    private int drugID; // Primary Key
    private String name;
    private String category;
    private int stock;

    public Drug(int drugID, String name, String category, int stock) {
        this.drugID = drugID;
        this.name = name;
        this.category = category;
        this.stock = stock;
    }

    // Getter for drugID
    public int getDrugID() {
        return drugID;
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
