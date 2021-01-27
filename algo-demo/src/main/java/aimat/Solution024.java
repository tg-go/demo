package aimat;

import aimat.model.ListNode;

/**
 * @author bo.luo
 * @date 2021/1/27 15:06
 */
public class Solution024 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head.next;
        ListNode cur = head;
        cur.next = null;
        while (pre != null) {
            ListNode tmp = pre.next;
            pre.next = cur;
            cur = pre;
            pre = tmp;
        }
        return cur;
    }

    public ListNode rev(ListNode head){
        ListNode result = new ListNode(-1);
        ListNode cur = head;
        while(cur != null){
            ListNode tmp = cur.next;
            cur.next = result.next;
            result.next = cur;
            cur = tmp;
        }
        return result.next;
    }
}
