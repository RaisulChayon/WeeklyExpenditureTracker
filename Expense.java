/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package weeklyexpendituretracker;

/**
 *
 * @author ddh
 */
public class Expense {
    private String day;
    private String category;
    private double amount;
    private String description;

    /**
     * Constructor to create an Expense object.
     *
     * @param day day of the week
     * @param category expense category
     * @param amount amount spent
     * @param description description of the expense
     */
    public Expense(String day, String category, double amount, String description) {
        this.day = day;
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    /**
     * Gets the day of the expense.
     *
     * @return day
     */
    public String getDay() {
        return day;
    }

    /**
     * Gets the category of the expense.
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Gets the amount of the expense.
     *
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets the description of the expense.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns expense details as a formatted string.
     *
     * @return formatted expense information
     */
    @Override
    public String toString() {
        return "Day: " + day + " | Category: " + category + " | Amount: $" + String.format("%.2f", amount) + " | Description: " + description;
    }
}
