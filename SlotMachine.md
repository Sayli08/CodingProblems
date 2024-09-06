


## Problem Statement: SlotWheels

You are given a slot machine with multiple wheels. Each wheel spins multiple times (i.e., one spin per row). The numbers displayed on the wheels for each spin are recorded in a 2D array called `history[]`. Each element in `history` represents a single spin, and the numbers in the strings represent the numbers displayed by each wheel during that spin.

Your task is to calculate the **minimum number of stops** required for the wheels to show all the numbers from each spin.

For each wheel, you need to determine the maximum number of stops required to display all the necessary numbers for each spin, while progressively reducing the number of values left to process.

### Input Format:
- The first line contains an integer `n` representing the number of spins.
- The next `n` lines contain strings of digits where each string represents a spin. Each character in the string corresponds to a number shown on a wheel during that spin.

### Output Format:
- Print a single integer representing the minimum total number of stops required for the wheels to display all numbers from each spin.

### Example 1:

#### Input:
```
4
712
246
365
312
```

#### Explanation:
1. In the first spin (`712`), the maximum value is **7**. Therefore, the wheels must make **7 stops** to show this spin. We remove the highest values from the remaining rows (`6` from `246`, `5` from `365`, and `3` from `312`).
2. Now, the remaining highest value is **5**. The wheels need **5 stops** to show this spin. We remove the next highest values from the remaining rows (`4` from `246`, `3` from `365`, and `2` from `312`).
3. Finally, the remaining highest value is **3**. The wheels need **3 stops** for the remaining spins.

The total number of stops is `7 + 5 + 3 = 15`.

#### Output:
```
15
```

### Example 2:

#### Input:
```
3
935
642
431
```

#### Explanation:
1. In the first spin (`935`), the maximum value is **9**. Therefore, the wheels must make **9 stops** to show this spin. We remove the highest values from the remaining rows (`6` from `642`, and `4` from `431`).
2. Now, the remaining highest value is **6**. The wheels need **6 stops** to show this spin. We remove the next highest values from the remaining rows (`5` from `642` and `3` from `431`).
3. Finally, the remaining highest value is **4**. The wheels need **4 stops** for the remaining spins.

The total number of stops is `9 + 6 + 4 = 19`.

#### Output:
```
19
```

### Constraints:
- `1 <= n <= 100`
- Each string in `history[]` contains only digits from `1` to `9`.

### Objective:
The goal is to calculate the **minimum number of total stops** needed to cover all the numbers across the spins, using the strategy of progressively finding and removing the highest values.

---

# Solution
```java
import java.util.Arrays;
import java.util.List;

class Result {

    /*
     * Complete the 'slotWheels' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING_ARRAY history as parameter.
     */

    public static int slotWheels(List<String> history) {
        int n = history.size();  // Number of spins
        int numWheels = history.get(0).length();  // Number of wheels (derived from the length of the first string)
        int totalStops = 0;

        // Convert the input history into a 2D array of integers
        int[][] spins = new int[n][numWheels];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < numWheels; j++) {
                spins[i][j] = Character.getNumericValue(history.get(i).charAt(j));
            }
        }

        // Continue until all elements are covered
        while (true) {
            int maxValue = 0;

            // Find the maximum value in the entire array
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < numWheels; j++) {
                    maxValue = Math.max(maxValue, spins[i][j]);
                }
            }

            // If maxValue is 0, we've removed all elements, break the loop
            if (maxValue == 0) {
                break;
            }

            // Add this maximum value to the total stops
            totalStops += maxValue;

            // Remove the largest value less than or equal to maxValue in each row
            for (int i = 0; i < n; i++) {
                int maxInRow = 0;

                // Find the largest value in the row that is less than or equal to maxValue
                for (int j = 0; j < numWheels; j++) {
                    if (spins[i][j] <= maxValue) {
                        maxInRow = Math.max(maxInRow, spins[i][j]);
                    }
                }

                // Remove the found maxInRow by setting it to 0 (covered)
                for (int j = 0; j < numWheels; j++) {
                    if (spins[i][j] == maxInRow) {
                        spins[i][j] = 0;
                        break;
                    }
                }
            }
        }

        return totalStops;
    }

    public static void main(String[] args) {
        // Input directly in the code
        List<String> history = Arrays.asList("712", "246", "365", "312");

        // Call the slotWheels function and print the result
        int result = slotWheels(history);

        // Output the result
        System.out.println("Total Stops: " + result);
    }
}
```
## Explaination



**Task**:  
The goal is to calculate the **minimum number of stops** required for the wheels to show all the numbers from each spin.

1. **Step-by-step Process**:  
   For each set of spins, we first find the **highest number** across all spins. We spin the wheels enough times to display that highest number. Once we've spun the wheels for this highest number, all **smaller numbers** that appear on the wheels in **other spins** will also be covered by those stops.

   **Example**:
   - Let’s say the first spin is **712**, and the highest number in this spin is **7**.
   - We will stop the wheels **7 times** because we need to spin them enough to show the largest number, which is **7**.
   - Now, since the wheels have already spun 7 times, any number **smaller than 7** that appears in **other spins** will also be shown. This means we don’t need additional stops for those smaller numbers.

2. **Why Do We Remove Smaller Numbers?**  
   After spinning the wheels 7 times, we have already covered any numbers **less than or equal to 7** that appear in **other spins**. For example:
   - Second spin (`246`): The highest number ≤ 7 is **6**, which is already covered by the 7 stops.
   - Third spin (`365`): The highest number ≤ 7 is **5**, also covered by the 7 stops.
   - Fourth spin (`312`): The highest number ≤ 7 is **3**, covered by the same 7 stops.

   Therefore, we **remove** these numbers from their respective rows/spins because they’ve already been covered by the previous stops.

3. **Next Steps**:  
   After removing **7, 6, 5, and 3**, we repeat the process by finding the next highest number among the remaining spins and continue reducing the numbers until all spins are completely covered.

