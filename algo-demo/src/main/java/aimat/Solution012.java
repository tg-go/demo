package aimat;

/**
 * @author bo.luo
 * @date 2021/1/19 16:01
 */
public class Solution012 {

    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (dfs(board, chars, row, col, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dfs(char[][] board, char[] chars, int row, int col, int index) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != chars[index]) {
            return false;
        }
        if (index == chars.length - 1) {
            return true;
        }
        char tmp = board[row][col];
        board[row][col] = '/';
        boolean result = dfs(board, chars, row - 1, col, index+1)
                || dfs(board, chars, row + 1, col, index+1)
                || dfs(board, chars, row, col - 1, index+1)
                || dfs(board, chars, row, col + 1, index+1);
        board[row][col] = tmp;
        return result;

    }

    /*public boolean exist1(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[0].length; column++) {
                if (dfs1(board, chars, row, column, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    *//**
     * @param board   矩阵
     * @param str    待匹配字符串
     * @param row    行
     * @param column 列
     * @param k      长度，是作为匹配成功的终止条件,同时也作为当前匹配字符的索引
     * @return
     *//*
    private boolean dfs1(char[][] board, char[] str, int row, int column, int k) {
        // 不匹配的情况
        if (row < 0 || row >= board.length || column < 0 || column >= board[0].length ||  board[row][column] != str[k]) {
            return false;
        }
        // 终止条件,已经到达最后一个字符了，只需要匹配相等即可
        if (k == str.length - 1) {
            return true;
        }
        // 如果都不满足，则需要判断下一部分的字符串是否满足
        char tmp = board[row][column];
        board[row][column] = '/';
        boolean res =
                dfs1(board, str, row - 1, column, k + 1) || dfs1(board, str, row + 1, column, k + 1) ||
                        dfs1(board, str, row, column - 1, k + 1)
                        || dfs1(board, str, row, column + 1, k + 1);
        board[row][column] = tmp;
        return res;
    }*/
}
