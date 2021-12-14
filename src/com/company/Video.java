package com.company;

import javax.swing.*;
import java.awt.*;

public class Video extends JPanel {
    private String movieName;
    private boolean needParentalControl;

    public Video(String movieName, Dimension videoSize, Dimension panelSize) {
        Color bg = new Color(61, 61, 61);
        Color fg = Color.white;
        setMovieName(movieName);

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setBorder(BorderFactory.createTitledBorder(null, movieName, 0, 0, null, fg));

        JPanel img = new JPanel();
        img.setPreferredSize(videoSize);
        img.setBackground(Color.lightGray);

        add(img);

        setLayout(new FlowLayout());
        setPreferredSize(panelSize);
        setBackground(bg);
        setForeground(fg);
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public boolean isNeedParentalControl() {
        return needParentalControl;
    }

    public void setNeedParentalControl(boolean needParentalControl) {
        this.needParentalControl = needParentalControl;
    }
}
