package com.example.hotspot;

import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HotspotEdgeTest {

    @Test
    public void testFindPairs_withDuplicates() {
        int[] nums = {3,3,3,3};
        List<int[]> pairs = Hotspot.findPairs(nums, 6);
        // With 4 elements of 3, number of pairs = C(4,2) = 6
        assertEquals(6, pairs.size());
    }

    @Test
    public void testFindPairs_emptyOrNull() {
        assertTrue(Hotspot.findPairs(new int[]{}, 10).isEmpty());
        assertTrue(Hotspot.findPairs(null, 10).isEmpty());
    }
}
