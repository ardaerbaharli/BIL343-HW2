package com.company;

import javax.swing.*; // Buttons, text fields, ...
import java.awt.*; // Container, Color,
import javax.swing.border.LineBorder;
import java.awt.event.*;//ActionListener


class Form extends JPanel {


    public JLabel lblUsername, lblPassword;
    public JTextField txtUsername, txtPassword;
    public JButton btnSubmit;

    public Form(FormType formType) {
        setLayout(new GridLayout(3, 2, 2, 2));
        setBorder(BorderFactory.createTitledBorder(formType.name()));

        lblUsername = new JLabel("Username:");
        txtUsername = new JTextField(20);
        lblUsername.setLabelFor(txtUsername);
        lblUsername.setBorder(new LineBorder(null, 0, false));

        lblPassword = new JLabel("Password:");
        txtPassword = new JTextField(20);
        lblPassword.setLabelFor(txtUsername);
        lblPassword.setBorder(new LineBorder(null, 0, false));

        btnSubmit = new JButton();
        btnSubmit.setHorizontalAlignment(SwingConstants.CENTER);

        add(lblUsername);
        add(txtUsername);
        add(lblPassword);
        add(txtPassword);
        add(btnSubmit);
    }
}

public class MainPage extends JFrame {

    private JPanel content;

    public MainPage() {

        content = new JPanel();
        content.setOpaque(true);
        content.setBackground(Color.WHITE);
        content.setLayout(new GridLayout(2, 1));

        Form loginForm = new Form(FormType.Login);
        loginForm.btnSubmit.setText("Login");
        loginForm.btnSubmit.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        loginButton_Clicked(loginForm.txtUsername.getText(), loginForm.txtPassword.getText());
                    }
                }
        );

        content.add(loginForm);

        Form registerForm = new Form(FormType.Register);

        JComboBox cb = new JComboBox(SubscriptionPlan.values());
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
        setTitle("Main Page");
        add(content);
        setResizable(false);
        pack();
        setVisible(true);
    }

    private void loginButton_Clicked(String username, String password) {
        System.out.println("Login");
        System.out.println(username);
        System.out.println(password);
    }

    private void registerButton_Clicked(Client client) {
        System.out.println("REgister");
        System.out.println(client.getUsername());
        System.out.println(client.getPassword());
        System.out.println(client.getSubscriptionPlan());
    }
}
