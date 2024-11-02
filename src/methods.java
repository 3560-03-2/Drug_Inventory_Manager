public class methods {

    public void logDrug(String name, String desc) {
        //add given drug with it's description to the system
    }

    public void reorderDrug(String name) {
        //invoke when particular drug stock falls below certain point
        //reorder drug specified in parameter
    }

    public void addSupplier(String name, String contactInfo, String address) {
        //add supplier and their information to the system
        //will create new "supplier"
    }

    public void removeSupplier(String name) {
        //remove supplier of name specified in parameter
        //notify user if their searched supplier is not in the system and can't be removed
    }

    public String searchSupplier(String query) {
        //find suppliers that match search query in parameter
        //return string of suppliers that match query
    }
}
