package sort;

import java.util.Arrays;

/**
 * 冒泡排序(一种交换排序算法)
 * <p>
 * 需要注意的是如果在一趟排序中没有发生过交换的操作，其实我们是可以提前结束排序的。通过flag标记来实现。
 *
 * @author bo.luo
 * @date 2021/1/11 20:00
 */
public class BubbleSort {


    private static int[] list = {5, 3, 7, 8, 1};

    public static void main(String[] args) {
        System.out.println("排序前：" + Arrays.toString(list));
        bubbleSort(list);
        System.out.println("排序后：" + Arrays.toString(list));
    }

    public static void bubbleSort(int[] list) {
        int tmp = 0;
        boolean flag = false;
        for (int i = 0; i < list.length - 1; i++) {
            for (int j = list.length - 1; j > i; j--) {
                if (list[j] < list[j - 1]) {
                    tmp = list[j];
                    list[j] = list[j - 1];
                    list[j - 1] = tmp;
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }
}
