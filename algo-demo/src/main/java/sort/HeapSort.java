package sort;

import java.util.Arrays;

public class HeapSort {

    private static int[] list = {5, 3, 7, 8, 1};

    public static void main(String[] args) {
        System.out.println("排序前：" + Arrays.toString(list));
        heapSort(list);
        System.out.println("排序后：" + Arrays.toString(list));
    }

    public static void heapAdjust(int[] array, int parent, int length) {
        // 从父节点开始，依次往后慢慢调整
        int temp = array[parent];
        int child = 2 * parent + 1;
        while (child < length) {
            // 找到子节点中的较大值
            if (child + 1 < length && array[child] < array[child + 1]) {
                child++;
            }

            // 如果父节点已经是最大值，则直接返回
            if (temp >= array[child]) {
                break;
            }

            // 否则进行，父节点和子节点的较大值进行交换
            array[parent] = array[child];

            // 处理后续的子节点
            parent = child;
            child = 2 * child + 1;
        }

        // 将原始的父节点的值放到最后的位置
        array[parent] = temp;
    }


    public static void heapSort(int[] list) {
        // 从最后的一个父节点开始调整堆
        for (int i = list.length / 2; i >= 0; i--) {
            heapAdjust(list, i, list.length);
        }

        for (int i = list.length - 1; i > 0; i--) {
            int temp = list[i];
            list[i] = list[0];
            list[0] = temp;

            heapAdjust(list, 0, i);
        }
    }
}
