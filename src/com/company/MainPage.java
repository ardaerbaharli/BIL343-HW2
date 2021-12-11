package com.company;

import javax.swing.*;

public class MainPage extends JFrame {
    private Client currentUser;

    public MainPage(Client client) {
        System.out.println("Loading main page.");
        currentUser = client;
    }
}
