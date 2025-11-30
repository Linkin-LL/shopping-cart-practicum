package com.shashi.service;

public interface DiscountStrategy {
    // Method to apply the discount to the amount
    double applyDiscount(double amount);

    // Method to get the discount code for the strategy
    String getDiscountCode();
}
