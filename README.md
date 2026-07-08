# Comparison of Tim Sort and Quick Sort

## Project Overview

This project is a Java-based algorithm analysis application developed for the CME 2204 Algorithm Analysis course.

The main purpose of the project is to implement and compare two sorting algorithms:

- Tim Sort
- Quick Sort

The comparison is performed on four different input types:

- Random data
- Semi-ordered data
- Increasing-order data
- Decreasing-order data

The project measures sorting times in milliseconds and writes the sorted arrays into output files. The experiment focuses on how different input structures and pivot selection strategies affect algorithm performance.

According to the assignment report, each dataset contains 1,000,000 integer values. The measured time includes only the sorting process. Array creation and file writing are not included in the sorting time measurement.

---

## Main Features

The project includes the following features:

- Reads random integer data from `random.txt`
- Reads semi-ordered integer data from `semi_ordered.txt`
- Generates increasing-order integer array in code
- Generates decreasing-order integer array in code
- Implements Tim Sort manually
- Implements Quick Sort manually
- Supports five different Quick Sort pivot strategies
- Measures sorting time using `System.nanoTime()`
- Copies datasets before sorting to keep comparisons fair
- Writes sorted results into output files
- Automatically creates an `outputs` folder
- Prints a performance comparison table to the console

---

## Project Structure

The source code is organized under the `Assignment` package.

```text
2023510186
├── src
│   ├── Assignment
│   │   ├── FileReader.java
│   │   ├── QuickSort.java
│   │   ├── Test.java
│   │   └── TimSort.java
│   └── module-info.java
```

---

## Source Code Files

### 1. Test.java

`Test.java` is the main driver class of the project.

It controls the full experiment flow. This class reads or generates the datasets, creates copies of the arrays, calls the sorting algorithms, measures the execution time, writes the sorted outputs into files, and prints the final performance table.

Main responsibilities:

- Creates a `FileReader` object
- Reads `random.txt`
- Reads `semi_ordered.txt`
- Generates increasing-order data
- Generates decreasing-order data
- Runs Tim Sort on all datasets
- Runs Quick Sort with five pivot strategies on all datasets
- Measures execution time using `System.nanoTime()`
- Writes sorted arrays into the `outputs` folder
- Prints a table of measured sorting times

The project uses the following dataset names:

```java
String[] dataNames = {"random", "semi_ordered", "increasing", "decreasing"};
```

The test class runs Tim Sort first and then runs Quick Sort with these pivot types:

```java
QuickSort.PivotType.FIRST
QuickSort.PivotType.LAST
QuickSort.PivotType.MIDDLE
QuickSort.PivotType.RANDOM
QuickSort.PivotType.MEDIAN
```

---

### 2. FileReader.java

`FileReader.java` is responsible for file reading, dataset generation, output folder creation, and writing sorted arrays into files.

Main responsibilities:

- Reads the random dataset from:

```java
private String randomfile = "src/random.txt";
```

- Reads the semi-ordered dataset from:

```java
private String semifile = "src/semi_ordered.txt";
```

- Uses a dataset size of:

```java
private final int DATA_SIZE = 1000000;
```

- Creates the `outputs` folder automatically
- Writes sorted results into output files
- Generates increasing-order array
- Generates decreasing-order array

The method:

```java
public int[] generateIncreasingArray()
```

creates an array like:

```text
1, 2, 3, 4, 5, ...
```

The method:

```java
public int[] generateDecreasingArray()
```

creates an array like:

```text
1000000, 999999, 999998, ...
```

The method:

```java
public void writeArrayToFile(int[] arr, String filename)
```

writes the sorted output into the `outputs` folder.

---

### 3. TimSort.java

`TimSort.java` implements Tim Sort manually.

Tim Sort is a hybrid sorting algorithm. It combines the strengths of:

- Insertion Sort
- Merge Sort

In this implementation, Tim Sort uses:

```java
private static final int MIN_RUN = 32;
```

The algorithm works in two main stages:

1. It divides the array into small parts called runs.
2. It sorts each run using insertion sort.
3. It merges the sorted runs iteratively.

The method:

```java
public static void sort(int[] arr)
```

starts the Tim Sort process.

The method:

```java
private static void insertionSort(int[] arr, int left, int right)
```

sorts small runs.

The method:

```java
private static void merge(int[] arr, int l, int m, int r)
```

merges two sorted parts of the array.

Tim Sort is especially effective on partially ordered or already ordered data because it benefits from existing order in the input.

---

### 4. QuickSort.java

`QuickSort.java` implements Quick Sort with five different pivot selection strategies.

Quick Sort is a divide-and-conquer algorithm. It selects a pivot, partitions the array around the pivot, and recursively sorts the left and right parts.

The enum below defines the supported pivot strategies:

```java
public enum PivotType {
    FIRST, LAST, MIDDLE, RANDOM, MEDIAN
}
```

The method:

```java
public static void sort(int[] arr, PivotType pivotType)
```

starts Quick Sort with the selected pivot strategy.

The method:

```java
private static int choosePivotIndex(int[] arr, int low, int high, PivotType pivotType)
```

selects the pivot index according to the chosen pivot type.

The pivot strategies are:

| Pivot Type | Description |
|---|---|
| FIRST | Uses the first element as pivot |
| LAST | Uses the last element as pivot |
| MIDDLE | Uses the middle element as pivot |
| RANDOM | Uses a random element as pivot |
| MEDIAN | Uses median-of-three selection |

For the median strategy, the program compares:

- First element
- Middle element
- Last element

Then it selects the median value among these three as the pivot.

The implementation also reduces recursion depth by recursively sorting the smaller subarray first and continuing with the larger subarray in a loop. This improves stack usage and reduces the risk of stack overflow on large inputs.

---

## Input Files

The project uses two input files:

| File Name | Description |
|---|---|
| `random.txt` | Contains random integer values line by line |
| `semi_ordered.txt` | Contains semi-ordered integer values line by line |

These files are read from the `src` folder according to the implementation:

```java
src/random.txt
src/semi_ordered.txt
```

The increasing and decreasing arrays are not read from files. They are generated directly in the code.

---

## Input Types

The experiment uses four input types.

### 1. Random Data

Random integers are read from:

```text
src/random.txt
```

This dataset is used to test average-case sorting behavior.

---

### 2. Semi-Ordered Data

Semi-ordered integers are read from:

```text
src/semi_ordered.txt
```

This dataset is useful for observing whether the algorithm benefits from partial order.

---

### 3. Increasing Data

Increasing data is generated directly in the code.

Example:

```text
1, 2, 3, 4, 5, ...
```

This dataset tests algorithm behavior on already sorted input.

---

### 4. Decreasing Data

Decreasing data is generated directly in the code.

Example:

```text
1000000, 999999, 999998, ...
```

This dataset tests algorithm behavior on reverse-sorted input.

---

## Output Files

The program creates an `outputs` folder automatically and writes sorted arrays into this folder.

Example output files include:

```text
timsort_random_out.txt
quicksort_first_random_out.txt
quicksort_last_random_out.txt
quicksort_middle_random_out.txt
quicksort_random_random_out.txt
quicksort_median_random_out.txt
```

The same naming style is also used for:

- semi-ordered data
- increasing data
- decreasing data

For example:

```text
timsort_semi_ordered_out.txt
quicksort_middle_increasing_out.txt
quicksort_median_decreasing_out.txt
```

---

## Performance Results

According to the assignment report, the measured execution times are:

| Algorithm | Random | Semi-Ordered | Increasing | Decreasing |
|---|---:|---:|---:|---:|
| Tim Sort | 87 ms | 47 ms | 13 ms | 26 ms |
| Quick Sort First Element | 66 ms | 54 ms | 78124 ms | 114958 ms |
| Quick Sort Last Element | 59 ms | 46 ms | 129359 ms | 104807 ms |
| Quick Sort Middle Element | 58 ms | 46 ms | 7 ms | 12 ms |
| Quick Sort Random Element | 63 ms | 49 ms | 20 ms | 25 ms |
| Quick Sort Median Element | 61 ms | 46 ms | 8 ms | 25 ms |

---

## Result Analysis

The results show that Quick Sort performance strongly depends on pivot selection.

### Tim Sort Result

Tim Sort produced stable and consistently good results for all input types.

Its best result was:

```text
13 ms on increasing-order data
```

This is expected because Tim Sort is designed to benefit from existing order in the input data.

---

### Quick Sort with First Element Pivot

Quick Sort with first element pivot performed reasonably on random and semi-ordered data.

However, it performed very poorly on increasing and decreasing datasets:

```text
Increasing: 78124 ms
Decreasing: 114958 ms
```

This happens because choosing the first element as pivot on already sorted or reverse-sorted input creates highly unbalanced partitions.

In this case, Quick Sort approaches its worst-case time complexity:

```text
O(n²)
```

---

### Quick Sort with Last Element Pivot

Quick Sort with last element pivot also performed poorly on ordered inputs:

```text
Increasing: 129359 ms
Decreasing: 104807 ms
```

This is also caused by unbalanced partitioning.

When the input is already sorted, the last element is usually one of the worst possible pivot choices.

---

### Quick Sort with Middle Element Pivot

Quick Sort with middle element pivot gave the best overall performance in the experiment.

Its results on ordered data were especially strong:

```text
Increasing: 7 ms
Decreasing: 12 ms
```

This pivot strategy avoids the extreme unbalanced partitioning problem seen in first and last pivot strategies.

---

### Quick Sort with Random Element Pivot

Quick Sort with random pivot produced balanced and safe results.

It was not always the fastest, but it avoided the extreme worst-case behavior of first and last pivot strategies.

Random pivot is useful because it reduces the probability of repeatedly choosing bad pivots.

---

### Quick Sort with Median Element Pivot

Quick Sort with median-of-three pivot also performed very well.

Its results were:

```text
Random: 61 ms
Semi-Ordered: 46 ms
Increasing: 8 ms
Decreasing: 25 ms
```

Median-of-three is useful because it chooses a more representative pivot by comparing the first, middle, and last values.

---

## Best and Worst Algorithms

According to the report:

| Category | Result |
|---|---|
| Best algorithm for random data | Quick Sort with middle element pivot |
| Best algorithm for semi-ordered data | Quick Sort middle / last / median and Tim Sort gave similar results |
| Best algorithm for increasing data | Quick Sort with middle element pivot |
| Best algorithm for decreasing data | Quick Sort with middle element pivot |
| Most stable algorithm overall | Tim Sort |
| Worst algorithms on ordered inputs | Quick Sort first element pivot and Quick Sort last element pivot |

---

## Algorithm Explanations

### Tim Sort

Tim Sort is a hybrid sorting algorithm based on insertion sort and merge sort.

It is effective because insertion sort is fast on small or nearly sorted arrays, while merge sort provides reliable merging performance.

In this implementation:

1. The array is divided into runs of size 32.
2. Each run is sorted using insertion sort.
3. The sorted runs are merged until the entire array is sorted.

General time complexity:

| Case | Complexity |
|---|---|
| Best case | O(n) |
| Average case | O(n log n) |
| Worst case | O(n log n) |

Tim Sort is generally stable and performs well on real-world data that contains partial order.

---

### Quick Sort

Quick Sort is a divide-and-conquer sorting algorithm.

The general steps are:

1. Select a pivot.
2. Move smaller elements to the left of the pivot.
3. Move larger elements to the right of the pivot.
4. Recursively sort both sides.

General time complexity:

| Case | Complexity |
|---|---|
| Best case | O(n log n) |
| Average case | O(n log n) |
| Worst case | O(n²) |

Quick Sort can be very fast, but its performance depends heavily on pivot selection.

A good pivot creates balanced partitions. A bad pivot creates unbalanced partitions.

---

## Why Pivot Selection Matters

Pivot selection is the most important factor in Quick Sort performance.

If the pivot divides the array into two balanced parts, Quick Sort works efficiently.

Example of balanced partition:

```text
Left side: 500,000 elements
Right side: 500,000 elements
```

This produces approximately:

```text
O(n log n)
```

performance.

If the pivot creates extremely unbalanced partitions, performance becomes much worse.

Example of unbalanced partition:

```text
Left side: 0 elements
Right side: 999,999 elements
```

This produces approximately:

```text
O(n²)
```

performance.

This is exactly why first and last pivot strategies performed very badly on already sorted and reverse-sorted datasets.

---

## How to Run the Project

### Requirements

To run the project, you need:

- Java installed
- Eclipse IDE or another Java IDE
- `random.txt`
- `semi_ordered.txt`

---

### Running with Eclipse

1. Open Eclipse.
2. Click `File`.
3. Select `Import`.
4. Choose `Existing Projects into Workspace`.
5. Select the project folder.
6. Click `Finish`.
7. Place `random.txt` and `semi_ordered.txt` inside the `src` folder.
8. Open `Test.java`.
9. Run the project.

---

### Running from Terminal

If the input files are placed correctly, the project can be compiled and run from terminal using commands similar to:

```bash
javac src/Assignment/*.java
java -cp src Assignment.Test
```

Make sure the input files are located as expected:

```text
src/random.txt
src/semi_ordered.txt
```

---

## Notes About File Placement

The implementation expects the input files at these paths:

```java
private String randomfile = "src/random.txt";
private String semifile = "src/semi_ordered.txt";
```

Because of this, the files should be placed inside the `src` folder.

If the files are missing, the program prints an error message such as:

```text
Hata: Dosya bulunamadı - src/random.txt
```

---

## Sample Console Output

A simplified version of the console output is:

```text
Algorithm            Random          Semi-Ordered    Increasing      Decreasing
----------------------------------------------------------------------------------
timsort              87 ms           47 ms           13 ms           26 ms
quicksort_first      66 ms           54 ms           78124 ms        114958 ms
quicksort_last       59 ms           46 ms           129359 ms       104807 ms
quicksort_middle     58 ms           46 ms           7 ms            12 ms
quicksort_random     63 ms           49 ms           20 ms           25 ms
quicksort_median     61 ms           46 ms           8 ms            25 ms
```

At the end, the program prints:

```text
Tüm işlemler tamamlandı. Çıktılar 'outputs' klasörüne kaydedildi.
```

---

## Strengths of the Project

- Compares two important sorting algorithms
- Tests algorithms on four different input types
- Implements five Quick Sort pivot strategies
- Measures only actual sorting time
- Uses array copies for fair comparison
- Automatically writes output files
- Shows how input order affects sorting performance
- Demonstrates the importance of pivot choice in Quick Sort

---

## Possible Future Improvements

The project can be improved with the following features:

- Add runtime averaging over multiple runs
- Add memory usage measurement
- Add graphs for performance comparison
- Add more dataset sizes such as 10,000, 100,000, and 5,000,000
- Add validation method to check whether output arrays are correctly sorted
- Add command-line arguments for input file paths
- Add more sorting algorithms such as Merge Sort, Heap Sort, and Radix Sort
- Export the performance matrix as CSV
- Add unit tests for Tim Sort and Quick Sort
- Improve file path flexibility

---

## Conclusion

This project compares Tim Sort and Quick Sort on four different input types with 1,000,000 integers.

The experiment shows that Tim Sort is stable and reliable across all datasets. It performs especially well when the data is already partially or fully ordered.

Quick Sort can be faster than Tim Sort, but its performance depends strongly on pivot selection. First and last pivot strategies perform very poorly on increasing and decreasing datasets because they create unbalanced partitions. Middle, random, and median pivot strategies avoid this problem and produce much better results.

Overall, the project demonstrates an important lesson in algorithm analysis: the theoretical complexity of an algorithm is important, but implementation choices and input structure can significantly affect real execution time.

---

