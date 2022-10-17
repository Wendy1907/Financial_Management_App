package ui;

import model.Account;
import model.AccountList;

import java.util.Scanner;

/**
 * This is the AccountList page of the Financial Tracker.
 */
public class AccountListApp {
    protected AccountList accountList;
    private Scanner input;

    // EFFECTS: run the AccountList page
    public AccountListApp(AccountList accountList) {
        init();
        this.accountList = accountList;
        runAccountList();
    }


    // EFFECTS: initialize the input console
    public void init() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this
    // EFFECTS: processes user input for AccountList page.
    public void runAccountList() {
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
    // EFFECTS: processes user command for AccountLis page
    private void processCommand(String command) {
        switch (command) {
            case "v":
                runViewAccount();
                break;
            case "va":
                goIntoSpecificAccount();
                break;
            case "a":
                doAddAccount();
                break;
            case "d":
                doDeleteAccount();
                break;
            case "s":
                doCalculateSpendingTotal();
                break;
            case "e":
                doCalculateEarningTotal();
                break;
            default:
                System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options in the AccountList page to user
    private void displayMenu() {
        int totalAccount = accountList.totalAccount();
        System.out.println("\n----------Welcome to AccountList!----------");
        System.out.println("You have " + totalAccount + " accounts at your account list now!");
        System.out.println("\nDo you want to:");
        System.out.println("\tv -> VIEW Accounts that you have");
        System.out.println("\tva -> Get in details of Selected Account");
        System.out.println("\ta -> ADD a NEW Account to your account list");
        System.out.println("\td -> DELETE Account from your account list");
        System.out.println("\ts -> get TOTAL of Spending in all of your accounts");
        System.out.println("\te -> get TOTAL of Earning in all of your accounts");
        System.out.println("\tq -> BACK to main page");
    }

    // MODIFIES: this
    // EFFECTS: processes user input for v (view)
    private void runViewAccount() {
        init();
        while (true) {
            if (accountList.getAccountList().isEmpty()) {
                System.out.println(">>> You have nothing at your Account list now. ");
                System.out.println("Go back to add Account for tracking!");
                return;
            }
            printAll();
            break;
        }
    }

    // EFFECTS: displays all Account in AccountList
    private void printAll() {
        int count = 1;
        System.out.println("\n>>>> At AccountList, you have these Account: ");
        for (Account a : accountList.getAccountList()) {
            System.out.println(count + ". " + a.getNameAccount());
            count++;
        }
    }

    //EFFECTS: show the details of Selected Account
    private void goIntoSpecificAccount() {
        init();
        while (true) {
            if (accountList.getAccountList().isEmpty()) {
                System.out.println(">>> You have nothing at your Account list now to view. ");
                System.out.println("Go back to add account for tracking!");
                return;
            }
            getIntoAccount();
            break;
        }
    }


    private void getIntoAccount() {
        Scanner item = new Scanner(System.in);
        System.out.println(">>> What's the Account's name that you want to get into?");
        String name = item.nextLine();

        for (Account a: accountList.getAccountList()) {
            if (a.getNameAccount().equals(name)) {
                new AccountApp(a);
            }
        }
    }



    //MODIFIES:this
    //EFFECTS: add new Account to the AccountList
    private void doAddAccount() {
        init();
        System.out.println("What is the Account name?");
        String name = input.nextLine();

        Account a = new Account(name);
        accountList.addAccount(a);
        System.out.println(">>> You have successfully added " + name + " to your Account list");
    }


    //MODIFIES: this
    //EFFECTS: delete Account from AccountList
    public void doDeleteAccount() {
        Scanner item = new Scanner(System.in);
        System.out.println(">>> What's the account's name that you want to delete?");
        String name = item.nextLine();

        for (Account a : accountList.getAccountList()) {
            if (a.getNameAccount().equals(name)) {
                accountList.removeAccount(a);
                System.out.println(name + " is gone now!");
                break;
            }
            System.out.println("Oops... You don't have this account! No need to delete.");
        }
    }



    //EFFECTS: get the total Spending amount in all Account in AccountList
    public void doCalculateSpendingTotal() {
        System.out.println("Total Spending is: " + accountList.calculateTotalSpendingAllAccount());
    }

    //EFFECTS: get the total Earning amount in all Account in AccountList
    public void doCalculateEarningTotal() {
        System.out.println("Total Earning is: " + accountList.calculateTotalEarningAllAccount());
    }
}
