package aimat;

/**
 * @author bo.luo
 * @date 2021/1/19 15:44
 */
public class Solution011 {

    private static int[] a = {3, 4, 5, 1, 2};
    private static int[] b = {2, 2, 2, 0, 1};

    public static void main(String[] args) {
        System.out.println(minArray(a));
        System.out.println(minArray(b));
    }

    public static int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low <= high) {
            int middle = (high - low) / 2 + low;
            if (numbers[middle] > numbers[high]) {
                low = middle + 1;
            } else if (numbers[middle] < numbers[high]) {
                high = middle;
            } else {
                high = high - 1;
            }
        }
        return numbers[low];
    }
}
