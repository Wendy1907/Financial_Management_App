package ui;

import model.AccountList;
import persistence.JsonAccountReader;
import persistence.JsonAccountWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomeActionListener implements ActionListener {
    private static final String JSON_STORE_ACCOUNT = "./data/Account.json";
    private JsonAccountWriter jsonAccountWriter;
    private JsonAccountReader jsonAccountReader;
    private AccountList accountList = new AccountList("Wendy's Account List");
    private Home app;

    //EFFECTS: run the main page
    public HomeActionListener(Home app) {
        this.app = app;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        jsonAccountWriter = new JsonAccountWriter(JSON_STORE_ACCOUNT);
        jsonAccountReader = new JsonAccountReader(JSON_STORE_ACCOUNT);

        if (e.getActionCommand().equals("account")) {
            AccountList listOfAccount = app.getAccountList();
            new AccountListPage(listOfAccount);
        } else if (e.getActionCommand().equals("save")) {
            saveAccountList();
        } else {
            loadAccountList();
        }
    }


    // EFFECTS: saves the AccountList to file
    private void saveAccountList() {
        try {
            jsonAccountWriter.open();
            jsonAccountWriter.write(accountList);
            jsonAccountWriter.close();

            System.out.println("Saved " + accountList.getName() + " to " + JSON_STORE_ACCOUNT);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_ACCOUNT);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads AccountList from file
//    private void loadAccountList() {
//        try {
//            accountList = jsonAccountReader.read();
//            app.setAccountList(accountList);
//            JOptionPane.showMessageDialog(app,"Loaded " + accountList.getName() + "from" + JSON_STORE_ACCOUNT);
//        } catch (IOException e) {
//            JOptionPane.showMessageDialog(app,"Unable to read from file: " + JSON_STORE_ACCOUNT);
//        }
//    }
    private void loadAccountList() {
        try {
            accountList = jsonAccountReader.read();
            System.out.println("Loaded " + accountList.getName() + " from " + JSON_STORE_ACCOUNT);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_ACCOUNT);
        }
    }

}
