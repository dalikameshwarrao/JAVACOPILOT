package com.example.legacy;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LegacyTotalsEdgeTest {

    @Test
    public void testComputeTotals_malformedRowsAndNegatives() {
        String csv = "alice,10.0\ninvalidrow\nbob,-5\ncharlie,$20\nalice,5\n";
        List<TotalsEntry> res = LegacyTotals.computeTotals(csv);
        // alice should have 15.0 total (10 + 5), bob negative ignored, charlie 20 parsed from $20
        boolean foundAlice = false;
        boolean foundCharlie = false;
        for (TotalsEntry e : res) {
            if (e.getUser().equals("alice")) {
                foundAlice = true;
                assertEquals(15.0, e.getTotal(), 0.0001);
            }
            if (e.getUser().equals("charlie")) {
                foundCharlie = true;
                assertEquals(20.0, e.getTotal(), 0.0001);
            }
        }
        assertTrue(foundAlice);
        assertTrue(foundCharlie);
    }

    @Test
    public void testComputeTotals_emptyInput() {
        List<TotalsEntry> res = LegacyTotals.computeTotals("");
        assertTrue(res.isEmpty());
    }
}
