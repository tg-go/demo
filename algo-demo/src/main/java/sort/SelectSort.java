package sort;

import java.util.Arrays;

/**
 * 直接选择排序，每次选择一个最小的数据进行排序
 *
 * @author bo.luo
 * @date 2021/1/11 20:46
 */
public class SelectSort {

    private static int[] list = {5, 3, 7, 8, 1};

    public static void main(String[] args) {
        System.out.println("排序前：" + Arrays.toString(list));
        selectSort(list);
        System.out.println("排序后：" + Arrays.toString(list));
    }

    public static void selectSort(int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            int minIndex = i;
            int tmp = list[i];
            for (int j = i; j < list.length; j++) {
                if (list[j] < tmp) {
                    tmp = list[j];
                    minIndex = j;
                }
            }
            tmp = list[i];
            list[i] = list[minIndex];
            list[minIndex] = tmp;
        }
    }
}
