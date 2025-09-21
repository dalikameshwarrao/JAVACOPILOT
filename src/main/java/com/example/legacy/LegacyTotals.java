
package com.example.legacy;

import java.util.*;

/*
 * Messy legacy code: parsing, accumulation and summarization all in one method.
 * - Mutates maps with array values (double[])
 * - Uses raw Maps and Lists
 * - No separation of concerns and limited validation
 */
public class LegacyTotals {
    public static List<Map<String, Object>> computeTotals(String data) {
        String[] rows = data.split("\n");
        Map<String, double[]> result = new HashMap<>();
        for (int i = 0; i < rows.length; i++) {
            String r = rows[i];
            if (r.equals("")) continue;
            String[] parts = r.split(",");
            String user = parts[0].trim();
            double price = 0;
            try {
                price = Double.parseDouble(parts[1]);
            } catch (Exception e) {
                String cleaned = parts[1].replaceAll("[^\\d.-]", "");
                try { price = Double.parseDouble(cleaned); } catch (Exception ex) { continue; }
            }
            if (!result.containsKey(user)) result.put(user, new double[]{0.0, 0.0});
            double[] cur = result.get(user);
            cur[0] = cur[0] + price;
            cur[1] = cur[1] + 1;
            result.put(user, cur);
        }
        List<Map<String, Object>> out = new ArrayList<>();
        for (String u : result.keySet()) {
            double[] v = result.get(u);
            Map<String,Object> m = new HashMap<>();
            m.put("user", u);
            m.put("total", v[0]);
            m.put("average", v[0] / v[1]);
            out.add(m);
        }
        return out;
    }
}
