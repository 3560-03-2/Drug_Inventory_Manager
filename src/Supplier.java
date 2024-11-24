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
}
