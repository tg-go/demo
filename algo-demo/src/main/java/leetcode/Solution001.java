package leetcode;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * <p>
 * 说明：
 * <p>
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,2,1]
 * 输出: 1
 * 示例 2:
 * <p>
 * 输入: [4,1,2,1,2]
 * 输出: 4
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xm0u83/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * <p>
 * <p>
 * <p>
 * 这道题的关键是掌握位运算，相同的数字进行位运算时，得到的值为0，所以才能找到单独那个数字
 *
 * @author bo.luo
 * @date 2020/12/2 12:42
 */
public class Solution001 {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int tmp : nums) {
            result ^= tmp;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution001 key = new Solution001();
        int[] nums = {1, 2, 3, 3, 4, 5, 2, 1, 4};
        System.out.println(key.singleNumber(nums));
    }
}
