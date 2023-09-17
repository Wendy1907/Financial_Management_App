package model;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Transactions {
    private List<Expense> expenseList;
    private List<Income> incomeList;

    public Transactions() {
        expenseList = new ArrayList<>();
        incomeList = new ArrayList<>();
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void addExpense(Expense expense) {
        expenseList.add(expense);
    }

    public void removeExpense(Expense expense) {
        if (expenseList != null) {
            for (Expense e: expenseList) {
                if (e == expense) {
                    expenseList.remove(e);
                }
            }
        }
    }

    /**
     * idk should I combined two of this together
     */
    public double calculateTotalExpense() {
        double amountExpense = 0.0;
        for (Expense e : expenseList) {
            amountExpense += e.getExpenseAmount();
        }
        return amountExpense;
    }

    public double calculateTotalExpenseByMonth(Month month, int year) {
        double amountMonthlyExpense = 0.0;
        for (Expense e : expenseList) {
            if (e.getYear() == year && e.getMonth() == month) {
                amountMonthlyExpense += e.getExpenseAmount();
            }
        }
        return amountMonthlyExpense;
    }

    public void addIncome(Income income) {
        incomeList.add(income);
    }

    public void removeIncome(Income income) {
        if (incomeList != null) {
            for (Income i : incomeList) {
                if (i == income) {
                    incomeList.remove(i);
                }
            }
        }
    }

    public double calculateTotalIncomeAccount() {

    }
}
