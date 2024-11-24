import java.io.*;
import java.text.*;
import java.util.*;

public class ReceiptReader {

    // method for testing
    //public static void main(String[] args) throws IOException {
    //    String file = "receipt.txt";

    //    ReceiptItems receipt = ReceiptReader.read(file);

    //    System.out.println("Customer: " + receipt.getCustomer());
    //    System.out.println("Items: " + receipt.getItems());
    //    System.out.println("Date: " + receipt.getDate());
    //}

    // reads fields for receiptItems from .txt file and returns a new receiptItems
    public static ReceiptItems read(String receiptFile) throws IOException{
        File file = new File(receiptFile);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        String customer = "";
        List<String> items = new ArrayList<>();
        Date date = null;

        String line;
        boolean isItemList = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

        while((line = reader.readLine()) != null){
            line = line.trim(); // delete line if it doesn't contain relevant data

            // read date
            if(line.startsWith("Date:")){
                String dateString = line.substring(5).trim();
                try {
                date = dateFormat.parse(dateString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            // read customer
            else if(line.startsWith("Customer:")) {
                customer = line.substring(9).trim();
            }
            // read items
            else if(line.startsWith("- ")) {
                items.add(line.substring(2).trim());
            }
        }

        reader.close();

        return new ReceiptItems(customer, items, date);
    }

}

// class for receiptItems. Has fields for customer's name, the list of items they purchased, and the date the receipt was printed.
class ReceiptItems{
    private String customer;
    private List<String> items;
    private Date date;

    public ReceiptItems(String customer, List<String> items, Date date){
        this.customer = customer;
        this.items = items;
        this.date = date;
    }

    public String getCustomer(){
        return customer;
    }

    public List<String> getItems(){
        return items;
    }

    public Date getDate(){
        return date;
    }
}