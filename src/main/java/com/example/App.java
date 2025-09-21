
package com.example;

import com.example.invoice.InvoiceEstimator;
import com.example.legacy.LegacyTotals;
import com.example.hotspot.Hotspot;

import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) {
        System.out.println("=== InvoiceEstimator sample ===");
        double[][] items = {{12.5, 2}, {5.0, 1}}; // price, qty
        Map<String, Double> res = InvoiceEstimator.estimate(items, 0.08);
        System.out.println(res);
        System.out.println();

        System.out.println("=== LegacyTotals sample ===");
        String csv = "alice,10.5\nbob,5\nalice,4.5\n";
    System.out.println(LegacyTotals.computeTotals(csv));
        System.out.println();

        System.out.println("=== Hotspot sample ===");
        int[] nums = {1,2,3,4,5};
        List<int[]> pairs = Hotspot.findPairs(nums, 6);
        for (int[] p : pairs) {
            System.out.println(p[0] + "," + p[1]);
        }
    }
}
