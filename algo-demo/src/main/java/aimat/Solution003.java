package aimat;

import java.util.HashSet;
import java.util.Set;

/**
 * @author bo.luo
 * @date 2021/1/19 10:10
 */
public class Solution003 {

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(nums));
    }

    public static int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int result = 0;
        for (int tmp : nums) {
            if (set.contains(tmp)) {
                result = tmp;
                break;
            }
            set.add(tmp);
        }
        return result;
    }
}
