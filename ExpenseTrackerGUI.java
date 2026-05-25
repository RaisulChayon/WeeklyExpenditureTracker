/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weeklyexpendituretracker;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
/**
 *
 * @author ddh
 */
public class ExpenseTrackerGUI extends JFrame {
    // Dropdown for selecting day
    private JComboBox<String> dayComboBox;

    // Dropdown for selecting expense category
    private JComboBox<String> categoryComboBox;

    // Text field for entering amount
    private JTextField amountTextField;

    // Text field for entering description
    private JTextField descriptionTextField;

    // Text area for displaying expense records and results
    private JTextArea outputTextArea;

    // Buttons used in the application
    private JButton addExpenseButton;
    private JButton totalExpenseButton;
    private JButton viewByCategoryButton;
    private JButton clearButton;

    // ArrayList stores all expense objects entered by the user
    private ArrayList<Expense> expenseList;

    /**
     * Constructor sets up the complete GUI.
     */
    public ExpenseTrackerGUI() {

        // Create the expense list
        expenseList = new ArrayList<>();

        // Set main window properties
        setTitle("Weekly Expenditure Tracker");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use BorderLayout for the main frame
        setLayout(new BorderLayout(10, 10));

        // Create and add GUI sections
        createTitlePanel();
        createInputPanel();
        createOutputPanel();
        createButtonPanel();
    }

    /**
     * Creates the title section of the GUI.
     */
    private void createTitlePanel() {
        JLabel titleLabel = new JLabel("Weekly Expenditure Tracker", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

        add(titleLabel, BorderLayout.NORTH);
    }

    /**
     * Creates input fields and dropdown menus.
     */
    private void createInputPanel() {

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Enter Expense Details"));

        // Days available in dropdown menu
        String[] days = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"  };

        // Categories required in assessment
        String[] categories = { "Groceries", "Eating Out", "Petrol", "Taxi", "Bills", "Rent", "Others"  };

        dayComboBox = new JComboBox<>(days);
        categoryComboBox = new JComboBox<>(categories);
        amountTextField = new JTextField();
        descriptionTextField = new JTextField();

        inputPanel.add(new JLabel("Day:"));
        inputPanel.add(dayComboBox);

        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(categoryComboBox);

        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountTextField);

        inputPanel.add(new JLabel("Description:"));
        inputPanel.add(descriptionTextField);

        add(inputPanel, BorderLayout.WEST);
    }

    /**
     * Creates the text area used to display output.
     */
    private void createOutputPanel() {

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Expense Output"));

        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Creates buttons and adds action listeners.
     */
    private void createButtonPanel() {

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addExpenseButton = new JButton("Add Expense");
        totalExpenseButton = new JButton("Total Weekly Expense");
        viewByCategoryButton = new JButton("View By Category");
        clearButton = new JButton("Clear");

        buttonPanel.add(addExpenseButton);
        buttonPanel.add(totalExpenseButton);
        buttonPanel.add(viewByCategoryButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        /*
         * Action listener for Add Expense button.
         * When clicked, it validates input and stores expense.
         */
        addExpenseButton.addActionListener(e -> addExpense());

        /*
         * Action listener for Total Weekly Expense button.
         * When clicked, it calculates total expenses.
         */
        totalExpenseButton.addActionListener(e -> calculateTotalWeeklyExpense());

        /*
         * Action listener for View By Category button.
         * When clicked, it groups all expenses by category.
         */
        viewByCategoryButton.addActionListener(e -> viewExpensesByCategory());

        /*
         * Action listener for Clear button.
         * When clicked, it clears the input and output fields.
         */
        clearButton.addActionListener(e -> clearFields());
    }

    /**
     * Adds an expense after validating user input.
     */
    private void addExpense() {

        String day = dayComboBox.getSelectedItem().toString();
        String category = categoryComboBox.getSelectedItem().toString();
        String amountInput = amountTextField.getText().trim();
        String description = descriptionTextField.getText().trim();

        // Check if amount field is empty
        if (amountInput.isEmpty()) {
            JOptionPane.showMessageDialog( this, "Please enter an amount.", "Input Error",  JOptionPane.ERROR_MESSAGE );
            return;
        }

        // Check if description field is empty
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog( this, "Please enter a description.", "Input Error", JOptionPane.ERROR_MESSAGE );
            return;
        }

        double amount;

        try {
            amount = Double.parseDouble(amountInput);

            // Amount must be greater than zero
            if (amount <= 0) {
                JOptionPane.showMessageDialog( this, "Amount must be greater than zero.", "Input Error", JOptionPane.ERROR_MESSAGE );
                return;
            }

        } catch (NumberFormatException ex) {

            // This handles invalid inputs such as letters or symbols
            JOptionPane.showMessageDialog( this, "Please enter a valid numeric amount.", "Input Error", JOptionPane.ERROR_MESSAGE );
            return;
        }

        // Create an Expense object and add it to the list
        Expense expense = new Expense(day, category, amount, description);
        expenseList.add(expense);

        // Show confirmation message
        JOptionPane.showMessageDialog( this,  "Expense added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE  );

        // Display the newly added expense
        outputTextArea.append(expense.toString() + "\n");

        // Clear only input fields after adding expense
        amountTextField.setText("");
        descriptionTextField.setText("");
        dayComboBox.setSelectedIndex(0);
        categoryComboBox.setSelectedIndex(0);
    }

    /**
     * Calculates and displays the total weekly expense.
     */
    private void calculateTotalWeeklyExpense() {

        // Check if no expenses have been added
        if (expenseList.isEmpty()) {
            JOptionPane.showMessageDialog( this, "No expenses have been added yet.", "No Data", JOptionPane.WARNING_MESSAGE );
            return;
        }

        double total = 0;

        // Add every expense amount
        for (Expense expense : expenseList) {
            total += expense.getAmount();
        }

        outputTextArea.setText("");
        outputTextArea.append("TOTAL WEEKLY EXPENSE\n");
        outputTextArea.append("====================\n");
        outputTextArea.append("Total Amount Spent: $" + String.format("%.2f", total) + "\n");
    }

    /**
     * Displays all weekly expenses grouped by category.
     */
    private void viewExpensesByCategory() {

        // Check if no expenses have been added
        if (expenseList.isEmpty()) {
            JOptionPane.showMessageDialog( this, "No expenses available to display.", "No Data", JOptionPane.WARNING_MESSAGE );
            return;
        }

        /*
         * LinkedHashMap is used to keep category order fixed.
         * Each category stores an ArrayList of expenses.
         */
        Map<String, ArrayList<Expense>> groupedExpenses = new LinkedHashMap<>();

        String[] categories = { "Groceries", "Eating Out", "Petrol", "Taxi", "Bills", "Rent", "Others" };

        // Create an empty list for each category
        for (String category : categories) {
            groupedExpenses.put(category, new ArrayList<>());
        }

        // Add each expense into its matching category list
        for (Expense expense : expenseList) {
            groupedExpenses.get(expense.getCategory()).add(expense);
        }

        outputTextArea.setText("");
        outputTextArea.append("WEEKLY EXPENSES GROUPED BY CATEGORY\n");
        outputTextArea.append("===================================\n\n");

        // Display category-wise expenses
        for (String category : groupedExpenses.keySet()) {

            ArrayList<Expense> categoryExpenses = groupedExpenses.get(category);

            outputTextArea.append("Category: " + category + "\n");
            outputTextArea.append("-----------------------------------\n");

            double categoryTotal = 0;

            if (categoryExpenses.isEmpty()) {
                outputTextArea.append("No expenses in this category.\n");
            } else {
                for (Expense expense : categoryExpenses) {
                    outputTextArea.append(
                            expense.getDay() + " | $" + String.format("%.2f", expense.getAmount()) + " | " + expense.getDescription() + "\n"
                    );

                    categoryTotal += expense.getAmount();
                }

                outputTextArea.append("Category Total: $" + String.format("%.2f", categoryTotal) + "\n");
            }

            outputTextArea.append("\n");
        }
    }

    /**
     * Clears input fields and output area.
     * This does not delete saved expenses from the list.
     */
    private void clearFields() {
        amountTextField.setText("");
        descriptionTextField.setText("");
        outputTextArea.setText("");
        dayComboBox.setSelectedIndex(0);
        categoryComboBox.setSelectedIndex(0);
    }
}
