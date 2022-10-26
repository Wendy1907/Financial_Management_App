package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/**
 * This Account class store the name of account that we have, shows the total Spending amount, adn
 * total Earning of this account, also show the List of Spending and Earning, and then categorize them
 * into different categories. We also can change the list of Spending and Earning by adding or removing
 * Spending/Earning transaction from these lists.
 */
public class Account implements Writable {
    private String nameAccount;
    private List<Spending> spendingList;
    private List<Earning> earningList;

    public Account(String nameAccount) {
        this.nameAccount = nameAccount;
        spendingList = new ArrayList<>();
        earningList = new ArrayList<>();

    }

    //getter

    public String getNameAccount() {
        return nameAccount;
    }

    public List<Spending> getSpendingList() {
        return spendingList;
    }

    public List<Earning> getEarningList() {
        return earningList;
    }

    //REQUIRES: spending != null
    //MODIFIES: this
    //EFFECTS: stores the given Spending into the list
    public void addEarning(Spending spending) {
        spendingList.add(spending);
    }

    //REQUIRES: earning != null
    //MODIFIES: this
    //EFFECTS: remove the given Spending from the list
    public void removeSpending(Spending spending) {
        spendingList.remove(spending);
    }

    //EFFECTS: get total amount of Spending
    public double calculateTotalSpendingAccount() {
        double amountSpendingAccount = 0.0;
        for (Spending s : spendingList) {
            amountSpendingAccount += s.getAmount();
        }
        return amountSpendingAccount;
    }

    //REQUIRES: earning != null
    //MODIFIES: this
    //EFFECTS: stores the given Earning into the list
    public void addEarning(Earning earning) {
        earningList.add(earning);
    }

    //REQUIRES: earning != null
    //MODIFIES: this
    //EFFECTS: remove the given Earning from the list
    public void removeEarning(Earning earning) {
        earningList.remove(earning);
    }

    //EFFECTS: get total amount of Earning
    public double calculateTotalEarningAccount() {
        double amountEarningAccount = 0.0;
        for (Earning e : earningList) {
            amountEarningAccount += e.getAmount();
        }
        return amountEarningAccount;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("nameAccount", nameAccount);
        return json;
    }


}