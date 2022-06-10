package com.company;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReportsPage extends JFrame {
    private User user;
    private JPanel panel, topBar, userPanel, platformPanel;
    private Database db;
    private Color bg, fg;
    private JButton btnBack, btnUserReports, btnPlatformReports;

    public ReportsPage(User user, Database db) {
        System.out.println("Setting up the reports page.");
        this.user = user;
        this.db = db;
        bg = new Color(41, 41, 41);
        fg = Color.white;

        //////////////////        //////////////////        //////////////////

        panel = new JPanel();
        panel.setForeground(fg);
        panel.setBackground(bg);
        panel.setLayout(new FlowLayout());

        //////////////////        //////////////////        //////////////////

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

        //////////////////        //////////////////        //////////////////

        userPanel = new JPanel();
        userPanel.setForeground(fg);
        userPanel.setBackground(bg);
        userPanel.setPreferredSize(new Dimension(400, 110));
        userPanel.setLayout(new GridBagLayout());

        btnUserReports = new JButton();
        btnUserReports.setText("Show User Reports");
        btnUserReports.setHorizontalAlignment(SwingConstants.CENTER);
        btnUserReports.setPreferredSize(new Dimension(250, 40));
        btnUserReports.setForeground(fg);
        btnUserReports.addActionListener(
                actionEvent -> btnUserReports_Clicked()
        );

        userPanel.add(btnUserReports);

        //////////////////        //////////////////        //////////////////

        platformPanel = new JPanel();
        platformPanel.setForeground(fg);
        platformPanel.setBackground(bg);
        platformPanel.setPreferredSize(new Dimension(400, 110));
        platformPanel.setLayout(new GridBagLayout());

        btnPlatformReports = new JButton();
        btnPlatformReports.setText("Show Platform Reports");
        btnPlatformReports.setHorizontalAlignment(SwingConstants.CENTER);
        btnPlatformReports.setPreferredSize(new Dimension(250, 40));
        btnPlatformReports.setForeground(fg);
        btnPlatformReports.addActionListener(
                actionEvent -> btnPlatformReports_Clicked()
        );

        platformPanel.add(btnPlatformReports);

        //////////////////        //////////////////        //////////////////

        panel.add(topBar);
        panel.add(userPanel);
        panel.add(platformPanel);

        //////////////////        //////////////////        //////////////////

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Reports");
        add(panel);
        setResizable(false);
        setBackground(bg);

        setSize(400, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void btnPlatformReports_Clicked() {

        try {
            List<Subscription> subs =  db.getAllSubscriptions();
            List<User> users = db.getAllUsers();
            Report platformReport = new Report(subs, users);
            Desktop desktop = Desktop.getDesktop();
            File file = new File(platformReport.getFileName());
            desktop.open(file);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void btnBack_Clicked() {
        new AccountPage(user, db);
        dispose();
    }

    private void btnUserReports_Clicked() {
        try {
            Report userReport = new Report(user);
            Desktop desktop = Desktop.getDesktop();
            File file = new File(userReport.getFileName());
            desktop.open(file);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
