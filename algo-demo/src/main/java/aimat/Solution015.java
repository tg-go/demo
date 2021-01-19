package aimat;

public class Solution015 {

    public static int hammingWeight(int n) {

        int result = 0;
        while (n != 0) {
            result += n & 1;
            n >>>= 1;
        }
        return result;
    }
}
