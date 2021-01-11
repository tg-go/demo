package sort;

import java.util.Arrays;

/**
 * 直接插入排序（一种插入排序）
 *
 * @author bo.luo
 * @date 2021/1/11 20:33
 */
public class InsertSort {

    private static int[] list = {5, 3, 7, 8, 1};

    public static void main(String[] args) {
        System.out.println("排序前：" + Arrays.toString(list));
        insertSort(list);
        System.out.println("排序后：" + Arrays.toString(list));
    }

    public static void insertSort(int[] list) {
        for (int i = 1; i < list.length; i++) {
            int j = 0;
            int tmp = list[i];
            for (j = i-1; j >= 0 && tmp < list[j]; j--) {
                list[j+1] = list[j];
            }
            list[j+1] = tmp;
        }
    }
}
