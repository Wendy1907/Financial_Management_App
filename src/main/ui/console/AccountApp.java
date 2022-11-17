package ui.console;

import model.*;

import java.util.Scanner;

/**
 * This is the Account page of the Financial Tracker.
 */
public class AccountApp {
    private static final String JSON_STORE_SPENDING = "./data/Spending.json";
    private static final String JSON_STORE_EARNING = "./data/Earning.json";
    protected Account account;
    private Scanner input;

    //EFFECTS: Run the Account page
    public AccountApp(Account account) {
        init();
        this.account = account;
        runAccount();
    }

    // EFFECTS: initialize the input console
    public void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user input for Account page
    public void runAccount() {
        while (true) {
            displayMenu();
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                return;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command for Account page.
    private void processCommand(String command) {
        switch (command) {
            case "as": doAddSpending();
                break;
            case "ds": doDeleteSpending();
                break;
            case "s": doCalculateSpendingTotalAccount();
                break;
            case "vs": runViewSpending();
                break;
            case "ae": doAddEarning();
                break;
            case "de": doDeleteEarning();
                break;
            case "e": doCalculateEarningTotalAccount();
                break;
            case "ve": runViewEarning();
                break;
            default:
                System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options of Account page to user
    private void displayMenu() {
        String name = account.getNameAccount();
        int totalSpending = account.getSpendingList().size();
        int totalEarning = account.getEarningList().size();
        System.out.println("\n----------Welcome to " + name + " Account!----------");
        System.out.println("You have " + totalSpending + " Spending at your Account now!");
        System.out.println("You have " + totalEarning + " Earning at your Account now!");
        System.out.println("\nDo you want to:");
        System.out.println("\tas -> ADD a NEW Spending to your Account");
        System.out.println("\tds -> DELETE Spending from your Account");
        System.out.println("\ts -> get TOTAL of Spending value in your Account");
        System.out.println("\tvs -> VIEW the list of Spending that you have in your Account");
        System.out.println("\tae -> ADD a NEW Earning to your Account");
        System.out.println("\tde -> DELETE Earning from your Account");
        System.out.println("\te -> get TOTAL of Earning value in your Account");
        System.out.println("\tve -> VIEW the list of Earning that you have in your Account");
        System.out.println("\tq -> BACK to AccountList page");
    }

    //MODIFIES: this
    //EFFECTS: add new Spending into Account
    private void doAddSpending() {
        init();
        System.out.println("What is the Spending description?");
        String name = input.nextLine();

        System.out.println("How much have you paid?");
        double amount = input.nextDouble();

        System.out.println("In which categories do you want to store the " + name + "?");
        System.out.println("\nSelect from:");
        System.out.println("\tdis -> Dinning");
        System.out.println("\tss -> Shopping");
        System.out.println("\tts -> Travel");
        System.out.println("\ths -> Health");
        System.out.println("\tgs -> Groceries");
        System.out.println("\tos -> Others");
        String category = input.next();
        category = category.toLowerCase();

        SpendingCategories type = categorizeSpending(category);
        Spending s = new Spending(name, amount, type);
        this.account.addSpending(s);
        System.out.println(">>> You have successfully added the Spending " + name + " to your Account.");
    }

    // EFFECTS: categorize Spending
    public SpendingCategories categorizeSpending(String category) {
        switch (category) {
            case "dis":
                return SpendingCategories.Dinning;
            case "ss":
                return SpendingCategories.Shopping;
            case "ts":
                return SpendingCategories.Travel;
            case "hs":
                return SpendingCategories.Health;
            case "gs":
                return SpendingCategories.Groceries;
            default:
                return SpendingCategories.Others;
        }
    }

    // MODIFIES: this
    // EFFECTS: delete Spending from Account and do nothing if that Spending is not in the Account.
    public void doDeleteSpending() {
        Scanner item = new Scanner(System.in);
        System.out.println(">>> What's the Spending's description that you want to delete?");
        String name = item.nextLine();

        for (Spending s : this.account.getSpendingList()) {
            if (s.getName().equals(name)) {
                this.account.removeSpending(s);
                System.out.println(name + " is gone now!");
                break;
            }
        }
    }

    //EFFECTS: get the total Spending amount in one Account
    public void doCalculateSpendingTotalAccount() {
        System.out.println(">>> Total Spending is : " + account.calculateTotalSpendingAccount());
    }

    // MODIFIES: this
    // EFFECTS: processes user input for v (view) Spending
    public void runViewSpending() {
        init();
        while (true) {
            if (account.getSpendingList().isEmpty()) {
                System.out.println(">>> You have nothing at your Spending list now. Go back to add Spending!");
                return;
            }
            printAllSpending();
            break;
        }
    }

    // EFFECTS: displays all Spending in Account
    private void printAllSpending() {
        int count = 1;
        System.out.println("\n>>>> At Account, you have these Spending: ");
        for (Spending s : account.getSpendingList()) {
            System.out.println(count + ". " + s.getName());
            count++;
        }
    }

    //MODIFIES: this
    //EFFECTS: add new Earning into Account
    private void doAddEarning() {
        init();
        System.out.println("What is the Earning description?");
        String name = input.nextLine();

        System.out.println("How much have you earned?");
        double amount = input.nextDouble();

        System.out.println("In which categories do you want to store the " + name + "?");
        System.out.println("\nSelect from:");
        System.out.println("\tse -> Salary");
        System.out.println("\tie -> Interest");
        System.out.println("\toe -> Others");
        String category = input.next();
        category = category.toLowerCase();

        EarningCategories type = categorizeEarning(category);
        Earning e = new Earning(name, amount, type);
        this.account.addEarning(e);
        System.out.println(">>> You have successfully added the Earning " + name + " to your Account.");
    }

    // EFFECTS: categorize Earning
    public EarningCategories categorizeEarning(String category) {
        switch (category) {
            case "se":
                return EarningCategories.Salary;
            case "ie":
                return EarningCategories.Interest;
            default:
                return EarningCategories.Others;
        }
    }

    // MODIFIES: this
    // EFFECTS: delete Earning from Account and do nothing if that Earning is not in the Account
    public void doDeleteEarning() {
        Scanner item = new Scanner(System.in);
        System.out.println(">>> What's the Earning's description that you want to delete?");
        String name = item.nextLine();

        for (Earning e : this.account.getEarningList()) {
            if (e.getName().equals(name)) {
                this.account.removeEarning(e);
                System.out.println(name + " is gone now!");
                break;
            }
        }
    }

    //EFFECTS: get the total Earning amount in one Account
    public void doCalculateEarningTotalAccount() {
        System.out.println(">>> Total Earning is: " + account.calculateTotalEarningAccount());
    }

    // MODIFIES: this
    // EFFECTS: processes user input for v (view) Earning
    public void runViewEarning() {
        init();
        while (true) {
            if (account.getEarningList().isEmpty()) {
                System.out.println(">>> You have nothing at your Earning list now. Go back to add Earning!");
                return;
            }
            printAllEarning();
            break;
        }
    }

    // EFFECTS: displays all Earning in Account
    private void printAllEarning() {
        int count = 1;
        System.out.println("\n>>>> At Account, you have these Earning: ");
        for (Earning e : account.getEarningList()) {
            System.out.println(count + ". " + e.getName());
            count++;
        }
    }

}
