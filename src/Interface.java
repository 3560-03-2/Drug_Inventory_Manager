import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import java.util.List;

public class Interface {

    Interface() {
        /* The main frame the system will be displayed on.
        Other frames may be opened for other actions */
        JFrame mainFrame = new JFrame("Drug Inventory System");
        mainFrame.setSize(1000, 1000);
        mainFrame.setLayout(new FlowLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        /*-----------------
         * drugList will be used to store the drug objects when one gets added to the system
         * by the user and this list will be displayed to the user upon several actions such as
         * looking through the inventory or reordering a drug
         -----------------*/
        DefaultListModel drugList = new DefaultListModel<>();

        /*-----------------
         * addDrugButton will be used for the user to choose to add a drug to the system
         * it will open a new window prompting for the necessary information and have a button
         * to submit the information and close the window
         -----------------*/
        JButton addDrugButton = new JButton("Log Drug");
        addDrugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //create the new window
                JFrame secondaryFrame = new JFrame("Log New Drug");
                secondaryFrame.setSize(700, 700);
                secondaryFrame.setLayout(new FlowLayout());
                secondaryFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                secondaryFrame.setVisible(true);

                //create a panel for necessary formatting
                JPanel formatPanel = new JPanel();
                formatPanel.setLayout(new BoxLayout(formatPanel, BoxLayout.PAGE_AXIS));

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
                        //add code to create new drug object with the parameters set to the data entered
                        secondaryFrame.setVisible(false);
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
                formatPanel.add(categoryLabel);
                formatPanel.add(categoryField);
                formatPanel.add(stockLabel);
                formatPanel.add(stockField);
                formatPanel.add(logButton);
                formatPanel.add(cancelButton);
                //add panel to the frame
                secondaryFrame.add(formatPanel);
            }
        });

        //add components to main frame
        mainFrame.add(addDrugButton);

        // Add "Find Drug" button
        JButton findDrugButton = new JButton("Find Drug");
        findDrugButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openFindDrugFrame();
            }
        });

        // Add the findDrugButton to the main frame
        mainFrame.add(findDrugButton);

        // Create a button for supplier button
        JButton supplierButton = new JButton("Supplier Actions");
        supplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                openSupplierActionsFrame();
            }
        });

        // Add the supplier button to the main frame
        mainFrame.add(supplierButton);
    }

    // Method to open the "Find Drug" frame
    private void openFindDrugFrame() {
        JFrame findDrugFrame = new JFrame("Find Drug");
        findDrugFrame.setSize(700, 500);
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
        JFrame supplierActionsFrame = new JFrame("Choose supplier actions");
                supplierActionsFrame.setSize(700, 300);
                supplierActionsFrame.setLayout(new BorderLayout());
                supplierActionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                JPanel inputPanel = new JPanel();
                inputPanel.setLayout(new FlowLayout());

                // Create 3 buttons for the 3 use cases of supplier
                JButton addSupplierButton = new JButton("Add a Supplier");
                JButton removeSupplierButton = new JButton("Remove a Supplier");
                JButton searchSupplierButton = new JButton("Search a Supplier");

                inputPanel.add(addSupplierButton);
                inputPanel.add(removeSupplierButton);
                inputPanel.add(searchSupplierButton);

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
            
                supplierActionsFrame.add(inputPanel, BorderLayout.NORTH);
                supplierActionsFrame.setVisible(true);
    }

    // Implement the action after clicking on the add supplier button
    private void openAddSupplierFrame() {
        JFrame addSupplierFrame = new JFrame("Add a Supplier");
        addSupplierFrame.setSize(700, 300);
        addSupplierFrame.setLayout(new FlowLayout());
        addSupplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        JLabel contactLabel = new JLabel("Contact Info:");
        JTextField contactField = new JTextField(20);
        JLabel addressLabel = new JLabel("Address:");
        JTextField addressField = new JTextField(30);
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
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
                Supplier supplier = new Supplier(Integer.parseInt(idField.getText()), nameField.getText(), 
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
        removeSupplierFrame.setSize(700, 300);
        removeSupplierFrame.setLayout(new FlowLayout());
        removeSupplierFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));


        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(10);
        JButton removeButton = new JButton("Remove");
        JButton cancelButton = new JButton("Cancel");
        
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(removeButton);
        inputPanel.add(cancelButton);
    
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                Methods.removeSupplier(Integer.parseInt(idField.getText()));
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
        searchSupplierFrame.setSize(700, 300);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Interface();
            }
        });
    }
}
