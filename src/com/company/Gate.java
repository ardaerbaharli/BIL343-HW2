package com.company;

import javax.swing.*; // Buttons, text fields, ...
import java.awt.*; // Container, Color,
import javax.swing.border.LineBorder;
import java.time.LocalDate;
import javax.swing.JOptionPane;


class Form extends JPanel {

    public JLabel lblUsername, lblPassword;
    public JTextField txtUsername, txtPassword;
    public JButton btnSubmit;
    private final Color bg;
    private final Color fg;

    public Form(FormType formType) {
        System.out.println("Creating " + formType.name() + " form.");
        bg = new Color(41, 41, 41);
        fg = Color.white;

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

    private final JPanel content;
    private final Database db;

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
                actionEvent -> {
                    String username = loginForm.txtUsername.getText();
                    String password = loginForm.txtPassword.getText();
                    loginButton_Clicked(new User(username, password, null));
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
                actionEvent -> {
                    String username = registerForm.txtUsername.getText();
                    String password = registerForm.txtPassword.getText();
                    SubscriptionPlan sb = (SubscriptionPlan) cb.getSelectedItem();
                    Subscription subscription = new Subscription(sb, username, LocalDate.now());
                    registerButton_Clicked(new User(username, password, subscription), subscription);
                }
        );

        content.add(registerForm);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Login or Register");
        add(content);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loginButton_Clicked(User user) {
        System.out.println("Login button clicked.");
        if (!Utils.validateUser(user))
            return;

        try {
            if (!db.doesExistsInUsers(user.getUsername()))
                throw new ClientDoesNotExistException();
            else {
                User currentUser = db.getUser(user.getUsername());
                currentUser.setSubscriptions(db.getSubscriptionsByUsername(currentUser.getUsername()));
                if (!currentUser.getPassword().equals(user.getPassword())) {
                    JOptionPane.showMessageDialog(null, "Username or password is not correct!");
                } else {
                    new MainPage(currentUser, db);
                    dispose();
                }
            }
        } catch (ClientDoesNotExistException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

    }

    private void registerButton_Clicked(User user, Subscription subscription) {
        if (!Utils.validateUser(user))
            return;

        try {
            if (db.doesExistsInUsers(user.getUsername()))
                throw new ClientAlreadyExistsException();
            else {
                boolean result = db.insertUser(user);
                System.out.println(result);
                db.insertSubscription(subscription);
                if (!result)
                    throw new RegistrationFailedException();
                else {

                    System.out.println("Loading the main page.");
                    new MainPage(user, db);
                    dispose();
                }
            }
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }
}

