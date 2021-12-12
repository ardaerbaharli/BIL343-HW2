package com.company;

import javax.swing.*;
import java.awt.*;

public class Video extends JPanel {
    public JButton btnMovie;
    public JLabel lblTitle;

    public Video(String title) {
        Color bg = new Color(61, 61, 61);
        Color fg = Color.white;

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createTitledBorder(null, title, 0, 0, null, fg));

        JPanel img = new JPanel();
        img.setPreferredSize(new Dimension(120, 120));
        img.setBackground(Color.lightGray);

        btnMovie = new JButton("Watch");
        btnMovie.setVerticalAlignment(SwingConstants.CENTER);
        btnMovie.setForeground(fg);

        add(img);
        add(btnMovie);

        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(180, 180));
        setBackground(bg);
        setForeground(fg);

    }
}
