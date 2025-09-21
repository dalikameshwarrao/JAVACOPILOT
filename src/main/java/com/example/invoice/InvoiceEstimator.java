
package com.example.invoice;

import java.util.HashMap;
import java.util.Map;

/*
 * Intentionally bad code:
 * - Uses a primitive 2D array for items (instead of a proper Item class)
 * - Rounds tax incorrectly using Math.round without scaling
 * - No input validation for negative qty or price types are assumed valid
 * - Duplicated code and poor naming
 */
public class InvoiceEstimator {

    // items: double[][] where each row is {price, qty}
    public static Map<String, Double> estimate(double[][] items, Double taxRate) {
        if (taxRate == null) {
            taxRate = 0.1;
        }
        double subtotal = 0.0;
        for (int i = 0; i < items.length; i++) {
            try {
                double price = items[i][0];
                double qty = items[i][1];
                subtotal += price * qty;
            } catch (Exception e) {
                // swallow exceptions and continue - bad practice
            }
        }
        // BUG: incorrect rounding - Math.round returns long and without scaling leads to integer rounding
        double tax = Math.round(subtotal * taxRate);
        double total = subtotal + tax;
        Map<String, Double> out = new HashMap<>();
        out.put("subtotal", subtotal);
        out.put("tax", tax);
        out.put("total", total);
        return out;
    }
}
