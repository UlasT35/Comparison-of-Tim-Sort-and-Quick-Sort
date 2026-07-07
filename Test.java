package Assignment;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();

        int[] randomData = fileReader.readRandomFile();
        int[] semiOrderedData = fileReader.readSemiOrderedFile();
        int[] increasingData = fileReader.generateIncreasingArray();
        int[] decreasingData = fileReader.generateDecreasingArray();

        int[][] datasets = {randomData, semiOrderedData, increasingData, decreasingData};
        String[] dataNames = {"random", "semi_ordered", "increasing", "decreasing"};

        System.out.printf("%-20s %-15s %-15s %-15s %-15s%n",
                "Algorithm", "Random", "Semi-Ordered", "Increasing", "Decreasing");
        System.out.println("----------------------------------------------------------------------------------");

        runAndMeasureTimSort(datasets, dataNames, fileReader);

        runAndMeasureQuickSort(datasets, dataNames, "quicksort_first", QuickSort.PivotType.FIRST, fileReader);
        runAndMeasureQuickSort(datasets, dataNames, "quicksort_last", QuickSort.PivotType.LAST, fileReader);
        runAndMeasureQuickSort(datasets, dataNames, "quicksort_middle", QuickSort.PivotType.MIDDLE, fileReader);
        runAndMeasureQuickSort(datasets, dataNames, "quicksort_random", QuickSort.PivotType.RANDOM, fileReader);
        runAndMeasureQuickSort(datasets, dataNames, "quicksort_median", QuickSort.PivotType.MEDIAN, fileReader);
        System.out.println();
        System.out.println("--------------------------------------------------------------------------------------");
        System.out.println("Tüm işlemler tamamlandı. Çıktılar 'outputs' klasörüne kaydedildi.");
    }

    private static void runAndMeasureTimSort(int[][] datasets, String[] dataNames, FileReader fileReader) {
        System.out.printf("%-20s", "timsort");

        for (int i = 0; i < datasets.length; i++) {
            if (datasets[i] == null) {
                System.out.printf("%-15s", "File Error");
                continue;
            }

            int[] arrCopy = Arrays.copyOf(datasets[i], datasets[i].length);

            long startTime = System.nanoTime();
            TimSort.sort(arrCopy);
            long endTime = System.nanoTime();

            long elapsedMs = (endTime - startTime) / 1_000_000;
            System.out.printf("%-15s", elapsedMs + " ms");

            fileReader.writeArrayToFile(arrCopy, "timsort_" + dataNames[i] + "_out.txt");
        }

        System.out.println();
    }

    private static void runAndMeasureQuickSort(int[][] datasets, String[] dataNames,
                                               String algName, QuickSort.PivotType pivotType,
                                               FileReader fileReader) {
        System.out.printf("%-20s", algName);

        for (int i = 0; i < datasets.length; i++) {
            if (datasets[i] == null) {
                System.out.printf("%-15s", "File Error");
                continue;
            }

            int[] arrCopy = Arrays.copyOf(datasets[i], datasets[i].length);

            try {
                long startTime = System.nanoTime();
                QuickSort.sort(arrCopy, pivotType);
                long endTime = System.nanoTime();

                long elapsedMs = (endTime - startTime) / 1_000_000;
                System.out.printf("%-15s", elapsedMs + " ms");

                fileReader.writeArrayToFile(arrCopy, algName + "_" + dataNames[i] + "_out.txt");
            } catch (StackOverflowError e) {
                System.out.printf("%-15s", "StackOverflow");
            }
        }

        System.out.println();
    }
}