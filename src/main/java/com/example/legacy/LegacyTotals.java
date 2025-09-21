
package com.example.legacy;

import java.util.*;

/*
 * Messy legacy code: parsing, accumulation and summarization all in one method.
 * - Mutates maps with array values (double[])
 * - Uses raw Maps and Lists
 * - No separation of concerns and limited validation
 */
public class LegacyTotals {
    public static List<TotalsEntry> computeTotals(String data) {
        if (data == null || data.isEmpty()) return Collections.emptyList();
        String[] rows = data.split("\\r?\\n");
        Map<String, Summary> summary = new LinkedHashMap<>();
        for (String row : rows) {
            if (row == null || row.trim().isEmpty()) continue;
            String[] parts = row.split(",", 2);
            if (parts.length < 2) continue;
            String user = parts[0].trim();
            String raw = parts[1].trim();
            double price;
            try {
                price = Double.parseDouble(raw);
            } catch (Exception e) {
                // try to clean non-numeric chars
                String cleaned = raw.replaceAll("[^\\d.-]", "");
                try {
                    price = Double.parseDouble(cleaned);
                } catch (Exception ex) {
                    continue;
                }
            }
            if (!Double.isFinite(price)) continue;
            if (price < 0) continue; // ignore negative prices
            Summary s = summary.computeIfAbsent(user, k -> new Summary());
            s.total += price;
            s.count += 1;
        }
        List<TotalsEntry> out = new ArrayList<>();
        for (Map.Entry<String, Summary> e : summary.entrySet()) {
            Summary s = e.getValue();
            double avg = s.count > 0 ? s.total / s.count : 0.0;
            out.add(new TotalsEntry(e.getKey(), s.total, avg));
        }
        return out;
    }

    // helper class to accumulate totals
    private static class Summary {
        double total = 0.0;
        int count = 0;
    }
}
