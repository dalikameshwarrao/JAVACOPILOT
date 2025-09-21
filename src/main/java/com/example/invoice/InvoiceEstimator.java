
package com.example.invoice;

import java.util.HashMap;
import java.util.Map;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class InvoiceEstimator {

    // items: double[][] where each row is {price, qty}
    public static Map<String, Double> estimate(double[][] items, Double taxRate) {
        if (taxRate == null) {
            taxRate = 0.1;
        }
        double subtotal = 0.0;
        for (int i = 0; i < items.length; i++) {
            // basic validation: row must have at least 2 elements and values must be finite and non-negative
            if (items[i] == null || items[i].length < 2) {
                continue;
            }
            double price = items[i][0];
            double qty = items[i][1];
            // skip invalid numbers (NaN/Infinity) and negative values
            if (!Double.isFinite(price) || !Double.isFinite(qty)) {
                continue;
            }
            if (price < 0 || qty < 0) {
                continue;
            }
            subtotal += price * qty;
        }
        // Round tax to 2 decimal places (HALF_UP)
        double taxUnrounded = subtotal * taxRate;
        double tax = BigDecimal.valueOf(taxUnrounded)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
        double total = subtotal + tax;
        Map<String, Double> out = new HashMap<>();
        out.put("subtotal", subtotal);
        out.put("tax", tax);
        out.put("total", total);
        return out;
    }
}
