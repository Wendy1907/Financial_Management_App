package model;

import java.time.LocalDate;

/**
 * This is the Earning class. We could track the Earning by the name (description for that earning),
 * amount of money have earned, the categories of that earning, and record the date of that Earning.
 */
public class Earning {
    private String name;
    private double amount;
    private EarningCategories categories;
    private LocalDate date;

    //EFFECTS: create a spending that contain name, amount, categories.
    public Earning(String name, double amount, EarningCategories categories) {
        this.name = name;
        this.amount = amount;
        this.categories = categories;
        this.date = getDate();
    }

    //getter
    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public EarningCategories getCategories() {
        return categories;
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }

}



