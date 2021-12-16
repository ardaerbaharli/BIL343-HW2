package com.company;

import java.time.LocalDate;
import java.util.*;

public class User {
    private String username;
    private String password;
    private boolean parentalControl;
    private List<Subscription> subscriptions;

    public User(String username, String password, Subscription subscription) {
        this.setUsername(username);
        this.setPassword(password);
        this.setSubscriptions(new ArrayList<>());
        this.addSubscription(subscription);
        this.setParentalControl(false);
    }

    public User(String username, String password, boolean parentalControl) {
        this.setUsername(username);
        this.setPassword(password);
        this.setSubscriptions(new ArrayList<>());
        this.setParentalControl(false);
    }


    public User(String username, String password, Subscription subscription, boolean parentalControl) {
        this.setUsername(username);
        this.setPassword(password);
        this.setSubscriptions(new ArrayList<>());
        this.addSubscription(subscription);
        this.setParentalControl(parentalControl);
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

    public boolean isParentalControlOn() {
        return parentalControl;
    }

    public void setParentalControl(boolean parentalControl) {
        this.parentalControl = parentalControl;
    }

    public void renewSubscription(Subscription subscription) {
        Subscription reSub = new Subscription(subscription.getSubscriptionPlan(), subscription.getUsername(), LocalDate.now());
        addSubscription(reSub);
    }

    public List<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public Subscription getLatestSubscription() {
        if (subscriptions.size() > 0)
            return subscriptions.get(subscriptions.size() - 1);
        return null;
    }

    public void setSubscriptions(List<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public void addSubscription(Subscription subscription) {
        this.subscriptions.add(subscription);
    }

    public void removeSubscription(Subscription subscription) {
        this.subscriptions.remove(subscription);
    }

    public String[] getVideoQualities() {
        String[][] videoQualities = new String[][]{
                new String[]{"144p"},
                new String[]{"144p", "360p"},
                new String[]{"144p", "360p", "480p"},
                new String[]{"144p", "360p", "480p", "720p"}};

        SubscriptionPlan plan = getLatestSubscription().getSubscriptionPlan();
        return videoQualities[plan.ordinal()];

    }
}
