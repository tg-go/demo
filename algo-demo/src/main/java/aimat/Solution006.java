package aimat;

import aimat.model.ListNode;

import java.util.*;

/**
 * @author bo.luo
 * @date 2021/1/19 10:49
 */
public class Solution006 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(2);

        node1.next = node2;
        node2.next = node3;

        int[] result = reversePrint1(node1);
        System.out.println(Arrays.toString(result));
    }


    // 这种方式不好，需要重复遍历，特别是push操作（不应该啊，直接从尾部插入）
    public static int[] reversePrint(ListNode head) {
        LinkedList list = new LinkedList();
        ListNode ptr = head;
        while (ptr != null) {
            list.push(ptr.val);
            ptr = ptr.next;
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = (int) list.get(i);
        }
        return result;
    }

    // 直接调整指针，并且记录大小，然后遍历即可
    public static int[] reversePrint1(ListNode head) {
        if (head == null) {
            return new int[]{};
        }
        int index = 0;
        int[] result = new int[1000];
        while (head != null) {
            result[index++] = head.val;
            head = head.next;
        }

        int[] result2 = new int[index];
        for (int i = 0; i < index; i++) {
            result2[i] = result[index - i - 1];
        }
        return result2;
    }
}
