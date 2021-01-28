package aimat;

import aimat.model.TreeNode;

/**
 * @author bo.luo
 * @date 2021/1/28 11:32
 */
public class Solution027 {

    public TreeNode mirrorTree(TreeNode root) {
        mirror(root);
        return root;
    }

    public void mirror(TreeNode root){
        if(root == null){
            return;
        }
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        mirror(root.left);
        mirror(root.right);
    }
}
