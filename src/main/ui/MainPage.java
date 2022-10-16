package ui;

import model.AccountList;

import java.util.Scanner;

/**
 * This is the Main Page of the Financial Tracker
 */

public class MainPage {
    private Scanner input;
    private AccountList accountList;

    //EFFECTS: run the main page
    public MainPage() {
        accountList = new AccountList("AccountList");
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        runMain();
    }

    private void runMain() {
        boolean running = true;
        while (running) {
            displayMenu();
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                running = false;
                System.out.println("Goodbye! Have a nice day!");
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "a":
                new AccountListApp(accountList);
                break;
            default:
                System.out.println("Not valid input...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n\n=======Welcome to your personal spending and earning tracker=======");
        System.out.println("-----------------------Shop smart, spend wise-----------------------");
        System.out.println("\nYou can :");
        System.out.println("\ta -> My account list");
        System.out.println("\tq -> quit");
    }




}
