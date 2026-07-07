package Assignment;

import java.util.Random;

public class QuickSort {
    public enum PivotType {
        FIRST, LAST, MIDDLE, RANDOM, MEDIAN
    }

    private static final Random random = new Random();

    public static void sort(int[] arr, PivotType pivotType) {
        quickSort(arr, 0, arr.length - 1, pivotType);
    }

    private static void quickSort(int[] arr, int low, int high, PivotType pivotType) {
        while (low < high) {
            int pivotIndex = partition(arr, low, high, pivotType);

            if (pivotIndex - low < high - pivotIndex) {
                quickSort(arr, low, pivotIndex - 1, pivotType);
                low = pivotIndex + 1;
            } else {
                quickSort(arr, pivotIndex + 1, high, pivotType);
                high = pivotIndex - 1;
            }
        }
    }

    private static int partition(int[] arr, int low, int high, PivotType pivotType) {
        int pivotIndex = choosePivotIndex(arr, low, high, pivotType);
        swap(arr, pivotIndex, high);

        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    private static int choosePivotIndex(int[] arr, int low, int high, PivotType pivotType) {
        switch (pivotType) {
            case FIRST:
                return low;
            case LAST:
                return high;
            case MIDDLE:
                return low + (high - low) / 2;
            case RANDOM:
                return low + random.nextInt(high - low + 1);
            case MEDIAN:
                return medianOfThreeIndex(arr, low, high);
            default:
                return high;
        }
    }

    private static int medianOfThreeIndex(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;

        int a = arr[low];
        int b = arr[mid];
        int c = arr[high];

        if ((a >= b && a <= c) || (a <= b && a >= c)) {
            return low;
        } else if ((b >= a && b <= c) || (b <= a && b >= c)) {
            return mid;
        } else {
            return high;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
}