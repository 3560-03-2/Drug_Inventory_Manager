import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Methods {
    
    // use case for logging a sale
    public static void logSale(Date date, String items, String receipt, String customer){

    }

    // use case for logging a delivery
    public static void logDelivery(Date date, String items){

    }

    // use case for logging a return
    public static void logReturn(Date date, String reason, String receipt, String customer){

    }

    // use case for finding a drug
    public static List<Drug> findDrug(String name, String category) {
        List<Drug> results = new ArrayList<>();
        String sqlQuery;

        try {
            MyJDBC myjdbc = new MyJDBC();

            // Build the SQL query based on input
            if (!name.isEmpty() && !category.isEmpty()) {
                sqlQuery = String.format(
                    "SELECT * FROM drug WHERE name LIKE '%%%s%%' AND category LIKE '%%%s%%';",
                    name, category
                );
            } else if (!name.isEmpty()) {
                sqlQuery = String.format("SELECT * FROM drug WHERE name LIKE '%%%s%%';", name);
            } else if (!category.isEmpty()) {
                sqlQuery = String.format("SELECT * FROM drug WHERE category LIKE '%%%s%%';", category);
            } else {
                System.out.println("Both name and category are empty!");
                return results;
            }

            // Execute the query and process the results
            String sqlResult = myjdbc.returnTable(sqlQuery);
            System.out.println("Matching drugs:\n" + sqlResult);

        } catch (Exception e) {
            System.out.println("Error finding drug: " + e.getMessage());
        }

        return results; // Empty list for now (can be enhanced later)
    }

    //use case for logging a drug in system
    public static void logDrug(String name, String desc) {
        //add given drug with it's description to the system
    }

    //use case for reordering drugs
    public static void reorderDrug(String name) {
        //invoke when particular drug stock falls below a certain threshold
        //reorder drug specified in parameter
    }

    //use case for adding a supplier into system
    public static void addSupplier(String name, String contactInfo, String address) {
        //add supplier and their information to the system
        //will create a new "supplier"
    }

    //use case for removing supplier from system
    public static void removeSupplier(String name) {
        //remove supplier of name specified in parameter
        //notify user if their searched supplier is not in the system and can't be removed
    }

    //use case for searching suppliers logged in system
    public static String searchSupplier(String query) {
        //find suppliers that match search query in parameter
        //return string of suppliers and their information that match query, if any
    }
}