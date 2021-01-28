package aimat;

import aimat.model.ListNode;

/**
 * @author bo.luo
 * @date 2021/1/27 17:30
 */
public class Solution025 {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(1);
        ListNode tmp = root;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                root.next = l1;
                l1 = l1.next;
                root = root.next;
            } else {
                root.next = l2;
                l2 = l2.next;
                root = root.next;
            }
        }
        if (l1 != null) {
            root.next = l1;
        }
        if (l2 != null) {
            root.next = l2;
        }

        return tmp.next;

    }
}
