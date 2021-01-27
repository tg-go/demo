package aimat;

import aimat.model.ListNode;

/**
 * @author bo.luo
 * @date 2021/1/27 14:27
 */
public class Solution018 {

    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        if (head.val == val) {
            return head.next;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
                break;
            }
            pre = pre.next;
            cur = cur.next;
        }
        return head;
    }
}
