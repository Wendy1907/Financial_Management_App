package ui;

import model.Account;
import model.AccountList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class AccountListPage extends JFrame implements ListSelectionListener, ActionListener {
    private static final String JSON_STORE_ACCOUNT = "./data/User.json";
    private JFrame frame;
    private JPanel topPanel;
    private JButton deleteButton;
    private JButton addButton;
    //private JButton refreshButton;
    private JPanel centerPanel;
    private JScrollPane accountPanel;
    private DefaultListModel listModel;
    private JList list;

    private JPanel bottomPanel;
    private JLabel spendingTotalLabel;
    private JFormattedTextField spendingTotal;
    private JLabel earningTotalLabel;
    private JFormattedTextField earningTotal;
    private JLabel accountGetInto;
    private JTextField accountNameInput;
    private JButton getIntoButton;
    private JButton cancelButton;


    protected AccountList accountList;

    // constructor
    public AccountListPage(AccountList accountList) {
        this.accountList = accountList;
        frame = new JFrame("Account List Page");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        topPanel = new JPanel(new GridLayout(1, 2));
        topPanel.setBackground(new Color(147, 169, 209));
        // Add Button
        createAddButton();
        // Delete Account Button
        createDeleteButton();
        //create

        createPaneList();
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        centerPanel.setBackground(new Color(135, 206, 235));


        bottomPanel = new JPanel(new GridLayout(5,2));
        // Total Spending , Total Earning View
        createSpendingTotal();
        createEarningTotal();
        createGetInto();

        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setResizable(false);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);
    }

    //EFFECTS: create the Add Button
    private void createAddButton() {
        addButton = new JButton("Add");

        try {
            Image imgAdd = ImageIO.read(getClass().getResource("image/AddIcon.png"));
            Image newImageAdd = imgAdd.getScaledInstance(15,15, Image.SCALE_DEFAULT);
            addButton.setIcon(new ImageIcon(newImageAdd));
        } catch (Exception e) {
            System.out.println(e);
        }

        addButton.addActionListener(e -> {
            JTextField accountName = new JTextField();
            JPanel panel = new JPanel(new GridLayout(1, 2));

            panel.add(new JLabel("Account's name: "));
            panel.add(accountName);

            processCommand(e, panel, accountName);
        });

        addButton.setBackground(new Color(255,255,255));

    }

    //EFFECTS: Action Event for the Add Button
    private void processCommand(ActionEvent e, JPanel panel, JTextField accountName) {
        if (e.getSource() == addButton) {
            int result = createImageIcon(panel);
            if (result == JOptionPane.OK_OPTION) {
                String name = accountName.getText();
                Account newAccount = new Account(name);
                accountList.addAccount(newAccount);
            }
            listModel.addElement(accountName.getText());
            deleteButton.setEnabled(true);
        }
    }

    //EFFECT: create an Image Icon for Adding external page
    private int createImageIcon(JPanel panel) {
        Image myPicture = null;
        try {
            myPicture = ImageIO.read(new File("src/main/ui/image/BankIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image newImage = myPicture.getScaledInstance(30,30, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(newImage);
        int result = JOptionPane.showConfirmDialog(null, panel, "Adding new Account",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, img);
        return result;
    }

    //EFFECT: check all of exceptions
    private void checkException(String name) {
        if (name == null | name.isEmpty() | name.equals("")) {
            JOptionPane.showMessageDialog(null, "You haven't enter the name for the Account!",
                    "Oops...", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "You have already added this Account's name "
                    + "to the list!", "Oops...", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //EFFECTS: create the Pane List for List of Account
    private void createPaneList() {
        centerPanel = new JPanel(new BorderLayout());
        JPanel selection = createSelectionBar();
        accountPanel = createAccountList();

        accountPanel.setVisible(true);
        centerPanel.add(selection, BorderLayout.PAGE_START);
        centerPanel.add(accountPanel, BorderLayout.CENTER);
    }

    //EFFECTS: create Account List
    private JScrollPane createAccountList() {
        listModel = new DefaultListModel();
        for (Account a : accountList.getAccountList()) {
            listModel.addElement(a.getNameAccount());
        }
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane accountPanel = new JScrollPane(list);

        accountPanel.setBackground(new Color(255,255,255));
        return accountPanel;
    }

    //EFFECTS: create the SelectionBar for the AccountList view
    private JPanel createSelectionBar() {
        JPanel example = new JPanel(new GridLayout(1,2));
        createAddButton();
        createDeleteButton();
        example.add(addButton);
        example.add(deleteButton);
        centerPanel.add(example, BorderLayout.PAGE_START);
        return example;
    }

    //EFFECTS: create the Delete Button.
    private void createDeleteButton() {
        deleteButton = new JButton("Delete");
        if (accountList.getAccountList().isEmpty()) {
            deleteButton.setEnabled(false);
        }
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                Account a = accountList.getAccountList().get(index);
                accountList.removeAccount(a);
                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);

                if (accountList.getAccountList().isEmpty()) {
                    deleteButton.setEnabled(false);
                }
            }
        });
    }

    // EFFECTS: create to view the Spending Total of the Account List.
    private void createSpendingTotal() {
        spendingTotalLabel = new JLabel("Total Spending:");
        bottomPanel.add(spendingTotalLabel);
        spendingTotal = new JFormattedTextField();
        bottomPanel.add(spendingTotal);
        double spendingTotalAllAccount = accountList.calculateTotalSpendingAllAccount();
        spendingTotal.setValue(spendingTotalAllAccount);
        spendingTotal.setEditable(false);
    }

    // EFFECTS: create to view the Earning Total of the Account List.
    private void createEarningTotal() {
        earningTotalLabel = new JLabel("Total Earning:");
        bottomPanel.add(earningTotalLabel);
        earningTotal = new JFormattedTextField();
        bottomPanel.add(earningTotal);
        double earningTotalAllAccount = accountList.calculateTotalEarningAllAccount();
        earningTotal.setValue(earningTotalAllAccount);
        earningTotal.setEditable(false);
    }

    // EFFECTS: create Get Into Button and set Action on it
    private void createGetInto() {
        accountGetInto = new JLabel("Account to Get In Details");
        bottomPanel.add(accountGetInto);
        accountNameInput = new JTextField(15);
        bottomPanel.add(accountNameInput);
        getIntoButton = new JButton("Get In");
        bottomPanel.add(getIntoButton);
        cancelButton = new JButton("Cancel");
        bottomPanel.add(cancelButton);
        getIntoButton.setActionCommand("getIn");
        cancelButton.setActionCommand("cancel");
        getIntoButton.addActionListener(this);
        cancelButton.addActionListener(this);

    }


    //EFFECTS: Set Action for the Get Into Button and Cancel Button
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "cancel") {
            accountNameInput.setText("");
        } else if (e.getActionCommand() == "getIn") {
            String accountToGetInto = accountNameInput.getText();
            for (Account a : accountList.getAccountList()) {
                if (accountToGetInto.equals(a.getNameAccount())) {
                    new AccountPage(a);
                }
            }
        }
    }


    @Override
    // EFFECTS: if there is no item selected, disable the button
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        if (list.getSelectedIndex() == -1) {
            deleteButton.setEnabled(false);
        } else {
            deleteButton.setEnabled(true);
        }
    }




}
