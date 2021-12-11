package com.company;

public class ClientAlreadyExistsException extends RuntimeException{
    public ClientAlreadyExistsException(){
        super("The username is already in use.");
    }
}
