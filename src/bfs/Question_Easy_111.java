package bfs;

import tree.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目：111. 二叉树的最小深度
 * https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/submissions/
 */
public class Question_Easy_111 {
    class Solution {
        /**
         * 解题：本题目求解的最短距离，符合BFS算法的解题框架
         * 包含三要素：记录已访问节点的列表、记录当前的距离、起点、终点（结束的标志）
         * @param root
         * @return
         */
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            int step = 1;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int st = queue.size();
                for (int i = 0; i < st; i++) {
                    TreeNode p = queue.poll();
                    if (p.left == null && p.right == null) {
                        return step;
                    }
                    if (p.left != null) {
                        queue.offer(p.left);
                    }
                    if (p.right != null) {
                        queue.offer(p.right);
                    }
                }
                step++;
            }
            return 1;
        }
    }
}
