package aimat;


import aimat.model.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 例如，给出
 * <p>
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *
 * @author bo.luo
 * @date 2021/1/19 14:03
 */
public class Solution007 {

    public static void main(String[] args) {
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};

    }


    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < inorder.length; i++) {
            indexMap.put(inorder[i], i);
        }
        TreeNode root = buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, indexMap);
        return root;

    }

    public static TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd, Map indexMap) {
        if (preStart > preEnd) {
            return null;
        } else if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        } else {
            int rootIndex = (int) indexMap.get(preorder[preStart]);
            int leftNodes = rootIndex - inStart;
            int rightNodes = inEnd - rootIndex;
            TreeNode leftNode = buildTree(preorder, preStart + 1, preStart + leftNodes, inorder, inStart, rootIndex - 1, indexMap);
            TreeNode rightNode = buildTree(preorder, preEnd - rightNodes + 1, preEnd, inorder, rootIndex + 1, inEnd, indexMap);
            TreeNode root = new TreeNode(preorder[preStart]);
            root.left = leftNode;
            root.right = rightNode;
            return root;
        }
    }

}
