package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;
import java.util.List;

public class AccountPage extends JFrame {

    private JLabel lblUsername, lblPassword, lblSubscriptionPlan, lblParental;
    private JTextField txtUsername, txtPassword;
    private JCheckBox checkParental;
    private JButton btnUpdate, btnRenewSubscription, btnInvoices, btnBack, btnReports;
    private JComboBox cbSubscriptionPlan;
    private JList invoiceList;
    private DefaultListModel invoiceListModel;
    private JPanel panel, topBar, accountSettingsPanel, subscriptionSettingsPanel, invoicesPanel;

    private User user;
    private Database db;

    public AccountPage(User user, Database db) {
        System.out.println("Creating account page.");

        this.user = user;
        this.db = db;

        Color bg = new Color(41, 41, 41);
        Color fg = Color.white;

        panel = new JPanel();
        panel.setForeground(fg);
        panel.setBackground(bg);

        panel.setLayout(new FlowLayout());
        //////////      //////////      //////////      //////////
        topBar = new JPanel();
        topBar.setPreferredSize(new Dimension(400, 30));
        topBar.setBackground(bg);
        topBar.setForeground(fg);

        topBar.setLayout(new BorderLayout());


        btnBack = new JButton();
        btnBack.setText("Back");
        btnBack.setForeground(fg);
        btnBack.addActionListener(
                actionEvent -> btnBack_Clicked()
        );
        topBar.add(btnBack, BorderLayout.LINE_START);

        btnReports = new JButton();
        btnReports.setText("Reports");
        btnReports.setForeground(fg);
        btnReports.addActionListener(
                actionEvent -> btnReports_Clicked()
        );

        topBar.add(btnReports, BorderLayout.LINE_END);

        //////////      //////////      //////////      //////////
        accountSettingsPanel = new JPanel();
        accountSettingsPanel.setForeground(fg);
        accountSettingsPanel.setBackground(bg);
        accountSettingsPanel.setPreferredSize(new Dimension(400, 170));
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

        accountSettingsPanel.add(lblUsername);
        accountSettingsPanel.add(txtUsername);

        accountSettingsPanel.add(lblPassword);
        accountSettingsPanel.add(txtPassword);

        accountSettingsPanel.add(lblParental);
        accountSettingsPanel.add(checkParental);

        accountSettingsPanel.add(Utils.getPlaceholderPanel());
        accountSettingsPanel.add(btnUpdate);

        //////////      //////////      //////////      //////////

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


        subscriptionSettingsPanel = new JPanel();
        subscriptionSettingsPanel.setForeground(fg);
        subscriptionSettingsPanel.setBackground(bg);
        subscriptionSettingsPanel.setPreferredSize(new Dimension(400, 100));

        subscriptionSettingsPanel.setLayout(new GridLayout(2, 2, 2, 2));
        subscriptionSettingsPanel.setBorder(BorderFactory.createTitledBorder(null, "Subscription Settings", TitledBorder.CENTER, 0, null, fg));

        subscriptionSettingsPanel.add(lblSubscriptionPlan);
        subscriptionSettingsPanel.add(cbSubscriptionPlan);

        subscriptionSettingsPanel.add(Utils.getPlaceholderPanel());
        subscriptionSettingsPanel.add(btnRenewSubscription);

        //////////      //////////      //////////      //////////
        invoicesPanel = new JPanel();
        invoicesPanel.setForeground(fg);
        invoicesPanel.setBackground(bg);
        invoicesPanel.setPreferredSize(new Dimension(400, 100));

//        invoicesPanel.setLayout(new GridLayout(2, 2, 2, 2));
        invoicesPanel.setLayout(new FlowLayout());
        invoicesPanel.setBorder(BorderFactory.createTitledBorder(null, "Invoices", TitledBorder.CENTER, 0, null, fg));

        btnInvoices = new JButton();
        btnInvoices.setText("Show Invoice");
        btnInvoices.setHorizontalAlignment(SwingConstants.CENTER);
        btnInvoices.setForeground(fg);
        btnInvoices.addActionListener(
                actionEvent -> btnInvoicesClicked()
        );

        invoiceListModel = new DefaultListModel();
        invoiceList = new JList(invoiceListModel);
        invoiceList.setPreferredSize(new Dimension(400, 400));
        JScrollPane scroll = new JScrollPane(invoiceList);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        invoicesPanel.add(btnInvoices);

        invoicesPanel.add(scroll);

        List<Subscription> subscriptions = user.getSubscriptions();
        for (Subscription sub : subscriptions) {
            System.out.println(sub.getInvoice().getFileName());
            invoiceListModel.addElement(sub.getInvoice().getFileName());
        }
        System.out.println(invoiceListModel.size());

        //////////      //////////      //////////      //////////
        panel.add(topBar);
        panel.add(accountSettingsPanel);
        panel.add(subscriptionSettingsPanel);
        panel.add(invoicesPanel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        setTitle("Account");
        setResizable(false);
        setSize(400, 450);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void btnReports_Clicked() {
        new ReportsPage(user, db);
        dispose();
    }

    private void btnBack_Clicked() {
        new MainPage(user, db);
        dispose();
    }

    private void btnInvoicesClicked() {
        try {
            if (invoiceList.isSelectionEmpty())
                throw new SelectionEmptyException();

            String invoiceName = invoiceList.getSelectedValue().toString();
            Desktop desktop = Desktop.getDesktop();
            File file = new File(invoiceName);
            desktop.open(file);

        } catch (SelectionEmptyException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void btnRenewSubscriptionClicked(SubscriptionPlan newSubscriptionPlan) {
        Subscription subscription = new Subscription(newSubscriptionPlan, user.getUsername());
        user.renewSubscription(subscription);
        db.insertSubscription(subscription);

        db.updateUser(user.getUsername(), user);
        user = db.getUser(user.getUsername());
        user.setSubscriptions(db.getSubscriptionsByUsername(user.getUsername()));

        new MainPage(user, db);
        dispose();
    }

    private void btnUpdate_Clicked(String username, User updatedUser) {
        if (!Utils.validateUser(updatedUser))
            return;

        db.updateUser(username, updatedUser);
        new MainPage(updatedUser, db);
        dispose();
    }
}
