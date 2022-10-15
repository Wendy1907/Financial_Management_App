package model;

public class Earning {
    private String name;
    private double amount;
    private EarningCategories categories;
    //private LocalDate date;

    //EFFECTS: create a spending that contain name, amount, categories.
    public Earning(String name, double amount, EarningCategories categories) {
        this.name = name;
        this.amount = amount;
        this.categories = categories;
        //this.date = date;
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

    //public LocalDate getDate() {
        //return date;
    //}

}



