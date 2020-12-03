package leetcode;

/**
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * <p>
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * <p>
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions/xmlwi1/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution003 {


    /**
     * 这道题的关键是如何对一个生序的数组进行划分，左下和右上都可以
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length - 1;
        int column = 0;

        int columnMax = matrix[0].length;
        while (row >= 0 && column < columnMax) {
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                row--;
            } else {
                column++;
            }
        }
        return false;
    }
}
