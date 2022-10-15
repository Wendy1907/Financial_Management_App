package model;

import java.util.ArrayList;
import java.util.List;

public class AccountList {
    private String nameOwner;
    private double totalSpendingAllAccount;
    private double totalEarningAllAccount;
    private List<Account> accountList;

    public AccountList(String nameOwner, double totalSpendingAllAccount, double totalEarningAllAccount) {
        this.nameOwner = nameOwner;
        this.totalSpendingAllAccount = 0.0;
        this.totalEarningAllAccount = 0.0;
        accountList = new ArrayList<>();
    }

    //getter
    public String getNameOwner() {
        return nameOwner;
    }

    public double getTotalSpendingAllAccount() {
        return totalSpendingAllAccount;
    }

    public double getTotalEarningAllAccount() {
        return totalEarningAllAccount;
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

    //EFFECTS: get total amount of Spending
    public double calculateTotalSpendingAllAccount() {
        for (Account a : accountList) {
            totalSpendingAllAccount += a.getTotalSpendingAccount();
        }
        return totalSpendingAllAccount;
    }

    //EFFECTS: get total amount of Spending
    public double calculateTotalEarningAllAccount() {
        for (Account a : accountList) {
            totalEarningAllAccount += a.getTotalEarningAccount();
        }
        return totalEarningAllAccount;
    }
}
