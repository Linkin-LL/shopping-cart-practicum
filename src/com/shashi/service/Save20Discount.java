package com.shashi.service;

public class Save20Discount implements DiscountStrategy {
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.80; // Apply 20% discount
    }

    @Override
    public String getDiscountCode() {
        return "SAVE20";
    }
}
