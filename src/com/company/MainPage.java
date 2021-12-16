package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MainPage extends JFrame {
    private User user;
    private JPanel content;
    private Database db;
    private Color bg, fg;

    public MainPage(User user, Database db) {
        System.out.println("Setting up the main page.");
        this.user = user;
        this.db = db;
        bg = new Color(41, 41, 41);
        fg = Color.white;

        content = new JPanel();
        content.setOpaque(true);
        content.setLayout(new FlowLayout());
        content.setBackground(bg);

        JPanel navBar = getNavBar();

        content.add(navBar);


        JPanel videos = showVideos();
        JScrollPane scrollableTextArea = new JScrollPane(videos);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        content.add(scrollableTextArea);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Main Page");
        add(content);
        setResizable(false);
        setBackground(bg);

        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private JPanel showVideos() {
        String[] genres;
        String[][] movieNames;

        if (user.getLatestSubscription().getSubscriptionPlan() == SubscriptionPlan.Free) {
            genres = new String[]{ "Fantasy", "Drama", "Comedy"};
            movieNames = new String[][]{
                    {"Mortal Kombat", "Legion", "Pirates of the Caribbean"},
                    {"Casablanca",  "The Godfather"},
                    {"Bad Boys",  "Ted", "The Hangover"}};
        } else {
            genres = new String[]{"Action", "Fantasy", "Drama", "Comedy"};
            movieNames = new String[][]{
                    {"Red Notice", "John Wick", "Extraction", "Fast & Furious 5"},
                    {"Mortal Kombat", "Legion", "Lord Of The Rings", "Pirates of the Caribbean"},
                    {"Casablanca", "Knives Out", "The Irishman", "The Godfather"},
                    {"Bad Boys", "The Dictator", "Ted", "The Hangover"}};
        }

        JPanel videosPanel = new JPanel();
        videosPanel.setLayout(new FlowLayout());
        videosPanel.setPreferredSize(new Dimension(800, 750));

        videosPanel.setBackground(bg);

        Dimension videoSize = new Dimension(192, 108);
        Dimension panelSize = new Dimension(192, 180);
        for (int i = 0; i < genres.length; i++) {
            JLabel genreName = new JLabel(genres[i]);
            genreName.setForeground(Color.white);
            videosPanel.add(genreName);

            JPanel videosLine = new JPanel(new FlowLayout());
            videosLine.setPreferredSize(new Dimension(800, 200));

            for (int j = 0; j < movieNames[i].length; j++) {
                Video video = new Video(movieNames[i][j], videoSize, panelSize);
                if (video.getMovieName().contains("a"))
                    video.setNeedParentalControl(true);
                else
                    video.setNeedParentalControl(false);
                if (user.isParentalControlOn() && video.isNeedParentalControl()) {
                    JLabel lblParental = new JLabel("Parental control.");
                    lblParental.setForeground(fg);
                    video.add(lblParental);
                } else {
                    JButton btnMovie = new JButton("Watch");
                    btnMovie.setVerticalAlignment(SwingConstants.CENTER);
                    btnMovie.setForeground(fg);
                    btnMovie.addActionListener(
                            actionEvent -> btnMovie_Clicked(video.getMovieName())
                    );
                    video.add(btnMovie);
                }

                videosLine.add(video);

            }

            videosLine.setBackground(bg);
            videosPanel.add(videosLine);
        }
        return videosPanel;

    }

    private void btnMovie_Clicked(String movieName) {
        new VideoPage(user, db, movieName);
        dispose();
    }

    private JPanel getNavBar() {
        JPanel navBar = new JPanel();
        navBar.setPreferredSize(new Dimension(800, 30));
        navBar.setBackground(bg);
        navBar.setBorder(new LineBorder(Color.lightGray, 1, false));
        navBar.setLayout(new BorderLayout());


        JLabel lblUsername = new JLabel(user.getUsername());
        lblUsername.setForeground(fg);
        navBar.add(lblUsername, BorderLayout.LINE_START);

        JButton btnAccount = new JButton("Account");
        btnAccount.setForeground(fg);
        btnAccount.addActionListener(
                actionEvent -> accountButton_Clicked()
        );
        navBar.add(btnAccount, BorderLayout.LINE_END);

        return navBar;
    }

    private void accountButton_Clicked() {
        new AccountPage(user, db);
        dispose();
    }
}
