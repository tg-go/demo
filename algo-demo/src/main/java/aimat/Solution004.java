package aimat;

/**
 * @author bo.luo
 * @date 2021/1/19 10:14
 */
public class Solution004 {

    private static final int[][] nums = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
    };

    public static void main(String[] args) {
        int target = 100;
        System.out.println(findNumberIn2DArray(nums, target));
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        // 需要注意极端情况
        if (matrix.length <= 0 || matrix[0].length <= 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        int i = row - 1;
        int j = 0;

        while (i >= 0 && i < row && j >= 0 && j < col) {
            if (matrix[i][j] > target) {
                i--;
            } else if (matrix[i][j] < target) {
                j++;
            } else {
                return true;
            }
        }
        return false;
    }
}
