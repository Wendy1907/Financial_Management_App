package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainTab extends Tab {

    public MainTab(AccountPage controller) {
        super(controller);

        setLayout(new GridLayout(3, 1));
        setVisible(true);


        welcomeText();
        logoPanel();
        data();
    }

    //EFFECTS: show the welcome message at the top of the first panel
    private void welcomeText() {
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JLabel greeting = new JLabel(
                "Welcome\n"
                + "to your Account!",
                JLabel.CENTER);
        JLabel subTitle = new JLabel("Shop smart, spend wise!", JLabel.CENTER);
        greeting.setFont(new Font("Verdana", Font.BOLD, 16));
        subTitle.setFont(new Font("Verdana", Font.ITALIC, 14));

        topPanel.add(greeting);
        topPanel.add(subTitle);
        this.add(topPanel);
    }

    // EFFECTS: create a logo panel that display the app's image logo
    private void logoPanel() {
        try {
            BufferedImage myPicture = ImageIO.read(new File("src/main/ui/image/MoneyIcon.png"));
            Image newImage = myPicture.getScaledInstance(90,
                    90, Image.SCALE_DEFAULT);
            JLabel picLabel = new JLabel(new ImageIcon(newImage));
            add(picLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void data() {
        JPanel dataRow = new JPanel(new GridLayout(2,2));
        this.add(dataRow);

        JLabel spendingAmountLabel = new JLabel("Spending Amount:");
        JFormattedTextField spendingAmount = new JFormattedTextField();
        dataRow.add(spendingAmountLabel);
        dataRow.add(spendingAmount);
        double spendingAmountAccount = controller.account.calculateTotalSpendingAccount();
        spendingAmount.setValue(spendingAmountAccount);
        spendingAmount.setEditable(false);

        JLabel earningAmountLabel = new JLabel("Earning Amount:");
        JFormattedTextField earningAmount = new JFormattedTextField();
        dataRow.add(earningAmountLabel);
        dataRow.add(earningAmount);
        double earningAmountAccount = controller.account.calculateTotalEarningAccount();
        earningAmount.setValue(earningAmountAccount);
        earningAmount.setEditable(false);
    }
}
