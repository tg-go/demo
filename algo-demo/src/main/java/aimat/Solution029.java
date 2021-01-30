package aimat;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author bo.luo
 * @date 2021/1/28 11:45
 */
public class Solution029 {

    public int[] spiralOrder(int[][] matrix) {
        int length = (matrix.length + 1) / 2;
        int row = matrix.length;
        int column = matrix[0].length;
        int[] result = new int[row * column];
        int index = 0;
        for (int i = 0; i < length; i++) {
            for (int start = i; start < column - i; start++) {
                result[index++] = matrix[i][start];
            }
            for (int start = i; start < row - i; start++) {
                result[index++] = matrix[start][column - i - 1];
            }
            for (int start = column - i - 1; start >= 0; start--) {
                result[index++] = matrix[row - i - 1][start];
            }
            for (int start = row - i - 1; start >= 0; start--) {
                result[index++] = matrix[start][i];
            }
        }
        return result;
    }


    @Test
    public void test1() {
        String str = "a|b|c|||";
        String[] r1 = str.split("\\|", 6);
        System.out.println(Arrays.toString(r1));
        String[] r2 = str.split("\\|",-1);
        System.out.println(Arrays.toString(r2));
        String[] r3 = str.split("\\|");
        System.out.println(Arrays.toString(r3));
    }
}
