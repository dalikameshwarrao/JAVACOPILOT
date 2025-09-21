
package com.example.hotspot;

import java.util.*;

/*
 * Intentionally slow implementation (O(n^2)) to find all pairs summing to a target.
 * This is correct but inefficient for large inputs.
 */
public class Hotspot {
    public static List<int[]> findPairs(int[] nums, int target) {
        List<int[]> pairs = new ArrayList<>();
        for (int i=0;i<nums.length;i++) {
            for (int j=i+1;j<nums.length;j++) {
                if (nums[i] + nums[j] == target) {
                    pairs.add(new int[]{nums[i], nums[j]});
                }
            }
        }
        return pairs;
    }
}
