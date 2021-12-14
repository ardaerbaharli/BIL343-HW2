package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AccountPage extends JFrame {

    private JLabel lblUsername, lblPassword, lblSubscriptionPlan, lblParental;
    private JTextField txtUsername, txtPassword;
    private JCheckBox checkParental;
    private JButton btnUpdate, btnRenewSubscription, btnInvoices;
    private JComboBox cbSubscriptionPlan;

    private User user;
    private Database db;

    public AccountPage(User user, Database db) {
        System.out.println("Creating account page.");

        this.user = user;
        this.db = db;

        Color bg = new Color(41, 41, 41);
        Color fg = Color.white;

        JPanel panel = new JPanel();
        panel.setForeground(fg);
        panel.setBackground(bg);

        panel.setLayout(new GridLayout(4, 1, 2, 2));

        JButton btnBack = new JButton("Back");
        btnBack.setForeground(fg);
        btnBack.addActionListener(
                actionEvent -> btnBack_Clicked()
        );
        panel.add(btnBack);

        JPanel accountSettingsPanel = new JPanel();
        accountSettingsPanel.setForeground(fg);
        accountSettingsPanel.setBackground(bg);

        accountSettingsPanel.setLayout(new GridLayout(4, 2, 2, 2));
        accountSettingsPanel.setBorder(BorderFactory.createTitledBorder(null, "Account Settings", TitledBorder.CENTER, 0, null, fg));


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
                actionEvent -> {
                    String newUsername = txtUsername.getText();
                    String newPassword = txtPassword.getText();
                    SubscriptionPlan subscriptionPlan = (SubscriptionPlan) cbSubscriptionPlan.getSelectedItem();
                    Subscription subscription = new Subscription(subscriptionPlan, newUsername);
                    boolean parentalControl = checkParental.isSelected();

                    User updatedUser = new User(newUsername, newPassword, subscription, parentalControl);

                    btnUpdate_Clicked(user.getUsername(), updatedUser);
                }
        );

        btnUpdate.setBorder(BorderFactory.createLineBorder(fg));


        lblSubscriptionPlan = new JLabel("Subscription Plan:");
        lblSubscriptionPlan.setLabelFor(cbSubscriptionPlan);
        lblSubscriptionPlan.setForeground(fg);
        lblSubscriptionPlan.setBorder(new LineBorder(null, 0, false));

        cbSubscriptionPlan = new JComboBox(SubscriptionPlan.values());
        cbSubscriptionPlan.setBackground(bg);
        cbSubscriptionPlan.setForeground(fg);
        cbSubscriptionPlan.setSelectedItem(user.getLatestSubscription().getSubscriptionPlan());

        btnRenewSubscription = new JButton();
        btnRenewSubscription.setText("Renew subscription");
        btnRenewSubscription.setHorizontalAlignment(SwingConstants.CENTER);
        btnRenewSubscription.setForeground(fg);
        btnRenewSubscription.addActionListener(
                actionEvent -> {
                    SubscriptionPlan subscriptionPlan = (SubscriptionPlan) cbSubscriptionPlan.getSelectedItem();
                    btnRenewSubscriptionClicked(subscriptionPlan);
                }
        );

        accountSettingsPanel.add(lblUsername);
        accountSettingsPanel.add(txtUsername);

        accountSettingsPanel.add(lblPassword);
        accountSettingsPanel.add(txtPassword);

        accountSettingsPanel.add(lblParental);
        accountSettingsPanel.add(checkParental);

        accountSettingsPanel.add(btnUpdate);

        JPanel subscriptionSettingsPanel = new JPanel();
        subscriptionSettingsPanel.setForeground(fg);
        subscriptionSettingsPanel.setBackground(bg);

        subscriptionSettingsPanel.setLayout(new GridLayout(2, 2, 2, 2));
        subscriptionSettingsPanel.setBorder(BorderFactory.createTitledBorder(null, "Subscription Settings", TitledBorder.CENTER, 0, null, fg));

        subscriptionSettingsPanel.add(lblSubscriptionPlan);
        subscriptionSettingsPanel.add(cbSubscriptionPlan);
        subscriptionSettingsPanel.add(btnRenewSubscription);

        JPanel invoicesPanel = new JPanel();
        invoicesPanel.setForeground(fg);
        invoicesPanel.setBackground(bg);

        invoicesPanel.setLayout(new FlowLayout());
        invoicesPanel.setBorder(BorderFactory.createTitledBorder(null, "Invoices", TitledBorder.CENTER, 0, null, fg));

        btnInvoices = new JButton();
        btnInvoices.setText("Show Invoices");
        btnInvoices.setHorizontalAlignment(SwingConstants.CENTER);
        btnInvoices.setForeground(fg);
        btnInvoices.addActionListener(
                actionEvent -> btnInvoicesClicked()
        );

        panel.add(accountSettingsPanel);
        panel.add(subscriptionSettingsPanel);

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        setTitle("Account");
        setResizable(false);
        pack();
        setVisible(true);
    }

    private void btnBack_Clicked() {
         new MainPage(user, db);
        dispose();
    }

    private void btnInvoicesClicked() {
        createInvoices();
    }

    private void createInvoices() {

    }

    private void btnRenewSubscriptionClicked(SubscriptionPlan newSubscriptionPlan) {
        Subscription subscription = new Subscription(newSubscriptionPlan, user.getUsername());
        user.renewSubscription(subscription);
        db.insertSubscription(subscription);

        user = db.getUser(user.getUsername());
        db.updateUser(user.getUsername(), user);

         new MainPage(user, db);
        dispose();
    }

    private void btnUpdate_Clicked(String username, User updatedUser) {
        if (!Validation.clientInfo(updatedUser))
            return;

        db.updateUser(username, updatedUser);
        new MainPage(updatedUser, db);
        dispose();
    }
}
