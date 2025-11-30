package com.shashi.service;

public class Save10Discount implements DiscountStrategy {
    @Override
    public double applyDiscount(double amount) {
        return amount * 0.90; // Apply 10% discount
    }

    @Override
    public String getDiscountCode() {
        return "SAVE10";
    }
}
