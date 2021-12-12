package com.company;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.xml.bind.ValidationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends JFrame {
    private Client currentUser;
    private JPanel content;
    private Database db;

    public MainPage(Client client, Database db) {
        System.out.println("Setting up the main page.");
        currentUser = client;
        this.db = db;

        Color bg = new Color(41, 41, 41);
        Color fg = Color.white;

        ShowVideos();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Main Page");
        add(content);
        setResizable(false);
        setBackground(bg);

        setSize(800, 600);
        setVisible(true);
    }

    private Color bg, fg;

    private void ShowVideos() {
        bg = new Color(41, 41, 41);
        fg = Color.white;

        content = new JPanel();
        content.setOpaque(true);
        content.setLayout(new FlowLayout());
        content.setBackground(bg);

        JPanel navBar = CreateNavBar();

        content.add(navBar);

        String genres[] = new String[]{"Action", "Fantasy", "Drama", "Comedy"};
        String movieNames[][] = new String[][]{
                {"Red Notice", "John Wick", "Extraction", "Fast&Furious 5"},
                {"Mortal Kombat", "Legion", "Lord Of The Rings", "Pirates of Caribbean"},
                {"Casablanca", "Knives Out", "The Irishman", "The Godfather"},
                {"Bad Boys", "The Dictator", "Ted", "The Hangover"}};

        for (int i = 0; i < 4; i++) {
            JLabel genreName = new JLabel(genres[i]);
            genreName.setForeground(Color.white);
            content.add(genreName);

            JPanel videos = new JPanel(new FlowLayout());
            videos.setPreferredSize(new Dimension(800, 200));
            for (int j = 0; j < 4; j++) {
                videos.add(new Video(movieNames[i][j]));
            }

            videos.setBackground(bg);
            content.add(videos);
        }
    }

    private JPanel CreateNavBar() {
        JPanel navBar = new JPanel();
        navBar.setPreferredSize(new Dimension(800, 30));
        navBar.setBackground(bg);
        navBar.setBorder(new LineBorder(Color.lightGray, 1, false));
        navBar.setLayout(new BorderLayout());


        JLabel lblUsername = new JLabel(currentUser.getUsername());
        lblUsername.setForeground(fg);
        navBar.add(lblUsername, BorderLayout.LINE_START);

        JButton btnAccount = new JButton("Account");
        btnAccount.setForeground(fg);
        btnAccount.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        accountButton_Clicked(currentUser);
                    }
                }
        );
        navBar.add(btnAccount, BorderLayout.LINE_END);

        return navBar;
    }

    private void accountButton_Clicked(Client currentUser) {

        AccountPage ap = new AccountPage(currentUser,db);
    }
}
