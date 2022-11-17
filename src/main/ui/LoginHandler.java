package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the Action Listener for the Login Page
 */
public class LoginHandler implements ActionListener {
    private LoginView loginView;

    // constructor
    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }


    // MODIFIES: this
    // EFFECTS: let app drink it page run if login successfully, otherwise give pop out reminder
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        if ("Clear".equals(text)) {
            loginView.getUserNameInput().setText("");
            loginView.getPwdInput().setText("");
        } else if ("Log In".equals(text)) {
            String user = loginView.getUserNameInput().getText();
            char[] chars = loginView.getPwdInput().getPassword();
            String pwd = new String(chars);
            System.out.println(user + " : " + pwd);


            if (isAdmin()) {
                new Home();
                System.out.println("true");
                loginView.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(loginView, "user name or password incorrect");
            }
        }
    }

    // EFFECTS: to see whether the username and password is correct
    public boolean isAdmin() {
        char[] chars = loginView.getPwdInput().getPassword();
        String pwd = new String(chars);
        System.out.println(loginView.getUserNameInput().getText());
        System.out.println(pwd);
        if (("anhthu19".equals(loginView.getUserNameInput().getText())) && pwd.equals("210")) {
            return true;
        } else {
            return false;
        }
    }

}
