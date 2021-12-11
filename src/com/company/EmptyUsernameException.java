package com.company;

import javax.xml.bind.ValidationException;

public class EmptyUsernameException extends ValidationException {
    public EmptyUsernameException()
    {
        super("Username cannot be empty!");
    }
}
