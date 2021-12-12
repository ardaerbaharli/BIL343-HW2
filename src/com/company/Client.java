package com.company;

import java.time.LocalDate;
import java.util.*;

public class Client {
    private String username;
    private String password;
    private SubscriptionPlan subscriptionPlan;
    private boolean parentalControl;
    private List<LocalDate> renewalDates;

    public Client(String username, String password, SubscriptionPlan subscriptionPlan) {
        System.out.println("Creating a client.");
        this.setUsername(username);
        this.setPassword(password);
        this.setSubscriptionPlan(subscriptionPlan);
        this.setParentalControl(false);
        setRenewalDates(new ArrayList<>());
    }


    public Client(String username, String password, SubscriptionPlan subscriptionPlan, boolean parentalControl) {
        System.out.println("Creating a client.");
        this.setUsername(username);
        this.setPassword(password);
        this.setSubscriptionPlan(subscriptionPlan);
        this.setParentalControl(parentalControl);
        setRenewalDates(new ArrayList<>());
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

    public boolean isParentalControlOn() {
        return parentalControl;
    }

    public void setParentalControl(boolean parentalControl) {
        this.parentalControl = parentalControl;
    }

    public List<LocalDate> getRenewalDates() {
        return renewalDates;
    }

    public void setRenewalDates(List<LocalDate> renewalDates) {
        this.renewalDates = renewalDates;
    }

    public void addRenewalDate(LocalDate renewalDate) {
        this.renewalDates.add(renewalDate);
    }

    public void removeRenewalDate(LocalDate renewalDate) {
        this.renewalDates.remove(renewalDate);
    }
}
