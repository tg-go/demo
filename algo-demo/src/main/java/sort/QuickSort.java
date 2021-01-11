package sort;

import java.util.Arrays;

/**
 * 快速排序（也是一种交换排序）
 * <p>
 * 基本思想是将一段待排序列分隔成两段，一段大，一段小。然后在分别排序
 *
 * @author bo.luo
 * @date 2021/1/11 20:10
 */
public class QuickSort {
    private static int[] list = {5, 3, 7, 8, 1};

    public static void main(String[] args) {
        System.out.println("排序前：" + Arrays.toString(list));
        quickSort(list);
        System.out.println("排序后：" + Arrays.toString(list));
    }

    public static void quickSort(int[] list) {
        quickSort2(list, 0, list.length - 1);
    }

    public static void quickSort(int[] list, int left, int right) {
        // 终止条件
        if (left >= right) {
            return;
        }

        // 找到中间的位置
        int guard = list[left];
        int start = left;
        int end = right;
        while (start < end) {
            while (start < end && list[end] >= guard) {
                end--;
            }
            list[start] = list[end];
            while (start < end && list[start] <= guard) {
                start++;
            }
            list[end] = list[start];
        }
        list[start] = guard;

        quickSort(list, left, start - 1);
        quickSort(list, start + 1, end);
    }

    public static int division(int[] list, int left, int right) {
        int base = list[left];
        while (left < right) {
            while (left < right && list[right] >= base) {
                right--;
            }
            list[left] = list[right];
            while (left < right && list[left] <= base) {
                left++;
            }
            list[right] = list[left];
        }

        list[left] = base;
        return left;
    }

    public static void quickSort2(int[] list, int left, int right) {
        if (left < right) {
            int base = division(list, left, right);
            quickSort(list,left,base-1);
            quickSort(list,base+1,right);
        }
    }
}
