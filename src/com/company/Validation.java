package com.company;

import javax.swing.*;
import javax.xml.bind.ValidationException;

public class Validation {
    public static boolean ClientInfo(Client client)
    {
        System.out.println("Validating client info.");
        try {
            if (client.getUsername().length() < 1)
                throw new EmptyUsernameException();

            if (client.getPassword().length() < 1)
                throw new EmptyPasswordException();

            System.out.println("Validation successful.");
            return true;
        } catch (ValidationException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
            return false;
        }
    }
}
