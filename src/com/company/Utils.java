package com.company;

import javax.swing.*;
import javax.xml.bind.ValidationException;
import java.awt.*;
import java.io.File;

public class Utils {
    public static boolean validateUser(User user) {
        System.out.println("Validating user info.");
        try {
            if (user.getUsername().length() < 1)
                throw new EmptyUsernameException();

            if (user.getPassword().length() < 1)
                throw new EmptyPasswordException();

            System.out.println("Validation successful.");
            return true;
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }

    public static JPanel getPlaceholderPanel() {
        Color bg = new Color(41, 41, 41);
        JPanel p = new JPanel();
        p.setBackground(bg);
        return p;
    }

    public static void cleanDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        for (File file : directory.listFiles())
            if (!file.isDirectory())
                file.delete();
    }
}
