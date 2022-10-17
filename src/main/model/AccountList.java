package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the AccountList class. In this class, it shows the name of the Owner (Tracker),
 * show the list of all accounts that person has, and calculate the total amount of mooney that
 * that person has spent and earned. We also can change the list of Account by adding or removing
 * Account from the Account lists.
 */
public class AccountList {
    private String name;
    private List<Account> accountList;

    public AccountList(String name) {
        this.name = name;
        accountList = new ArrayList<>();
    }

    //getter
    public String getName() {
        return name;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    //REQUIRES: account != null
    //MODIFIES: this
    //EFFECTS: stores the given Account into the list
    public void addAccount(Account account) {
        accountList.add(account);
    }

    //REQUIRES: account != null
    //MODIFIES: this
    //EFFECTS: remove the given Account from the list
    public void removeAccount(Account account) {
        accountList.remove(account);
    }

    //EFFECTS: get total number of Account in the list
    public int totalAccount() {
        int count = 0;
        for (Account a : accountList) {
            count++;
        }
        return count;
    }

    //EFFECTS: get total amount of Spending
    public double calculateTotalSpendingAllAccount() {
        double totalSpendingAllAccount = 0.0;
        for (Account a : accountList) {
            totalSpendingAllAccount += a.calculateTotalSpendingAccount();
        }
        return totalSpendingAllAccount;
    }

    //EFFECTS: get total amount of Spending
    public double calculateTotalEarningAllAccount() {
        double totalEarningAllAccount = 0.0;
        for (Account a : accountList) {
            totalEarningAllAccount += a.calculateTotalEarningAccount();
        }
        return totalEarningAllAccount;
    }
}
