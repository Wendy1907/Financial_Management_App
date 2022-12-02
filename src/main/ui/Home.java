package ui;

import model.AccountList;
import model.Event;
import model.EventLog;
import persistence.JsonAccountReader;
import persistence.JsonAccountWriter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This is the code for Home Page
 */

public class Home extends Frame implements ActionListener {


    private static final String JSON_STORE_ACCOUNT = "./data/User.json";
    private JsonAccountWriter jsonAccountWriter;
    private JsonAccountReader jsonAccountReader;
    private AccountList accountList = new AccountList("Wendy's Account List");

    //constructor
    public Home() {

        jsonAccountWriter = new JsonAccountWriter(JSON_STORE_ACCOUNT);
        jsonAccountReader = new JsonAccountReader(JSON_STORE_ACCOUNT);

        Frame frame = new Frame("Home Page");

        Panel panelForButtons = new Panel();
        Panel panelForLabel = new Panel();

        //List of buttons
        Button accountList = new Button("Account List");
        Button save = new Button("Save Data");
        Button load = new Button("Load Data");

        //Welcome Label
        Label welcome = new Label("Welcome to Your Personal Spending and Earning Tracker");

        setAndAddEverything(frame, panelForButtons, panelForLabel, accountList, save, load, welcome);

        pack();

        frame.setResizable(false);
        frame.setVisible(true);
        windowClose(frame);
    }

    // MODIFIES: this
    // EFFECTS: set and add every element in this frame
    private void setAndAddEverything(Frame frame, Panel panelForButtons, Panel panelForLabel, Button accountList,
                                     Button save, Button load, Label welcome) {
        frame.setLayout(null);

        frame.setBounds(200, 200, 500, 300);
        frame.setBackground(new Color(147, 169, 209));
        panelForButtons.setBounds(25,180, 450, 70);
        panelForButtons.setBackground(new Color(203, 217, 239));
        panelForLabel.setBounds(100, 100, 325, 30);
        panelForLabel.setBackground(new Color(247, 202, 202));

        panelForLabel.add(welcome);
        frame.add(panelForLabel);
        frame.add(panelForButtons);


        panelForButtons.add(accountList);
        panelForButtons.add(save);
        panelForButtons.add(load);


        accountList.setActionCommand("accounts");
        load.setActionCommand("load");
        save.setActionCommand("save");

        accountList.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);

//        setActionCommand(accountList, load, save);


        panelForButtons.setLayout(new FlowLayout());
    }




    // getter and setter
    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }

    public AccountList getAccountList() {
        return accountList;
    }

    //EFFECTS: Override the ActionListener to get some Action for the Button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("accounts")) {
            new AccountListPage(accountList);
        } else if (e.getActionCommand().equals("save")) {
            saveData();
        } else {
            loadData();
        }
    }

    //EFFECTS: Save the data to file
    private void saveData() {
        try {
            jsonAccountWriter.open();
            jsonAccountWriter.write(accountList);
            jsonAccountWriter.close();
            System.out.println("Save current data to " + JSON_STORE_ACCOUNT);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file " + JSON_STORE_ACCOUNT);
        }
    }

    // EFFECTS: Load the data to file
    private void loadData() {
        try {
            AccountList accountListSet = jsonAccountReader.read();
            setAccountList(accountListSet);
            System.out.println("Load data from " + JSON_STORE_ACCOUNT);
        } catch (IOException e) {
            System.out.println("Unable to load file from " + JSON_STORE_ACCOUNT);
        }
    }

    // MODIFIES: this
    // EFFECTS: print out the EventLog
    public void printLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.getDate() + "\n" + next.getDescription() + "\n");
        }
    }

    // MODIFIES: this
    // EFFECTS: Close the window
    private void windowClose(Frame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                printLog();
                System.exit(0);
            }
        });
    }
}
