
Project: poc-bad-code

Overview

This repository started as a small Java/Maven sample containing intentionally messy code and a few failing tests. The goal for this pass was to:

- Refactor messy code into clearer, maintainable functions and classes.
- Fix logic errors discovered by the unit tests.
- Improve algorithmic hotspots (replace O(n^2) algorithms with O(n) where safe).
- Update and add unit tests so the project is fully verified.

What I changed (high level)

- InvoiceEstimator
  - Fixed tax rounding (now uses BigDecimal to round to 2 decimal places with HALF_UP).
  - Added input validation to skip rows with NaN/Infinity, negative values, or malformed data.
- Hotspot
  - Replaced the O(n^2) pair-finding logic with an O(n) solution using a HashMap (complements map).
  - Preserved the behavior of returning all matching pairs but now scales much better.
- LegacyTotals
  - Introduced `TotalsEntry` POJO (typed return) instead of raw Map and mutated double[] arrays.
  - Rewrote parsing with clearer validation and accumulation logic.
- App and Tests
  - Updated `App` sample output for readability.
  - Updated tests to work with refactored APIs and added minor robustness improvements to assertions.

How AI assisted

This session used AI to accelerate refactoring and verification:

- Troubleshooting: AI parsed failing test stack traces to identify buggy methods and failing assertions.
- Refactors: AI suggested and implemented improvements (input validation, BigDecimal rounding, algorithmic change from O(n^2) to O(n), and typed return objects).
- Tests: AI updated tests to reflect API changes and improved assertions where helpful.

Before vs After (concrete examples)

Messy example (before - excerpt from original `LegacyTotals`):

    Map<String,double[]> result = new HashMap<>();
    for (int i=0;i<rows.length;i++){
      String r = rows[i];
      if (r.equals("")) continue;
      String[] parts = r.split(",");
      String user = parts[0].trim();
      double price = Double.parseDouble(parts[1]);
      if (!result.containsKey(user)) result.put(user, new double[]{0.0,0.0});
      double[] cur = result.get(user);
      cur[0] = cur[0] + price;
      cur[1] = cur[1] + 1;
      result.put(user, cur);
    }

Refactored (after - excerpt from `LegacyTotals`):

    Map<String,Summary> summary = new LinkedHashMap<>();
    for (String row : rows) {
      if (row==null || row.trim().isEmpty()) continue;
      String[] parts = row.split(",",2);
      if (parts.length < 2) continue;
      String user = parts[0].trim();
      double price = parseAndCleanPrice(parts[1]);
      if (!Double.isFinite(price) || price < 0) continue;
      Summary s = summary.computeIfAbsent(user, k -> new Summary());
      s.total += price; s.count += 1;
    }
    // then build List<TotalsEntry>

And a bug fix example (InvoiceEstimator) — before:

    double tax = Math.round(subtotal * taxRate); // wrong: Math.round returns long and no scaling

After:

    double tax = BigDecimal.valueOf(subtotal * taxRate)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();

How to run

From project root (Windows PowerShell):

```powershell
mvn -DskipTests=false package
java -cp target\poc-bad-code-0.1.0-SNAPSHOT.jar com.example.App
```

All unit tests pass after the refactors.

Next steps and suggestions

- Add more unit tests for edge cases and malformed rows.
- Replace primitive item arrays (double[][]) with a proper `Item` class to make invoice APIs clearer.
- Add logging, configuration, and better error reporting if this moves to production.
- Consider performance benchmarks for `Hotspot.findPairs` with large inputs.

If you'd like, I can add the README as a commit and push more tests or documentation next.

Short changelog
---------------

- 2025-09-21: Refactored `InvoiceEstimator` to validate inputs and fix rounding bug (BigDecimal). Added input validation to skip malformed rows.
- 2025-09-21: Rewrote `Hotspot.findPairs` from O(n^2) to O(n) using a complements map.
- 2025-09-21: Introduced `TotalsEntry` POJO and refactored `LegacyTotals` to return typed entries with clean parsing and validation.
- 2025-09-21: Updated unit tests and expanded README documentation.

Additional before/after snippets
-------------------------------

Hotspot (before):

  for (int i=0;i<nums.length;i++) {
    for (int j=i+1;j<nums.length;j++) {
      if (nums[i] + nums[j] == target) {
        pairs.add(new int[]{nums[i], nums[j]});
      }
    }
  }

Hotspot (after):

  Map<Integer,Integer> seen = new HashMap<>();
  for (int x : nums) {
    int complement = target - x;
    Integer cnt = seen.getOrDefault(complement, 0);
    if (cnt > 0) {
      for (int i=0;i<cnt;i++) pairs.add(new int[]{complement, x});
    }
    seen.put(x, seen.getOrDefault(x, 0) + 1);
  }

Changelog notes
---------------

The changes are intentionally small and low-risk; they maintain behavior while improving safety and clarity. `LegacyTotals` does change its return type to `List<TotalsEntry>` to make the API typed and clearer.
