package tree;

/**
 * 226. 翻转二叉树
 * 链接：https://leetcode-cn.com/problems/invert-binary-tree/
 */
public class Question_Easy_226 {
    class Solution {
        /**
         * 递归函数的定义：翻转左右子树
         * @param root
         * @return
         */
        public TreeNode invertTree(TreeNode root) {
            if(root == null){
                return root;
            }
            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
            invertTree(root.left);
            invertTree(root.right);
            return root;

        }
    }
}
