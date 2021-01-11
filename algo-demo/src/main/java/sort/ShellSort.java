package sort;

import java.util.Arrays;

/**
 * 希尔排序（缩小增量排序，也是一种插入排序）
 * 这个排序也是比较简单的，其实就是对数据进行分组，然后每个小组使用直接插入排序
 *
 * @author bo.luo
 * @date 2021/1/11 20:39
 */
public class ShellSort {

    private static int[] list = {5, 3, 7, 8, 1};

    public static void main(String[] args) {
        System.out.println("排序前：" + Arrays.toString(list));
        shellSort(list);
        System.out.println("排序后：" + Arrays.toString(list));
    }

    public static void shellSort(int[] list) {
        int gap = list.length / 2;
        while (gap >= 1) {
            for (int i = gap; i < list.length; i++) {
                int j = 0;
                int tmp = list[i];

                for (j = i - gap; j >= 0 && tmp < list[j]; j = j - gap) {
                    list[j + gap] = list[j];
                }
                list[j + gap] = tmp;
            }
            gap = gap / 2;
        }
    }
}
