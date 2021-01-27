package aimat;

import aimat.model.ListNode;

/**
 * @author bo.luo
 * @date 2021/1/27 14:57
 */
public class Solution022 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode pre = head;
        ListNode cur = head;
        while (k > 0) {
            pre = pre.next;
            k--;
        }
        while (pre != null) {
            pre = pre.next;
            cur = cur.next;
        }
        return cur;
    }
}
