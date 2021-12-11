package com.company;

public class ClientDoesNotExistException extends RuntimeException {
    public ClientDoesNotExistException()
    {
        super("Client does not exists!");
    }
}
