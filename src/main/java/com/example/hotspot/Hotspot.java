
package com.example.hotspot;

import java.util.*;

/*
 * Intentionally slow implementation (O(n^2)) to find all pairs summing to a target.
 * This is correct but inefficient for large inputs.
 */
public class Hotspot {
    // Improved: O(n) time using a map of seen values -> counts
    // Returns list of pairs [a,b] where a is the earlier-seen value and b is the current value
    public static List<int[]> findPairs(int[] nums, int target) {
        List<int[]> pairs = new ArrayList<>();
        if (nums == null || nums.length < 2) return pairs;
        Map<Integer, Integer> seen = new HashMap<>(); // value -> count
        for (int x : nums) {
            int complement = target - x;
            Integer cnt = seen.getOrDefault(complement, 0);
            if (cnt > 0) {
                // add one pair for each previous occurrence of complement
                for (int i = 0; i < cnt; i++) {
                    pairs.add(new int[]{complement, x});
                }
            }
            seen.put(x, seen.getOrDefault(x, 0) + 1);
        }
        return pairs;
    }
}
