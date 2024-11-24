import java.awt.*;
import java.awt.event.*;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Interface();
            }
        });
    }
}
