
package com.example.invoice;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceEstimatorTest {

    @Test
    public void testEstimate_basic() {
        double[][] items = {{12.5, 2}, {5.0, 1}};
        Map<String, Double> res = InvoiceEstimator.estimate(items, 0.08);
        // expected subtotal = 12.5*2 + 5 = 30.0
        assertEquals(30.0, res.get("subtotal"), 0.0001);
        // expected tax = round(30.0 * 0.08, 2) = 2.40
        assertEquals(2.40, res.get("tax"), 0.0001, "Tax should be rounded to 2 decimals");
        assertEquals(32.40, res.get("total"), 0.0001);
    }

    @Test
    public void testEstimate_defaultTax() {
        double[][] items = {{10.0, 1}};
        Map<String, Double> res = InvoiceEstimator.estimate(items, null);
        assertEquals(10.0, res.get("subtotal"), 0.0001);
        // default taxRate = 0.1 -> tax = 1.00
        assertEquals(1.00, res.get("tax"), 0.0001);
    }

    @Test
    public void testEstimate_invalidPriceReturns400LikeBehavior() {
        // Since this is a library not server, we assert that invalid rows are skipped and don't crash
        double[][] items = {{Double.NaN, 1}, {5.0, 2}};
        Map<String, Double> res = InvoiceEstimator.estimate(items, 0.1);
        assertEquals(10.0, res.get("subtotal"), 0.0001);
    }
}
