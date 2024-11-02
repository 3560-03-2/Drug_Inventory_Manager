public class Methods {
    
    // use case for logging a sale
    public static void logSale(long date, String items, String receipt, String customer){

    }

    // use case for logging a delivery
    public static void logDelivery(long date, String items){

    }

    // use case for logging a return
    public static void logReturn(long date, String reason, String receipt, String customer){

    }

    // use case for finding a drug
    public String void findDrug(String name, String category){
        // Should return the drug based on name or category.
    }

    //use case for logging a drug in system
    public void logDrug(String name, String desc) {
        //add given drug with it's description to the system
    }

    //use case for reordering drugs
    public void reorderDrug(String name) {
        //invoke when particular drug stock falls below a certain threshold
        //reorder drug specified in parameter
    }

    //use case for adding a supplier into system
    public void addSupplier(String name, String contactInfo, String address) {
        //add supplier and their information to the system
        //will create a new "supplier"
    }

    //use case for removing supplier from system
    public void removeSupplier(String name) {
        //remove supplier of name specified in parameter
        //nofiy user if their searched supplier is not in the system and can't be removed
    }

    //use case for searching suppliers logged in system
    public String searchSupplier(String query) {
        //find suppliers that match search query in parameter
        //return string of suppliers and their information that match query, if any
    }
}