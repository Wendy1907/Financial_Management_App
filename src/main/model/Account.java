package model;

import org.json.JSONArray;
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
    private List<Spending> dinningList;
    private List<Spending> shoppingList;
    private List<Spending> travelList;
    private List<Spending> healthList;
    private List<Spending> groceriesList;
    private List<Spending> othersSpendingList;
    private List<Earning> earningList;
    private List<Earning> salaryList;
    private List<Earning> interestList;
    private List<Earning> othersEarningList;

    public Account(String nameAccount) {
        this.nameAccount = nameAccount;
        spendingList = new ArrayList<>();
        dinningList = new ArrayList<>();
        shoppingList = new ArrayList<>();
        travelList = new ArrayList<>();
        healthList = new ArrayList<>();
        groceriesList = new ArrayList<>();
        othersSpendingList = new ArrayList<>();
        earningList = new ArrayList<>();
        salaryList = new ArrayList<>();
        interestList = new ArrayList<>();
        othersEarningList = new ArrayList<>();

    }

    //getter

    public String getNameAccount() {
        return nameAccount;
    }

    public List<Spending> getSpendingList() {
        return spendingList;
    }

    public List<Spending> getDinningList() {
        return dinningList;
    }

    public List<Spending> getShoppingList() {
        return shoppingList;
    }

    public List<Spending> getTravelList() {
        return travelList;
    }

    public List<Spending> getGroceriesList() {
        return groceriesList;
    }

    public List<Spending> getHealthList() {
        return healthList;
    }

    public List<Spending> getOthersSpendingList() {
        return othersSpendingList;
    }

    public List<Earning> getEarningList() {
        return earningList;
    }

    public List<Earning> getSalaryList() {
        return salaryList;
    }

    public List<Earning> getInterestList() {
        return interestList;
    }

    public List<Earning> getOthersEarningList() {
        return othersEarningList;
    }

    //REQUIRES: spending != null
    //MODIFIES: this
    //EFFECTS: stores the given Spending into the list
    public void addSpending(Spending spending) {
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

    //REQUIRES: s != null
    //MODIFIES: this
    //EFFECTS: stores the given Spending transaction into the appropriate container within this class
    public void storeSpending() {
        for (Spending s : spendingList) {
            SpendingCategories categories = s.getCategories();
            switch (categories) {
                case Dinning:
                    this.dinningList.add(s);
                    break;
                case Shopping:
                    this.shoppingList.add(s);
                    break;
                case Travel:
                    this.travelList.add(s);
                    break;
                case Health:
                    this.healthList.add(s);
                    break;
                case Groceries:
                    this.groceriesList.add(s);
                    break;
                default:
                    this.othersSpendingList.add(s);
            }
        }
    }

    //REQUIRES: s != null
    //MODIFIES: this
    //EFFECTS: stores the given Spending transaction into the appropriate container within this class
    public void storeEarning() {
        for (Earning e : earningList) {
            EarningCategories categories = e.getCategories();
            switch (categories) {
                case Salary:
                    this.salaryList.add(e);
                    break;
                case Interest:
                    this.interestList.add(e);
                    break;
                default:
                    this.othersEarningList.add(e);
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("nameAccount", nameAccount);
        json.put("spendingList", spendingToJson());
        json.put("earningList", earningToJson());
        return json;
    }

    public JSONArray spendingToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Spending spending : spendingList) {
            jsonArray.put(spending.toJson());
        }

        return jsonArray;
    }

    public JSONArray earningToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Earning earning : earningList) {
            jsonArray.put(earning.toJson());
        }

        return jsonArray;
    }


}