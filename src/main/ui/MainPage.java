package ui;

import model.AccountList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This is the Main Page of the Financial Tracker
 */

public class MainPage {
    private static final String JSON_STORE_ACCOUNT = "./data/accounts.json";
    private Scanner input;
    private AccountList accountList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    //EFFECTS: run the main page
    public MainPage() throws FileNotFoundException {
        accountList = new AccountList("AccountList");
        input = new Scanner(System.in);
        input.useDelimiter("\n");

        jsonWriter = new JsonWriter(JSON_STORE_ACCOUNT);
        jsonReader = new JsonReader(JSON_STORE_ACCOUNT);

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
            case "s":
                saveAccountList();
                break;
            case "l":
                loadAccountList();
            default:
                System.out.println("Not valid input...");
        }
    }

    private void saveAccountList() {
        try {
            jsonWriter.open();
            ;
            jsonWriter.write(accountList);
            jsonWriter.close();
            System.out.println("Saved" + accountList.getName() + "to" + JSON_STORE_ACCOUNT);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_ACCOUNT);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadAccountList() {
        try {
            accountList = jsonReader.read();
            System.out.println("Loaded " + accountList.getName() + " from " + JSON_STORE_ACCOUNT);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_ACCOUNT);
        }
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\n\n=======Welcome to your personal Spending and Earning tracker=======");
        System.out.println("-----------------------Shop smart, spend wise-----------------------");
        System.out.println("\nYou can :");
        System.out.println("\ta -> My account list");
        System.out.println("\ts -> Save for all the information into file");
        System.out.println("\tl -> Load all information from file");
        System.out.println("\tq -> quit");
    }
}
