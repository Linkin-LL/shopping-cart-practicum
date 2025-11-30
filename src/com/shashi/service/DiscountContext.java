package com.shashi.service;

import java.util.HashSet;
import java.util.Set;

public class DiscountContext {
    private Set<String> appliedDiscountCodes = new HashSet<>();

    // Apply a discount if the code is valid and not already used
    public double applyDiscount(String code, double amount) {
        DiscountStrategy discountStrategy = getDiscountStrategyByCode(code);
        if (discountStrategy != null && !appliedDiscountCodes.contains(code)) {
            appliedDiscountCodes.add(code);  // Mark this code as applied
            return discountStrategy.applyDiscount(amount);
        }
        return amount;  // No discount applied if code is invalid or already used
    }

    // Get the DiscountStrategy based on the discount code
    private DiscountStrategy getDiscountStrategyByCode(String code) {
        switch (code.toUpperCase()) {
            case "SAVE10":
                return new Save10Discount();
            case "SAVE20":
                return new Save20Discount();
            default:
                return null;  // Invalid code
        }
    }
}
