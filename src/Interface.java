import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import java.util.List;

public class Interface {

    Interface() {
        /* The main frame the system will be displayed on.
        Other frames may be opened for other actions */
        JFrame mainFrame = new JFrame("Drug Inventory System");
        mainFrame.setSize(550, 550);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.getContentPane().setBackground(Color.WHITE);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add Log Drug button
        JButton logDrugButton = new JButton("Log Drug");
        logDrugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openLogDrugFrame();
            }
        });

        // Add "Find Drug" button
        JButton findDrugButton = new JButton("Find Drug");
        findDrugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openFindDrugFrame();
            }
        });

        // Create a button for supplier button
        JButton supplierButton = new JButton("Supplier Actions");
        supplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openSupplierActionsFrame();
            }
        });

        // Create a button for log actions
        JButton logButton = new JButton("Log Actions");
        logButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                openLogActionsFrame();
            }
        });

        JButton reorderButton = new JButton("Reorder Drug");
        reorderButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                openReorderFrame();
            }
        });

        JTextArea notifArea = new JTextArea("No new notifications.");
        notifArea.setFont(new Font("Times New Roman", Font.BOLD, 20));
        notifArea.setPreferredSize(new Dimension(400, 200));
        notifArea.setBackground(Color.LIGHT_GRAY);
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(1, 2, 5, 0));
        displayPanel.add(notifArea);
        displayPanel.setBackground(Color.WHITE);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(logDrugButton);
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(findDrugButton);
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(reorderButton);
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(supplierButton);
        buttonPanel.add(new JLabel(" "));
        buttonPanel.add(logButton);
        buttonPanel.add(new JLabel(" "));
        displayPanel.add(buttonPanel);
        displayPanel.setPreferredSize(new Dimension(500, 300));
        ImageIcon logoIcon = new ImageIcon("src//logo.jpg");
        Image logoImage = logoIcon.getImage();
        Image resizedLogo = logoImage.getScaledInstance(500, 125, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(resizedLogo));
        mainFrame.add(logo);
        mainFrame.add(displayPanel);
        mainFrame.setVisible(true);
    }

    // Method to open the "Find Drug" frame
    private void openFindDrugFrame() {
        JFrame findDrugFrame = new JFrame("Find Drug");
        findDrugFrame.setSize(550, 350);
        findDrugFrame.setLayout(new BorderLayout());
        findDrugFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Input panel for drug name and category
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(15);
        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(15);
        JButton searchButton = new JButton("Search");

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(categoryLabel);
        inputPanel.add(categoryField);
        inputPanel.add(searchButton);

        // Text area for displaying results
        JTextArea resultArea = new JTextArea(15, 50);
        resultArea.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Search button action
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String name = nameField.getText().trim();
                String category = categoryField.getText().trim();

                try {
                    // Use the findDrug method from Methods.java
                    List<Drug> results = Methods.findDrug(name, category);

                    // Display results
                    if (results.isEmpty()) {
                        resultArea.setText("No drugs found for the given criteria.");
                    } else {
                        StringBuilder resultText = new StringBuilder();
                        for (Drug drug : results) {
                            resultText.append(", Name: ").append(drug.getName())
                                      .append(", Category: ").append(drug.getCategory())
                                      .append(", Stock: ").append(drug.getStock())
                                      .append("\n");
                        }
                        resultArea.setText(resultText.toString());
                    }
                } catch (Exception e) {
                    resultArea.setText("Error occurred: " + e.getMessage());
                }
            }
        });

        // Add components to frame
        findDrugFrame.add(inputPanel, BorderLayout.NORTH);
        findDrugFrame.add(scrollPane, BorderLayout.CENTER);

        findDrugFrame.setVisible(true);
    }

    // Implement the action after clicking on the supplier button
    private void openSupplierActionsFrame() {
        JFrame supplierActionsFrame = new JFrame("Choose action");
                supplierActionsFrame.setSize(300, 200);
                supplierActionsFrame.setLayout(new BorderLayout());
                supplierActionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new GridLayout(4, 1, 0, 10));

                // Create 3 buttons for the 3 use cases of supplier
                JButton addSupplierButton = new JButton("Add a Supplier");
                JButton removeSupplierButton = new JButton("Remove a Supplier");
                JButton searchSupplierButton = new JButton("Search a Supplier");
                JButton back = new JButton("Back");

                inputPanel.add(addSupplierButton);
                inputPanel.add(removeSupplierButton);
                inputPanel.add(searchSupplierButton);
                inputPanel.add(back);

                addSupplierButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        openAddSupplierFrame();
                    }
                });
                removeSupplierButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        openRemoveSupplierFrame();
                    }
                });
                searchSupplierButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        openSearchSupplierFrame();
                    }
                });
                back.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        supplierActionsFrame.setVisible(false);
                    }
                });
            
                supplierActionsFrame.add(inputPanel, BorderLayout.NORTH);
                supplierActionsFrame.setVisible(true);
    }

    // Implement the action after clicking on the add supplier button
    private void openAddSupplierFrame() {
        JFrame addSupplierFrame = new JFrame("Add a Supplier");
        addSupplierFrame.setSize(400, 250);
        addSupplierFrame.setLayout(new FlowLayout());
        addSupplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel contactLabel = new JLabel("Contact Info:");
        JTextField contactField = new JTextField(20);
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(30);
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(contactLabel);
        inputPanel.add(contactField);
        inputPanel.add(addressLabel);
        inputPanel.add(addressField);
        inputPanel.add(addButton);
        inputPanel.add(cancelButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Supplier supplier = new Supplier(nameField.getText(), 
                                                 contactField.getText(), addressField.getText());
                Methods.addSupplier(supplier);
                JOptionPane.showMessageDialog(null, 
                                      "Supplier added successfully!");
                addSupplierFrame.setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                addSupplierFrame.setVisible(false);
            }
        });

        addSupplierFrame.add(inputPanel, BorderLayout.NORTH);
        addSupplierFrame.setVisible(true);
    }

    // Implement the action after clicking on the remove supplier button
    private void openRemoveSupplierFrame() {
        JFrame removeSupplierFrame = new JFrame("Remove a Supplier");
        removeSupplierFrame.setSize(350, 225);
        removeSupplierFrame.setLayout(new FlowLayout());
        removeSupplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));


        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JButton removeButton = new JButton("Remove");
        JButton cancelButton = new JButton("Cancel");
        
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(removeButton);
        inputPanel.add(cancelButton);
    
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Methods.removeSupplier(nameField.getText());
                } catch (NumberFormatException | SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null, 
                                      "Supplier removed successfully!");
                removeSupplierFrame.setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                removeSupplierFrame.setVisible(false);
            }
        });

        removeSupplierFrame.add(inputPanel, BorderLayout.NORTH);
        removeSupplierFrame.setVisible(true);
    }

    // Implement the action after clicking on the search supplier button
    private void openSearchSupplierFrame() { 
        JFrame searchSupplierFrame = new JFrame("Search a Supplier");
        searchSupplierFrame.setSize(500, 350);
        searchSupplierFrame.setLayout(new FlowLayout());
        searchSupplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

        JLabel queryLabel = new JLabel("Search Query (Type in anything about the supplier):");
        JTextField queryField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        JButton cancelButton = new JButton("Cancel");

        inputPanel.add(queryLabel);
        inputPanel.add(queryField);
        inputPanel.add(searchButton);
        inputPanel.add(cancelButton);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String supplier = "";
                try {
                    supplier = Methods.searchSupplier(queryField.getText());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (supplier!= null) {
                    JOptionPane.showMessageDialog(null, supplier, 
                                            "Supplier Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(searchSupplierFrame, 
                                          "No supplier found with the given query.");
                }
                searchSupplierFrame.setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                searchSupplierFrame.setVisible(false);
            }
        });

        searchSupplierFrame.add(inputPanel, BorderLayout.NORTH);
        searchSupplierFrame.setVisible(true);
    }

    // Implement the action after clicking on the logs button
    private void openLogActionsFrame() {
        JFrame logActionsFrame = new JFrame("Choose log actions");
        logActionsFrame.setSize(300, 200);
        logActionsFrame.setLayout(new BorderLayout());
        logActionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1, 0, 10));

        // Create 3 buttons for the 3 use cases of logs
        JButton logSaleButton = new JButton("Log a Sale");
        JButton logDeliveryButton = new JButton("Log a Delivery");
        JButton logReturnButton = new JButton("Log a Return");
        JButton back = new JButton("Back");

        inputPanel.add(logSaleButton);
        //inputPanel.add(logDeliveryButton); DID NOT YET IMPLEMENT
        inputPanel.add(logReturnButton);
        inputPanel.add(back);

        logSaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openLogSaleFrame();
            }
        });
        logDeliveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //openLogDeliveryFrame();
            }
        });
        logReturnButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                openLogReturnFrame();
            }
        });     
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                logActionsFrame.setVisible(false);
            }
        });

        logActionsFrame.add(inputPanel, BorderLayout.NORTH);
        logActionsFrame.setVisible(true);
    }

    // Implement the action after clicking on the log sale button
    private void openLogSaleFrame() {
        JFrame logSaleFrame = new JFrame("Log a Sale");
        logSaleFrame.setSize(500, 250);
        logSaleFrame.setLayout(new FlowLayout());
        logSaleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
    
        JLabel receiptLabel = new JLabel("Path to receipt file:"); // If receipt.txt is in src folder, just type enter receipt.txt into text field
        JTextField receiptField = new JTextField(50);
        JButton logButton = new JButton("Log Sale");
        JButton cancelButton = new JButton("Cancel");
    
        inputPanel.add(receiptLabel);
        inputPanel.add(receiptField);
        inputPanel.add(logButton);
        inputPanel.add(cancelButton);
    
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String receipt = receiptField.getText();
                Methods.logSale(receipt);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                logSaleFrame.setVisible(false);
            }
        });

        logSaleFrame.add(inputPanel, BorderLayout.NORTH);
        logSaleFrame.setVisible(true);
    }

    private void openLogReturnFrame(){
        JFrame logReturnFrame = new JFrame("Log a Return");
        logReturnFrame.setSize(500, 300);
        logReturnFrame.setLayout(new FlowLayout());
        logReturnFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

        JLabel receiptLabel = new JLabel("Path to receipt file:"); // If receipt.txt is in src folder, just type enter receipt.txt into text field
        JTextField receiptField = new JTextField(50);
        JLabel returnLabel = new JLabel("Reason for return:");
        JTextField returnField = new JTextField(50);
        JButton logButton = new JButton("Log Return");
        JButton cancelButton = new JButton("Cancel");    
        
        inputPanel.add(receiptLabel);
        inputPanel.add(receiptField);
        inputPanel.add(returnLabel);
        inputPanel.add(returnField);
        inputPanel.add(logButton);
        inputPanel.add(cancelButton);
        
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String receipt = receiptField.getText();
                String reason = returnField.getText();
                Methods.logReturn(reason, receipt);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                logReturnFrame.setVisible(false);
            }
        });

        logReturnFrame.add(inputPanel, BorderLayout.NORTH);
        logReturnFrame.setVisible(true);
    }

    private void openLogDrugFrame() {
        //create the new window
        JFrame secondaryFrame = new JFrame("Log New Drug");
        secondaryFrame.setSize(300, 300);
        secondaryFrame.setLayout(new FlowLayout());
        secondaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        secondaryFrame.setVisible(true);

        //create a panel for necessary formatting
        JPanel formatPanel = new JPanel();
        formatPanel.setLayout(new BoxLayout(formatPanel, BoxLayout.PAGE_AXIS));
        JPanel formatPanel2 = new JPanel();
        formatPanel2.setLayout(new BoxLayout(formatPanel2, BoxLayout.LINE_AXIS));

        //create the labels and text fields for user to enter data
        JLabel drugNameLabel = new JLabel("Drug Name: ");
        JTextField drugNameField = new JTextField(30);
        JLabel categoryLabel = new JLabel("Category: ");
        JTextField categoryField = new JTextField(30); //would probably be better as a dropdown menu
        JLabel stockLabel = new JLabel("Stock: ");
        JTextField stockField = new JTextField(5);

        //create button for user to log the new drug in the system
        JButton logButton = new JButton("Log Drug");
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //add code to check that all areas are filled in with information then
                if(drugNameField.getText().isEmpty() || categoryField.getText().isEmpty() || stockField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                }
                else {
                    //add code to create new drug object with the parameters set to the data entered
                    Drug drug = new Drug(drugNameField.getText(), categoryField.getText(), Integer.parseInt(stockField.getText()));
                    try {
                        Methods.logDrug(drug);
                        JOptionPane.showMessageDialog(null, "Drug logged successfully!");
                        if (drug.getStock() < 11) {
                            JOptionPane.showMessageDialog(null, "Inventory of " + drug.getName() + " is low!");
                        }
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    secondaryFrame.setVisible(false);
                }
            }
        });

        //create button for user to cancel the action and close window
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                secondaryFrame.setVisible(false);
            }
        });

        //add all the components to the panel
        formatPanel.add(drugNameLabel);
        formatPanel.add(drugNameField);
        formatPanel.add(new JLabel(""));
        formatPanel.add(categoryLabel);
        formatPanel.add(categoryField);
        formatPanel.add(new JLabel(""));
        formatPanel.add(stockLabel);
        formatPanel.add(stockField);
        formatPanel.add(new JLabel(""));
        formatPanel2.add(logButton);
        formatPanel2.add(cancelButton);
        //add panel to the frame
        secondaryFrame.add(formatPanel);
        secondaryFrame.add(formatPanel2);
    }

    private void openReorderFrame() {
        JFrame secondaryFrame = new JFrame("Reorder Drug");
        secondaryFrame.setSize(300, 300);
        secondaryFrame.setLayout(new FlowLayout());
        secondaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel formatPanel = new JPanel();
        formatPanel.setLayout(new BoxLayout(formatPanel, BoxLayout.PAGE_AXIS));
        JPanel formatPanel2 = new JPanel();
        formatPanel2.setLayout(new BoxLayout(formatPanel2, BoxLayout.LINE_AXIS));

        JLabel drugNameLabel = new JLabel("Drug Name: ");
        JTextField drugNameField = new JTextField(30);
        JLabel quantityLabel = new JLabel("Quantity: ");
        JTextField quantityField = new JTextField(5);

        //create button for user to log the new drug in the system
        JButton reorderButton = new JButton("Reorder");
        reorderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //add code to check that all areas are filled in with information then
                if(drugNameField.getText().isEmpty() || quantityField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                }
 /*             else {
                    //add code to create new drug object with the parameters set to the data entered
                    Boolean bool = Methods.reorderedDrug(drugNameField.getText(), Integer.parseInt(quantityField.getText()));
                    if(bool){
                        JOptionPane.showMessageDialog(null, "Reordering " + quantityField.getText() + " units of " + drugNameField.getText() + ".");
                        secondaryFrame.setVisible(false);
                    }
                    else
                        JOptionPane.showMessageDialog(null, "Drug to reorder could not be found.");
                } */
            }
        });

        //create button for user to cancel the action and close window
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                secondaryFrame.setVisible(false);
            }
        });

        //add all the components to the panel
        formatPanel.add(drugNameLabel);
        formatPanel.add(drugNameField);
        formatPanel.add(new JLabel(""));
        formatPanel.add(quantityLabel);
        formatPanel.add(quantityField);
        formatPanel.add(new JLabel(""));
        formatPanel2.add(reorderButton);
        formatPanel2.add(cancelButton);
        //add panel to the frame
        secondaryFrame.add(formatPanel);
        secondaryFrame.add(formatPanel2);
        secondaryFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Interface();
            }
        });
    }
}
