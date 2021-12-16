package com.company;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class VideoPage extends JFrame {

    private User user;
    private Database db;

    private Color bg, fg;

    public VideoPage(User user, Database db, String movieName) {
        System.out.println("Setting up the video page.");
        this.setUser(user);
        this.setDb(db);

        bg = new Color(41, 41, 41);
        fg = Color.white;

        JPanel content = new JPanel(new FlowLayout());

        JPanel navBar = createNavBar();
        content.add(navBar);

        Dimension videoSize = new Dimension(730, 410);
        Dimension panelSize = new Dimension(800, 600);
        Video video = new Video(movieName, videoSize, panelSize);
        JPanel videoSettings = createVideoSettings();
        video.add(videoSettings);
        content.add(video);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Video Page");
        add(content);
        setResizable(false);
        setBackground(bg);

        setSize(800, 600);
        setLocationRelativeTo(null);

        setVisible(true);
    }

    private JPanel createVideoSettings() {
        JPanel videoSettings = new JPanel();
        videoSettings.setPreferredSize(new Dimension(730, 30));
        videoSettings.setBackground(bg);
        videoSettings.setBorder(new LineBorder(Color.lightGray, 1, false));
        videoSettings.setLayout(new GridLayout(1, 6));

        videoSettings.add(Utils.getPlaceholderPanel());
        videoSettings.add(Utils.getPlaceholderPanel());
        videoSettings.add(Utils.getPlaceholderPanel());

        JSlider volumeSlider = new JSlider();
        JLabel volumeLabel = new JLabel("Volume:");
        volumeLabel.setLabelFor(volumeSlider);
        volumeLabel.setForeground(fg);
        videoSettings.add(volumeLabel);

        videoSettings.add(volumeSlider);

        JComboBox cbVideoQuality = new JComboBox(user.getVideoQualities());
        videoSettings.add(cbVideoQuality);

        return videoSettings;
    }


    private JPanel createNavBar() {
        JPanel navBar = new JPanel();
        navBar.setPreferredSize(new Dimension(800, 30));
        navBar.setBackground(bg);
        navBar.setBorder(new LineBorder(Color.lightGray, 1, false));
        navBar.setLayout(new BorderLayout());

        JButton btnBack = new JButton("Back");
        btnBack.setForeground(fg);
        btnBack.addActionListener(
                actionEvent -> btnBack_Clicked()
        );
        navBar.add(btnBack, BorderLayout.LINE_START);

        JLabel lblUsername = new JLabel(user.getUsername());
        lblUsername.setForeground(fg);
        navBar.add(lblUsername, BorderLayout.LINE_END);

        return navBar;
    }

    private void btnBack_Clicked() {
        new MainPage(getUser(), getDb());
        dispose();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }
}
