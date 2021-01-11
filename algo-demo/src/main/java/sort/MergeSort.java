package sort;

import java.util.Arrays;

public class MergeSort {

    private static int[] list = {5, 3, 7, 8, 1};

    public static void main(String[] args) {
        System.out.println("排序前：" + Arrays.toString(list));
        mergeSort(list);
        System.out.println("排序后：" + Arrays.toString(list));
    }

    public static void mergeSort(int[] list) {

        for (int gap = 1; gap < list.length; gap = gap * 2) {
            mergePass(list, gap, list.length);
        }
    }

    public static void mergePass(int[] array, int gap, int length) {
        int i = 0;
        for (i = 0; i + 2 * gap - 1 < length; i = i + 2 * gap) {
            merge(array, i, i + gap - 1, i + 2 * gap - 1);
        }

        if (i + gap - 1 < length) {
            merge(array, i, i + gap - 1, length - 1);
        }
    }

    public static void merge(int[] array, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = 0;
        int[] array2 = new int[high - low + 1];

        while (i <= mid && j <= high) {
            if (array[i] < array[j]) {
                array2[k] = array[i];
                k++;
                i++;
            } else {
                array2[k] = array[j];
                k++;
                j++;
            }
        }

        while (i <= mid) {
            array2[k] = array[i];
            i++;
            k++;
        }

        while (j <= high) {
            array2[k] = array[j];
            j++;
            k++;
        }

        for (k = 0, i = low; i <= high; i++, k++) {
            array[i] = array2[k];
        }
    }
}
