package aimat;

import aimat.model.TreeNode;

/**
 * @author bo.luo
 * @date 2021/1/28 11:17
 */
public class Solution026 {

    public boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (isSubStructure(A.left, B) || isSubStructure(A.right, B) || recv(A, B));
    }

    private boolean recv(TreeNode a, TreeNode b) {
        if (b == null) {
            return true;
        }
        if (a == null || a.val != b.val) {
            return false;
        }
        return recv(a.right, b.right) && recv(a.left, b.left);
    }
}
