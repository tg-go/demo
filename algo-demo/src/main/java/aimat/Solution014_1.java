package aimat;

/**
 * @author bo.luo
 * @date 2021/1/19 17:16
 */
public class Solution014_1 {

    /**
     * 【解题思路1】动态规划
     * 对于的正整数 n，当 n≥2 时，可以拆分成至少两个正整数的和。令 k 是拆分出的第一个正整数，则剩下的部分是 n−k，n−k 可以不继续拆分，或者继续拆分成至少两个正整数的和。
     * 由于每个正整数对应的最大乘积取决于比它小的正整数对应的最大乘积，因此可以使用动态规划求解。
     *
     * dp数组的含义： dp[i] 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积。
     * 边界条件： 0 不是正整数，1 是最小的正整数，0 和 1 都不能拆分，因此 dp[0]=dp[1]=0。
     * 状态转移方程：
     * 当 i≥2 时，假设对正整数 i 拆分出的第一个正整数是 j（1≤j<i），则有以下两种方案：
     *
     * 将 i 拆分成 j 和 i−j 的和，且 i−j 不再拆分成多个正整数，此时的乘积是 j×(i−j)；
     * 将 i 拆分成 j 和 i−j 的和，且 i−j 继续拆分成多个正整数，此时的乘积是 j×dp[i−j]。
     * 因此，当 j 固定时，有 dp[i]=max(j×(i−j),j×dp[i−j])。由于 j 的取值范围是 1 到 i−1，需要遍历所有的 j 得到 dp[i] 的最大值，因此可以得到状态转移方程如下：
     *
     * dp[i]= \max_{1≤j<i} {(j×(i−j),j×dp[i−j])}
     * dp[i]=
     * 1≤j<i
     * max
     * ​
     *  (j×(i−j),j×dp[i−j])
     *
     * 最终得到 dp[n] 的值即为将正整数 n 拆分成至少两个正整数的和之后，这些正整数的最大乘积。
     *
     * @param n
     * @return
     */


    public static int cuttingRope(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    public static int cut2(int n){
        int[] dp = new int[n+1];
        for (int i = 2;i <= n;i++){
            for (int j = 1; j < i;j++){
                dp[i] = Math.max(dp[i],Math.max(j*(i-j),j*dp[i-j]));
            }
        }
        return dp[n];
    }
}
