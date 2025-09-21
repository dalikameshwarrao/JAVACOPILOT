
package com.example.hotspot;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class HotspotTest {

    @Test
    public void testFindPairs_basic() {
        int[] nums = {1,2,3,4,5};
        List<int[]> pairs = Hotspot.findPairs(nums, 6);
        // expected pairs: (1,5) and (2,4) - order not important
        Set<String> seen = new HashSet<>();
        for (int[] p : pairs) {
            seen.add(p[0] + "," + p[1]);
        }
        assertTrue(seen.contains("1,5") && seen.contains("2,4"), "Should find (1,5) and (2,4)");
    }
}
