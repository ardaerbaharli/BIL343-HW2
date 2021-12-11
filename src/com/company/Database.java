package com.company;

public class Database {
    public Database() {
        System.out.println("Initializing the database.");
        CreateTable();
    }

    public boolean CreateTable() {
        System.out.println("Creating the table.");
        return true;
    }

    public boolean InsertRecord(Client client) {
        System.out.println("Insetring a record.");
        return true;

    }

    public boolean RemoveRecord(String username) {
        System.out.println("Removing a record.");
        Client client = GetRecord(username);
        return true;

    }

    public boolean DoesExists(String username) {
        System.out.println("Checking if the user exist.");
        return true;

    }

    public Client GetRecord(String username) {
        System.out.println("Retrieving a client.");
        return new Client("", "", null);
    }
}
