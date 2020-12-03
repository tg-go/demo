package leetcode;

import java.util.Arrays;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * <p>
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xm77tm/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution002 {

    /**
     * 先排序，然后返回中间的数字。
     * 我觉得这个想法不错
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 分治法
     *
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        return findMiddle(nums, 0, nums.length - 1);
    }

    public int findMiddle(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int middle = (left + right) / 2;
        int leftMiddle = findMiddle(nums, left, middle);
        int rightMiddle = findMiddle(nums, middle + 1, right);
        if (leftMiddle == rightMiddle) {
            return leftMiddle;
        } else {
            int leftCount = count(nums, leftMiddle, left, middle);
            int rightCount = count(nums, rightMiddle, middle + 1, right);
            return leftCount > rightCount ? leftMiddle : rightMiddle;

        }
    }

    public int count(int[] nums, int num, int left, int right) {
        int count = 0;
        for (int i = left; i <= right; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }
}
