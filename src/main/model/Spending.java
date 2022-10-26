package model;

import org.json.JSONObject;
import persistence.Writable;

import java.time.LocalDate;

/**
 * This is the Spending class. Spending class contains a name (description for that Spending), amount, category and
 * record the Date for each Spending transaction.
 */

public class Spending implements Writable {
    private String name;
    private double amount;
    private SpendingCategories categories;
    private LocalDate date;


    //EFFECTS: create a spending that contain name, amount, categories and date.
    public Spending(String name, double amount, SpendingCategories categories) {
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

    public SpendingCategories getCategories() {
        return categories;
    }

    public LocalDate getDate() {
        return LocalDate.now();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("amount", amount);
        json.put("categories", categories);
        return json;
    }
}


