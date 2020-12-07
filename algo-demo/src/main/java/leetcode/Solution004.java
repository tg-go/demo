package leetcode;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 *  
 * <p>
 * 说明：
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * <p>
 *
 * @author bo.luo
 * @date 2020/12/4 10:03
 */
public class Solution004 {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[index] = nums1[i];
                index--;
                i--;
            } else {
                nums1[index] = nums2[j];
                index--;
                j--;
            }
        }
        while (i >= 0) {
            nums1[index] = nums1[i];
            i--;
            index--;
        }
        while (j >= 0) {
            nums1[index] = nums2[j];
            j--;
            index--;
        }
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
