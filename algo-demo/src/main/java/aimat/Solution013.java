package aimat;

/**
 * @author bo.luo
 * @date 2021/1/19 17:11
 */
public class Solution013 {


    public int movingCount(int m, int n, int k) {
        int[] dp = new int[n+1];
        for(int i = 2;i<=n;i++){
            for(int j = 1; j < i;j++){
                dp[i] = Math.max(dp[i],Math.max(j*(i-j),j*dp[i-j]));
            }
        }
        return dp[n];
    }
}
