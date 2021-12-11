package com.company;

public class RegistrationFailedException extends RuntimeException {
    public RegistrationFailedException()
    {
        super("Something went wrong");
    }
}
