package aimat;

/**
 * @author bo.luo
 * @date 2021/1/27 14:33
 */
public class Solution016 {

    public double myPow(double x, int n) {
        int tmp = n > 0 ? n : -n;
        double result = 1;
        for (int i = 1; i <= tmp; i++) {
            result = result * x;
        }
        if (n < 0) {
            result = 1 / result;
        }
        return result;
    }
}
