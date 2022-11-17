package ui;

import javax.swing.*;

public abstract class Tab extends JPanel {
    protected AccountPage controller;

    //REQUIRES: SmartHomeUI controller that holds this tab
    public Tab(AccountPage controller) {
        this.controller = controller;
    }
}
