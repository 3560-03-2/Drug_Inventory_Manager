import java.util.*;
import java.io.IOException;
import java.sql.*;

public class Methods {
    
    private static String jbdcUrl = "jdbc:mysql://localhost:3306/mydb";
    private static String user = "root";
    private static String pass = "password";

    // use case for logging a sale
    public static void logSale(String receipt){
        try {
            ReceiptItems recieptItems = ReceiptReader.read(receipt); // parses through receipt file and stores data in receiptItems
    
            // store data from receipt
            String customer = recieptItems.getCustomer(); // customer string used to search customer table for corresponding customerID
            String items = String.join(", ", recieptItems.getItems()); // receiptReader reads items as a list of strings, so I join the list here to be stored in the table.
            java.util.Date date = recieptItems.getDate();
    
            // convert javaUtilDate into sqlDate
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    
            // prepare sql query for adding sale
            String sqlString2 = "INSERT INTO sale (date, items, receipt, Pharmacist_pharmacistID, Customers_customerID, Drug_drugID, Drug_Supplier_supplierID) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";

            //TODO NEED METHOD TO SEARCH CUSTOMERS TABLE AND RETURN A CUSTOMER ID GIVEN A CUSTOMER'S NAME

            //add sale to the system. this method protects against sql injection but needs testing
            try (Connection connection = DriverManager.getConnection(jbdcUrl, user, pass); // connect to database
            // prepare statement for execution
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString2)) {

                // parameters for prepared statement
                preparedStatement.setDate(1, sqlDate);
                preparedStatement.setString(2, items);
                preparedStatement.setString(3, receipt);
                preparedStatement.setInt(4, 1); // temporary foreign key, pharmacist
                preparedStatement.setInt(5, 1); // temporary foreign key, customer
                preparedStatement.setInt(6, 1); // temporary foreign key, drug 
                preparedStatement.setInt(7, 1); // temporary foreign key, drug supplier

                // execute query
                int rows = preparedStatement.executeUpdate();
                System.out.println("Inserted " + rows + " rows.");

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // use case for logging a delivery
    public static void logDelivery(java.util.Date date, String items){

    }

    // use case for logging a return
    public static void logReturn(String reason, String receipt){

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
            //String sqlResult = myjdbc.returnTable(sqlQuery);
            //System.out.println("Matching drugs:\n" + sqlResult);

            List<List<String>> drugList = new ArrayList<List<String>>();
            drugList = myjdbc.returnData(sqlQuery);
            for (List<String> row : drugList) {
                Drug drug = new Drug(
                    Integer.parseInt(row.get(0)),
                    row.get(1),
                    row.get(2),
                    Integer.parseInt(row.get(3))
                );
                results.add(drug);
            }

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
    public static void addSupplier(Supplier newSupplier) {
        int newSupplierID = newSupplier.getSupplierID();
        String newName = newSupplier.getName();
        String newContactInfo = newSupplier.getContactInfo();
        String newAddress = newSupplier.getAddress();
        //add supplier and their information to the system
        String sqlString = "INSERT INTO mydb.supplier(supplierID, name, contactInfo, address)";
        sqlString += String.format(" VALUES(%d, '%s', '%s', '%s');", newSupplierID, newName, newContactInfo, newAddress);   

        try{
            MyJDBC myjdbc = new MyJDBC();
            myjdbc.alterDatabase(sqlString);
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    //use case for removing supplier from system
    public static void removeSupplier(int ID) {
        //remove supplier of name specified in parameter
        //notify user if their searched supplier is not in the system and can't be removed
        String sqlString = "DELETE FROM supplier WHERE (supplierID = " + ID + ");";
        try{
            MyJDBC myjdbc = new MyJDBC();
            myjdbc.alterDatabase(sqlString);
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    //use case for searching suppliers logged in system
    public static String searchSupplier(String query) throws SQLException {
        //find suppliers that match search query in parameter
        //return string of suppliers and their information that match query, if any
        //query can be any thing related to the supplier such as name or address
        MyJDBC myjdbc = new MyJDBC();
        Supplier supplier = new Supplier(0,"", "", "");
        String sqlString = "SELECT * FROM supplier;";
        List<List<String>> suppliersData = myjdbc.returnData(sqlString);
        for (List<String> supplierData : suppliersData){
            if (supplierData.get(0).toLowerCase().contains(query.toLowerCase()) ||
                    supplierData.get(1).toLowerCase().contains(query.toLowerCase()) ||
                    supplierData.get(2).toLowerCase().contains(query.toLowerCase()) ||
                    supplierData.get(3).toLowerCase().contains(query.toLowerCase())){ 
                    supplier = new Supplier(
                        Integer.parseInt(supplierData.get(0)),
                        supplierData.get(1),
                        supplierData.get(2),
                        supplierData.get(3));
            }
        }
        if (supplier.getName().equals("")){
            return null;
        }
    
        String result = "Name: " + supplier.getName();
        result += "\nID: " + supplier.getSupplierID();
        result += "\nContact Info: " + supplier.getContactInfo();
        result += "\nAddress: " + supplier.getAddress();
        return result;
    }
}
