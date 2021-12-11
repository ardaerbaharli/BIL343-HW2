package com.company;

public class Client {
    private String username;
    private String password;
    private SubscriptionPlan subscriptionPlan;
    private boolean parentalControl;

    public Client(String username, String password, SubscriptionPlan subscriptionPlan) {
        System.out.println("Creating a client.");
        this.setUsername(username);
        this.setPassword(password);
        this.setSubscriptionPlan(subscriptionPlan);
        this.setParentalControl(false);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public boolean isParentalControl() {
        return parentalControl;
    }

    public void setParentalControl(boolean parentalControl) {
        this.parentalControl = parentalControl;
    }
}
