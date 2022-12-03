package ui;

import model.*;

import javax.swing.*;
import java.awt.*;

/**
 * This is the code for Account Page which contains Three Tabs: Main Tab, Spending Tab, Eanring Tab
 */


public class AccountPage extends JFrame {
//    private JFrame frame;
//    private JPanel panel;

    private static final int MAIN_TAB_INDEX = 0;
    private static final int SPENDING_TAB_INDEX = 1;
    private static final int EARNING_TAB_INDEX = 2;

    protected Account account;


    private JTabbedPane topBar;

    protected JPanel mainTab;
    protected JPanel spendingTab;
    protected JPanel earningTab;



    // constructor
    public AccountPage(Account account) {
        super("Account");
        this.account = account;

        topBar = new JTabbedPane();
        topBar.setTabPlacement(JTabbedPane.TOP);

        loadTabs();
        add(topBar);

        pack();
        setResizable(true);
        setSize(600, 400);
        setVisible(true);
        getContentPane().setBackground(new Color(247, 202, 202));
    }

    // EFFECTS: load Tabs into the Frame
    public void loadTabs() {
        mainTab = new MainTab(this);
        spendingTab = new SpendingTab(this);
        earningTab = new EarningTab(this);

        topBar.add(mainTab, MAIN_TAB_INDEX);
        topBar.setTitleAt(MAIN_TAB_INDEX, "Main");

        topBar.add(spendingTab, SPENDING_TAB_INDEX);
        topBar.setTitleAt(SPENDING_TAB_INDEX, "Spending");

        topBar.add(earningTab, EARNING_TAB_INDEX);
        topBar.setTitleAt(EARNING_TAB_INDEX, "Earning");
    }

    // EFFECTS: load the Spending Tab
    public void loadNewSpending() {
        if (spendingTab != null) {
            topBar.remove(spendingTab);
        }
        spendingTab = new SpendingTab(this);
        topBar.add(spendingTab, SPENDING_TAB_INDEX);
        topBar.setTitleAt(SPENDING_TAB_INDEX, "Spending");
    }

    // EFFECTS: load the Earning Tab
    public void loadNewEarning() {
        if (earningTab != null) {
            topBar.remove(earningTab);
        }
        earningTab = new EarningTab(this);
        topBar.add(earningTab, EARNING_TAB_INDEX);
        topBar.setTitleAt(EARNING_TAB_INDEX, "Earning");
    }
}
