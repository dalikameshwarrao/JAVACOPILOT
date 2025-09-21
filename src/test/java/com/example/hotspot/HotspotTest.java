
package com.example.hotspot;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HotspotTest {

    @Test
    public void testFindPairs_basic() {
        int[] nums = {1,2,3,4,5};
        List<int[]> pairs = Hotspot.findPairs(nums, 6);
        // expected pairs: (1,5) and (2,4) - order not important
        boolean has15 = false;
        boolean has24 = false;
        for (int[] p : pairs) {
            if (p[0] == 1 && p[1] == 5) has15 = true;
            if (p[0] == 2 && p[1] == 4) has24 = true;
        }
        assertTrue(has15 && has24, "Should find (1,5) and (2,4)"); 
    }
}
