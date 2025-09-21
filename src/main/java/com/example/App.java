package com.example;

import com.example.invoice.InvoiceEstimator;
import com.example.legacy.LegacyTotals;
import com.example.hotspot.Hotspot;

import java.util.Arrays;
import java.util.Map;

/**
 * Entry point for the sample application demonstrating
 * InvoiceEstimator, LegacyTotals, and Hotspot functionalities.
 */
public class App {
    public static void main(String[] args) {
        runInvoiceEstimatorSample();
        runLegacyTotalsSample();
        runHotspotSample();
    }

    private static void runInvoiceEstimatorSample() {
        System.out.println("=== InvoiceEstimator sample ===");
        double[][] items = { {12.5, 2}, {5.0, 1} }; // price, qty
        Map<String, Double> result = InvoiceEstimator.estimate(items, 0.08);
        System.out.println(result);
        System.out.println();
    }

    private static void runLegacyTotalsSample() {
        System.out.println("=== LegacyTotals sample ===");
        String csv = "alice,10.5\nbob,5\nalice,4.5\n";
        Map<String, Double> totals = LegacyTotals.computeTotals(csv);
        System.out.println(totals);
        System.out.println();
    }

    private static void runHotspotSample() {
        System.out.println("=== Hotspot sample ===");
        int[] nums = {1, 2, 3, 4, 5};
        var pairs = Hotspot.findPairs(nums, 6);
        System.out.println(Arrays.toString(pairs));
    }
}
