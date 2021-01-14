package tree;

/**
 * 114. 二叉树展开为链表
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 */
public class Question_Medium_114 {
    class Solution {
        public void flatten(TreeNode root) {
            if(root == null){
                return;
            }
            // 将左右子树展开
            flatten(root.left);
            flatten(root.right);

            /**** 后序遍历位置 ****/
            // 将左子树指向右子树
            TreeNode temp = root.right;
            root.right = root.left;
            root.left = null;

            // 将右子树接到新树右子树末端
            TreeNode p = root;
            while(p.right != null){
                p = p.right;
            }
            p.right = temp;
        }
    }
}
