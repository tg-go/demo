package aimat;

/**
 * @author bo.luo
 * @date 2021/1/19 10:46
 */
public class Solution005 {


    public static void main(String[] args) {
        String str = "We are happy.";
        System.out.println(replaceSpace(str));
    }

    public static String replaceSpace(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.replaceAll(" ", "%20");
    }
}
