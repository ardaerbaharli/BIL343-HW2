package com.company;

import java.time.LocalDate;

public class Subscription {
    private SubscriptionPlan subscriptionPlan;
    private LocalDate date;
    private String username;

    public Subscription(SubscriptionPlan subscriptionPlan, String username) {
        this.setSubscriptionPlan(subscriptionPlan);
        this.setUsername(username);
        this.setDate(LocalDate.now());
    }

    public Subscription(SubscriptionPlan subscriptionPlan, String username, LocalDate subDate) {
        this.setSubscriptionPlan(subscriptionPlan);
        this.setUsername(username);
        this.setDate(subDate);
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
