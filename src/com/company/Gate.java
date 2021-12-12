package com.company;

import javax.swing.*; // Buttons, text fields, ...
import java.awt.*; // Container, Color,
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.*;//ActionListener
import javax.swing.JOptionPane;
import javax.xml.bind.ValidationException;


class Form extends JPanel {

    public JLabel lblUsername, lblPassword;
    public JTextField txtUsername, txtPassword;
    public JButton btnSubmit;

    public Form(FormType formType) {
        System.out.println("Creating " + formType.name() + " form.");
        Color bg = new Color(41, 41, 41);
        Color fg = Color.white;

        setForeground(fg);
        setBackground(bg);

        setLayout(new GridLayout(3, 2, 2, 2));
        setBorder(BorderFactory.createTitledBorder(null, formType.name(), 0, 0, null, fg));

        lblUsername = new JLabel("Username:");
        lblUsername.setLabelFor(txtUsername);
        lblUsername.setForeground(fg);
        lblUsername.setBorder(new LineBorder(null, 0, false));

        txtUsername = new JTextField(20);
        txtUsername.setForeground(fg);
        txtUsername.setBackground(bg);
        txtUsername.setBorder(BorderFactory.createLineBorder(fg));

        lblPassword = new JLabel("Password:");
        lblPassword.setLabelFor(txtPassword);
        lblPassword.setForeground(fg);
        lblPassword.setBorder(new LineBorder(null, 0, false));

        txtPassword = new JTextField(20);
        txtPassword.setForeground(fg);
        txtPassword.setBackground(bg);
        txtPassword.setBorder(BorderFactory.createLineBorder(fg));

        btnSubmit = new JButton();
        btnSubmit.setHorizontalAlignment(SwingConstants.CENTER);
        btnSubmit.setForeground(fg);

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnSubmit);
    }
}

public class Gate extends JFrame {

    private JPanel content;
    private Database db;

    public Gate() {
        System.out.println("Setting up the gate forms.");
        db = new Database();

        Color bg = new Color(41, 41, 41);
        Color fg = Color.white;

        content = new JPanel();
        content.setOpaque(true);
        content.setLayout(new GridLayout(2, 1));

        Form loginForm = new Form(FormType.Login);
        loginForm.btnSubmit.setText("Login");
        loginForm.btnSubmit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String username = loginForm.txtUsername.getText();
                        String password = loginForm.txtPassword.getText();
                        loginButton_Clicked(new Client(username, password, null));
                    }
                }
        );

        content.add(loginForm);

        Form registerForm = new Form(FormType.Register);
        JComboBox cb = new JComboBox(SubscriptionPlan.values());
        cb.setBackground(bg);
        cb.setForeground(fg);
        registerForm.add(cb);

        registerForm.btnSubmit.setText("Register");
        registerForm.btnSubmit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        String username = registerForm.txtUsername.getText();
                        String password = registerForm.txtPassword.getText();
                        SubscriptionPlan sb = (SubscriptionPlan) cb.getSelectedItem();
                        registerButton_Clicked(new Client(username, password, sb));
                    }
                }
        );

        content.add(registerForm);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Login or Register");
        add(content);
        setResizable(false);
        pack();
        setVisible(true);
    }

    private void loginButton_Clicked(Client client) {
        System.out.println("Login button clicked.");
        if (!Validation.ClientInfo(client))
            return;

        try {
            if (!db.DoesExists(client.getUsername()))
                throw new ClientDoesNotExistException();
            else {
                Client currentUser = db.GetRecord(client.getUsername());
                MainPage mp = new MainPage(currentUser, db);
            }
        } catch (ClientDoesNotExistException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    private void registerButton_Clicked(Client client) {
        if (!Validation.ClientInfo(client))
            return;

        try {
            if (db.DoesExists(client.getUsername()))
                throw new ClientAlreadyExistsException();
            else {
                boolean result = db.InsertRecord(client);
                if (!result)
                    throw new RegistrationFailedException();
                else {
                    System.out.println("Loading the main page.");
                    MainPage mp = new MainPage(client, db);
                }
            }
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}

