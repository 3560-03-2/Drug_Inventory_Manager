public class Supplier{
    private int supplierID;
    private String name;
    private String contactInfo;
    private String address;

    public Supplier(int supplierID, String name, String contactInfo, String address){
        this.supplierID = supplierID;
        this.name = name;
        this.contactInfo = contactInfo;
        this.address = address;
    }

    public int getSupplierID(){
        return supplierID;
    }

    public String getName(){
        return name;
    }
    
    public String getContactInfo(){
        return contactInfo;
    }
    
    public String getAddress(){
        return address;
    }
    
    public void setSupplierID(int supplierID){
        this.supplierID = supplierID;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setContactInfo(String contactInfo){
        this.contactInfo = contactInfo;
    }
    
    public void setAddress(String address){
        this.address = address;
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
            //myjdbc.showTable("supplier");
            System.out.println("New supplier added successfully.");
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
            //myjdbc.showTable("supplier");
            System.out.println("Supplier with ID " + ID + " has been removed.");
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }

    }

    // Test the functionality of adding and removing suppliers 
    public static void main(String[] args) {
        /* 
        Supplier supplier1 = new Supplier(1, "Amazon", "1234567890", "410 Terry Avenue North, Seattle, Washington, 98109");
        Supplier supplier2 = new Supplier(2, "Google", "9876543210", "1600 Amphitheatre Parkway, Mountain View, California, 94043");
        Supplier supplier3 = new Supplier(3, "Target", "9632587410", "123 Main St, Redmond, Washington, 98052");
        addSupplier(supplier1);
        addSupplier(supplier2);
        addSupplier(supplier3);
        */
    }
}