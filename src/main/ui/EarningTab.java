package ui;

import model.Earning;
import model.EarningCategories;

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
 * This is the code for Earning Tab in the Account Page
 */

public class EarningTab extends Tab implements ListSelectionListener {
    private JPanel topPanel;
    private JButton addButton;
    private JButton deleteButton;

    private JPanel centerPanel;
    private JScrollPane accountPanel;
    private DefaultListModel listModel;
    private JList list;

    private JPanel bottomPanel;
    private JLabel earningAmountLabel;
    private JFormattedTextField earningAmount;

    String[] earningCategories = {"Salary", "Interest", "Others"};

    // constructor
    public EarningTab(AccountPage controller) {
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
        createEarningAmount();

        setVisible(true);
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    // EFFECTS: create the Add Button
    private void createAddButton() {
        addButton = new JButton("Add");

        addButton.addActionListener(e -> {
            JTextField earningDescription = new JTextField();
            JTextField earningAmountItem = new JTextField();
            JComboBox earningCategoryList = new JComboBox(earningCategories);
            JPanel panel = new JPanel(new GridLayout(3, 2));

            panel.add(new JLabel("Earning Description:"));
            panel.add(earningDescription);
            panel.add(new JLabel("Earning Amount:"));
            panel.add(earningAmountItem);
            panel.add(new JLabel("Choose a Category:"));
            panel.add(earningCategoryList);

            processCommand(e, panel, earningDescription, earningAmountItem, earningCategoryList);
        });
        addButton.setBackground(new Color(122, 189, 194));
        addButton.setFont(new Font("Verdana", Font.BOLD, 16));
    }



    // EFFECTS: Add Action for the Add Button
    private void processCommand(ActionEvent e, JPanel panel, JTextField earningDescription,
                                JTextField earningAmountItem, JComboBox earningCategoryList) {
        if (e.getSource() == addButton) {
            int result = createImageIcon(panel);
            if (result == JOptionPane.OK_OPTION) {
                String description = earningDescription.getText();
                double amount = Double.parseDouble(earningAmountItem.getText());
                String category = (String) earningCategoryList.getSelectedItem();
                EarningCategories type = controller.account.convertToEarningCategory(category);
                Earning earning = new Earning(description, amount, type);
                controller.account.addEarning(earning);
                listModel.addElement(earningDescription.getText() + " : " + earningAmountItem.getText());
                deleteButton.setEnabled(true);
            }
        }
    }

    // EFFECTS: create the Image Icon in th Add Earning external Page.
    private int createImageIcon(JPanel panel) {
        Image myPicture = null;
        try {
            myPicture = ImageIO.read(new File("src/main/ui/image/EarningIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image newImage = myPicture.getScaledInstance(40,40, Image.SCALE_DEFAULT);
        ImageIcon img = new ImageIcon(newImage);
        int result = JOptionPane.showConfirmDialog(null, panel, "Adding new Earning",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, img);
        return result;
    }

    //EFFECTS: Check the Exceptions
    private void checkException(String name) {
        if (name == null | name.isEmpty() | name.equals("")) {
            JOptionPane.showMessageDialog(null, "You haven't enter the name for the Earning!",
                    "Oops...", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "You have already added this Earning's description "
                    + "to the list!", "Oops...", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //EFFECTS: create the Pane List for List of Earning
    private void createPaneList() {
        centerPanel = new JPanel(new BorderLayout());
        JPanel selection = createSelectionBar();
        accountPanel = createEarningList();

        accountPanel.setVisible(true);
        centerPanel.add(selection, BorderLayout.PAGE_START);
        centerPanel.add(accountPanel, BorderLayout.CENTER);
    }

    // EFFECTS: create the Earning List
    private JScrollPane createEarningList() {
        listModel = new DefaultListModel();
        for (Earning e : controller.account.getEarningList()) {
            listModel.addElement(e.getName() + " : " + e.getAmount());
        }
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(10);
        JScrollPane earningPanel = new JScrollPane(list);

        earningPanel.setBackground(new Color(255,255,255));
        return earningPanel;
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
        if (controller.account.getEarningList().isEmpty()) {
            deleteButton.setEnabled(false);
        }
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = list.getSelectedIndex();
                listModel.remove(index);
                Earning earning = controller.account.getEarningList().get(index);
                controller.account.removeEarning(earning);

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);

                if (controller.account.getEarningList().isEmpty()) {
                    deleteButton.setEnabled(false);
                }
            }
        });
    }

    // EFFECTS: create to view the Earning Total of the Account.
    private void createEarningAmount() {
        earningAmountLabel = new JLabel("Total Earning in this Account:");
        bottomPanel.add(earningAmountLabel);
        earningAmount = new JFormattedTextField();
        bottomPanel.add(earningAmount);
        double earningTotalAccount = controller.account.calculateTotalEarningAccount();
        earningAmount.setValue(earningTotalAccount);
        earningAmount.setEditable(false);
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

