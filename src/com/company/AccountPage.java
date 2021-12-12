package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPage extends JFrame {

    private JLabel lblUsername, lblPassword, lblSubscriptionPlan, lblParental;
    private JTextField txtUsername, txtPassword;
    private JCheckBox checkParental;
    private JButton btnUpdate;
    private JComboBox cbSubscriptionPlan;

    private Database db;

    public AccountPage(Client user, Database db) {
        System.out.println("Creating account page.");

        this.db = db;

        Color bg = new Color(41, 41, 41);
        Color fg = Color.white;

        JPanel panel = new JPanel(new FlowLayout());
        panel.setForeground(fg);
        panel.setBackground(bg);

        panel.setLayout(new GridLayout(5, 2, 2, 2));
        panel.setBorder(BorderFactory.createTitledBorder(null, "Account Settings", TitledBorder.CENTER, 0, null, fg));

        lblUsername = new JLabel("Username:");
        lblUsername.setLabelFor(txtUsername);
        lblUsername.setForeground(fg);
        lblUsername.setBorder(new LineBorder(null, 0, false));

        txtUsername = new JTextField(20);
        txtUsername.setText(user.getUsername());
        txtUsername.setForeground(fg);
        txtUsername.setBackground(bg);
        txtUsername.setBorder(BorderFactory.createLineBorder(fg));

        lblPassword = new JLabel("Password:");
        lblPassword.setLabelFor(txtPassword);
        lblPassword.setForeground(fg);
        lblPassword.setBorder(new LineBorder(null, 0, false));

        txtPassword = new JTextField(20);
        txtPassword.setText(user.getPassword());
        txtPassword.setForeground(fg);
        txtPassword.setBackground(bg);
        txtPassword.setBorder(BorderFactory.createLineBorder(fg));

        lblSubscriptionPlan = new JLabel("Subscription Plan:");
        lblSubscriptionPlan.setLabelFor(cbSubscriptionPlan);
        lblSubscriptionPlan.setForeground(fg);
        lblSubscriptionPlan.setBorder(new LineBorder(null, 0, false));

        cbSubscriptionPlan = new JComboBox(SubscriptionPlan.values());
        cbSubscriptionPlan.setBackground(bg);
        cbSubscriptionPlan.setForeground(fg);

        lblParental = new JLabel("Parental Control:");
        lblParental.setLabelFor(checkParental);
        lblParental.setForeground(fg);
        lblParental.setBorder(new LineBorder(null, 0, false));

        checkParental = new JCheckBox("", user.isParentalControlOn());

        btnUpdate = new JButton();
        btnUpdate.setText("Update account");
        btnUpdate.setHorizontalAlignment(SwingConstants.CENTER);
        btnUpdate.setForeground(fg);
        btnUpdate.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String newUsername = txtUsername.getText();
                        String newPassword = txtPassword.getText();
                        SubscriptionPlan subscriptionPlan = (SubscriptionPlan) cbSubscriptionPlan.getSelectedItem();
                        boolean parentalControl = checkParental.isSelected();

                        Client updatedClient = new Client(newUsername, newPassword, subscriptionPlan, parentalControl);

                        btnUpdate_Clicked(user.getUsername(), updatedClient);
                    }
                }
        );

        panel.add(lblUsername);
        panel.add(txtUsername);

        panel.add(lblPassword);
        panel.add(txtPassword);

        panel.add(lblSubscriptionPlan);
        panel.add(cbSubscriptionPlan);

        panel.add(lblParental);
        panel.add(checkParental);

        panel.add(btnUpdate);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        setTitle("Account");
        setResizable(false);
        pack();
        setVisible(true);
    }

    private void btnUpdate_Clicked(String username, Client updatedClient) {
        if (!Validation.ClientInfo(updatedClient))
            return;

        db.UpdateUser(username, updatedClient);
//        setVisible(false);
        dispose();
    }
}
