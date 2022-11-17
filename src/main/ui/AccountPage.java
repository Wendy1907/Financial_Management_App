package ui;

import model.*;

import javax.swing.*;
import java.awt.*;

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

//    private void printLog(EventLog events) {
//        for (Event next : events) {
//            System.out.println(next.getDescription());
//        }
//    }

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

    public void loadNewSpending() {
        if (spendingTab != null) {
            topBar.remove(spendingTab);
        }
        spendingTab = new SpendingTab(this);
        topBar.add(spendingTab, SPENDING_TAB_INDEX);
        topBar.setTitleAt(SPENDING_TAB_INDEX, "Spending");
    }

    public void loadNewEarning() {
        if (earningTab != null) {
            topBar.remove(earningTab);
        }
        earningTab = new EarningTab(this);
        topBar.add(earningTab, EARNING_TAB_INDEX);
        topBar.setTitleAt(EARNING_TAB_INDEX, "Earning");
    }
}
