package ui;

import model.Spending;
import model.SpendingCategories;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * This is the code for Spending Tab in the Account Page
 */

public class SpendingTab extends Tab implements ListSelectionListener {
    private JPanel topPanel;
    private JButton addButton;
    private JButton deleteButton;

    private JPanel centerPanel;
    private JScrollPane accountPanel;
    private DefaultListModel listModel;
    private JList list;

    private JPanel bottomPanel;
    private JLabel spendingAmountLabel;
    private JFormattedTextField spendingAmount;

    String[] spendingCategories = {"Dinning", "Shopping", "Travel", "Health", "Groceries", "Others"};

    //constructor
    public SpendingTab(AccountPage controller) {
        super(controller);
        setLayout(new BorderLayout());

        topPanel = new JPanel(new GridLayout(1,2));
        topPanel.setBackground(new Color(147, 169, 209));
        //Add Button
        createAddButton();
        //Delete Account Button
        createDeleteButton();

        createPaneList();
        JScrollPane scrollPane = new JScrollPane(centerPanel);
        centerPanel.setBackground(new Color(135, 206, 235));

        bottomPanel = new JPanel(new GridLayout(1, 2));
        createSpendingAmount();

        setVisible(true);
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: create the Add Button
    private void createAddButton() {
        addButton = new JButton("Add");

        addButton.addActionListener(e -> {
            JTextField spendingDescription = new JTextField();
            JTextField spendingAmountItem = new JTextField();
            JComboBox spendingCategoryList = new JComboBox(spendingCategories);
            JPanel panel = new JPanel(new GridLayout(3, 2));

            panel.add(new JLabel("Spending Description:"));
            panel.add(spendingDescription);
            panel.add(new JLabel("Spending Amount:"));
            panel.add(spendingAmountItem);
            panel.add(new JLabel("Choose a Category:"));
            panel.add(spendingCategoryList);

            processCommand(e, panel, spendingDescription, spendingAmountItem, spendingCategoryList);
        });
        addButton.setBackground(new Color(122, 189, 194));
        addButton.setFont(new Font("Verdana", Font.BOLD, 16));
    }



    // EFFECTS: Add Action for the Add Button
    private void processCommand(ActionEvent e, JPanel panel, JTextField spendingDescription,
                                JTextField spendingAmountItem, JComboBox spendingCategoryList) {
        if (e.getSource() == addButton) {
            int result = createImageIcon(panel);
            if (result == JOptionPane.OK_OPTION) {
                String description = spendingDescription.getText();
                double amount = Double.parseDouble(spendingAmountItem.getText());
                String category = (String) spendingCategoryList.getSelectedItem();
                SpendingCategories type = controller.account.convertToSpendingCategory(category);
                Spending spending = new Spending(description, amount, type);
                controller.account.addSpending(spending);
                listModel.addElement(spendingDescription.getText() + " : " + spendingAmountItem.getText());
                deleteButton.setEnabled(true);
            }
        }
    }

    // EFFECTS: create the Image Icon in th Add Spending external Page.
    private int createImageIcon(JPanel panel) {
        Image myPicture = null;
        try {
            myPicture = ImageIO.read(new File("src/main/ui/image/SpendingIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image newImage = myPicture.getScaledInstance(40,40, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(newImage);
        int result = JOptionPane.showConfirmDialog(null, panel, "Adding new Account",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, img);
        return result;
    }

    //EFFECTS: Check the Exceptions
    private void checkException(String name) {
        if (name == null | name.isEmpty() | name.equals("")) {
            JOptionPane.showMessageDialog(null, "You haven't enter the name for the Account!",
                    "Oops...", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "You have already added this Account's name "
                    + "to the list!", "Oops...", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //EFFECTS: create the Pane List for List of Spending
    private void createPaneList() {
        centerPanel = new JPanel(new BorderLayout());
        JPanel selection = createSelectionBar();
        accountPanel = createSpendingList();

        accountPanel.setVisible(true);
        centerPanel.add(selection, BorderLayout.PAGE_START);
        centerPanel.add(accountPanel, BorderLayout.CENTER);
    }

    // EFFECTS: create the Spending List
    private JScrollPane createSpendingList() {
        listModel = new DefaultListModel();
        for (Spending s : controller.account.getSpendingList()) {
            listModel.addElement(s.getName() + " : " + s.getAmount());
        }
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane spendingPanel = new JScrollPane(list);

        spendingPanel.setBackground(new Color(255,255,255));
        return spendingPanel;
    }

    // EFFECTS: create the example bar that display the basic element of list
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
        if (controller.account.getSpendingList().isEmpty()) {
            deleteButton.setEnabled(false);
        }
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                Spending s = controller.account.getSpendingList().get(index);
                controller.account.removeSpending(s);

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);

                if (controller.account.getSpendingList().isEmpty()) {
                    deleteButton.setEnabled(false);
                }
            }
        });
    }

    // EFFECTS: create to view the Spending Total of the Account.
    private void createSpendingAmount() {
        spendingAmountLabel = new JLabel("Total Spending in this Account:");
        bottomPanel.add(spendingAmountLabel);
        spendingAmount = new JFormattedTextField();
        bottomPanel.add(spendingAmount);
        double spendingTotalAccount = controller.account.calculateTotalSpendingAccount();
        spendingAmount.setValue(spendingTotalAccount);
        spendingAmount.setEditable(false);
    }

    @Override
    // EFFECTS: if there is no item selected, disable the button
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            return;
        }
        if (list.getSelectedIndex() == -1) {
            //No selection, disable fire button.
            deleteButton.setEnabled(false);
        } else {
            //Selection, enable the fire button.
            deleteButton.setEnabled(true);
        }
    }


}