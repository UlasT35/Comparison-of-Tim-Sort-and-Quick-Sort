package Assignment;
public class TimSort {
    private static final int MIN_RUN = 32;
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; i += MIN_RUN) {
            insertionSort(arr, i, Math.min((i + MIN_RUN - 1), (n - 1)));
        }
        for (int size = MIN_RUN; size < n; size = 2 * size) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if (mid < right) {
                    merge(arr, left, mid, right);
                }
            }
        }
    }
    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j] > temp) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }
    }
    private static void merge(int[] arr, int l, int m, int r) {
        int len1 = m - l + 1; //length
        int len2 = r - m;
        int[] left = new int[len1]; 
        int[] right = new int[len2];

        System.arraycopy(arr, l, left, 0, len1);
        for (int i = 0; i < len2; i++) {
            right[i] = arr[m + 1 + i];
        }

        int i = 0, j = 0, k = l;
        while (i < len1 && j < len2) {
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        while (i < len1) {
            arr[k++] = left[i++];
        }
        while (j < len2) {
            arr[k++] = right[j++];
        }
    }
}