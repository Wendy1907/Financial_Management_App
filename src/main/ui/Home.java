package ui;

import model.AccountList;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Home extends Frame {
    private static HomeActionListener homeActionListener;
    private AccountList accountList = new AccountList("Wendy's Account List");

    public Home() {
        homeActionListener = new HomeActionListener(this);

        Frame frame = new Frame("Financial Tracker");

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


        accountList.setActionCommand("account");
        load.setActionCommand("load");
        save.setActionCommand("save");

        setActionCommand(accountList, load, save);


        panelForButtons.setLayout(new FlowLayout());
    }

    // MODIFIES: this
    // EFFECTS: Close the window
    private static void windowClose(Frame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    private static void setActionCommand(Button accountList, Button load, Button save) {
        accountList.addActionListener(homeActionListener);
        save.addActionListener(homeActionListener);
        load.addActionListener(homeActionListener);
    }


    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }

    public AccountList getAccountList() {
        return accountList;
    }
}
