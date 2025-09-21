
package com.example.legacy;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class LegacyTotalsTest {

    @Test
    public void testComputeTotals_basic() {
        String csv = "alice,10.5\nbob,5\nalice,4.5\n";
        List<Map<String, Object>> res = LegacyTotals.computeTotals(csv);
        // find alice entry
        boolean foundAlice = false;
        for (Map<String,Object> m : res) {
            if (m.get("user").equals("alice")) {
                foundAlice = true;
                assertEquals(15.0, (Double) m.get("total"), 0.0001);
                assertEquals(7.5, (Double) m.get("average"), 0.0001);
            }
        }
        assertTrue(foundAlice, "alice must be present in results");
    }
}
