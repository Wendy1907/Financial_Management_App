package model;

import java.time.LocalDateTime;

public class Income {
    private String incomeName;
    private LocalDateTime incomeDate;
    private IncomeCategories incomeCategories;
    private double incomeAmount;

    public Income(String incomeName, LocalDateTime incomeDate, IncomeCategories incomeCategories,
                  double incomeAmount) {
        this.incomeName = incomeName;
        this.incomeDate = getIncomeDateTime();
        this.incomeCategories = incomeCategories;
        this.incomeAmount = incomeAmount;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public LocalDateTime getIncomeDateTime() {
        return LocalDateTime.now();
    }

    public IncomeCategories getIncomeCategories() {
        return incomeCategories;
    }

    public double getIncomeAmount() {
        return incomeAmount;
    }
}

