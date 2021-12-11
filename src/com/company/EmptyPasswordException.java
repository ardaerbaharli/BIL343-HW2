package com.company;

import javax.xml.bind.ValidationException;

public class EmptyPasswordException extends ValidationException {
    public EmptyPasswordException()
    {
        super("Password cannot be empty!");
    }
}
