package model;

import java.time.LocalDateTime;
import java.time.Month;

public class Expense {
    private String expenseName;
    private LocalDateTime expenseDate;
    private ExpenseCategories expenseCategories;
    private double expenseAmount;

    public Expense(String expenseName, LocalDateTime expenseDate, ExpenseCategories expenseCategories,
                   double expenseAmount) {
        this.expenseName = expenseName;
        this.expenseDate = getExpenseDateTime();
        this.expenseCategories = expenseCategories;
        this.expenseAmount = expenseAmount;
    }

    public String getExpenseName() {
        return expenseName;
    }

    public LocalDateTime getExpenseDateTime() {
        return LocalDateTime.now();
    }

    public ExpenseCategories getExpenseCategories() {
        return expenseCategories;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public Month getMonth() {
        return LocalDateTime.now().getMonth();
    }

    public int getYear() {
        return LocalDateTime.now().getYear();
    }


}
