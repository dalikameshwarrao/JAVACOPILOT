package com.example.invoice;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class InvoiceEstimatorEdgeTest {

    @Test
    public void testEstimate_withNaNAndNegativeValues() {
        double[][] items = {{Double.NaN, 1}, {-5.0, 2}, {10.0, 1}};
        Map<String, Double> res = InvoiceEstimator.estimate(items, 0.1);
        // Only valid item is 10.0 -> subtotal 10.0, tax 1.0
        assertEquals(10.0, res.get("subtotal"), 0.0001);
        assertEquals(1.0, res.get("tax"), 0.0001);
    }

    @Test
    public void testEstimate_emptyItems() {
        double[][] items = {};
        Map<String, Double> res = InvoiceEstimator.estimate(items, 0.2);
        assertEquals(0.0, res.get("subtotal"), 0.0001);
        assertEquals(0.0, res.get("tax"), 0.0001);
        assertEquals(0.0, res.get("total"), 0.0001);
    }
}
