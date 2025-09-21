
package com.example.legacy;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LegacyTotalsTest {

    @Test
    public void testComputeTotals_basic() {
        String csv = "alice,10.5\nbob,5\nalice,4.5\n";
        List<com.example.legacy.TotalsEntry> res = LegacyTotals.computeTotals(csv);
        boolean foundAlice = false;
        for (com.example.legacy.TotalsEntry e : res) {
            if (e.getUser().equals("alice")) {
                foundAlice = true;
                assertEquals(15.0, e.getTotal(), 0.0001);
                assertEquals(7.5, e.getAverage(), 0.0001);
            }
        }
        assertTrue(foundAlice, "alice must be present in results");
    }
}
