


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

You can upload this Markdown file as is. Let me know if you need any adjustments!
