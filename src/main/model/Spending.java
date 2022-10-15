package model;

/**
 * This is the Spending class. Spending class contains a name/ description, amount, category and
 * bought time for each spending transaction.
 */

public class Spending {
    private String name;
    private double amount;
    private SpendingCategories categories;
    //private LocalDate date;


    //EFFECTS: create a spending that contain name, amount, categories and date.
    public Spending(String name, double amount, SpendingCategories categories) {
        this.name = name;
        this.amount = amount;
        this.categories = categories;
        //this.date = getDate();
    }

    //getter
    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public SpendingCategories getCategories() {
        return categories;
    }

    //public LocalDate getDate() {
        //return date;
    //}



}


