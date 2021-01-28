package aimat;

import aimat.model.TreeNode;

/**
 * @author bo.luo
 * @date 2021/1/28 11:35
 */
public class Solution028 {

    public boolean isSymmetric(TreeNode root) {
        return root == null ? true : cur(root.left, root.right);
    }

    private boolean cur(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null || left.val != right.val) {
            return false;
        }
        return cur(left.left, right.right) && cur(left.right, right.left);
    }
}
