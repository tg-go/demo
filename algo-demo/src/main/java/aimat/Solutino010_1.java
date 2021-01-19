package aimat;

/**
 * @author bo.luo
 * @date 2021/1/19 15:39
 */
public class Solutino010_1 {

    public static void main(String[] args) {
        System.out.println(fib(0));
        System.out.println(fib(1));
        System.out.println(fib(2));
        System.out.println(fib(5));
    }

    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        } else {
            int a = 0;
            int b = 1;
            int index = 2;
            while (index <= n) {
                int tmp = b;
                b = (a + b) % 1000000007;
                a = tmp;
                index++;
            }
            return b;
        }
    }
}
