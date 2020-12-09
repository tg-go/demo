package leetcode;

/**
 *
 * @author bo.luo
 * @date 2020/12/4 10:13
 */
public class Solution006 {

    public static boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            while (start < end && !Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            }
            while (start < end && !Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            }
            if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s1 = "A man, a plan, a canal: Panama";

        String s2 = "race a car";

        System.out.println(isPalindrome(s1));

        System.out.println(isPalindrome(s2));
    }
}
