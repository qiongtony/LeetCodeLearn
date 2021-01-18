package houserobber;

import tree.TreeNode;

import java.util.HashMap;

public class Question_Medium_337 {

    /**
     * 第一种解法：
     * 递归+备忘录
     */
    class Solution1 {
        private HashMap<TreeNode, Integer> map;

        public Solution1(){
            map = new HashMap<>();
        }

        public int rob(TreeNode root) {
            // 自顶向下，打劫根，或者左右子树
            if (root == null){
                return 0;
            }
            if (map.containsKey(root)){
                return map.get(root);
            }
            int maxLeft = 0;
            int maxRight = 0;
            if (root.left != null){
                maxLeft = rob(root.left.left) + rob(root.left.right);
            }
            if (root.right != null){
                maxRight = rob(root.right.left)+ rob(root.right.right);
            }

            int res = Math.max(root.val + maxLeft + maxRight, rob(root.left) + rob(root.right));
            map.put(root, res);
            return res;
        }
    }

    /**
     * 解法2：动态规划
     * dp是长度为2的数组，[0]代表在该root下，不抢该根节点，[1]代表在root下，抢该根节点
     * 根据根节点抢不抢有两种情况：
     * 抢：根节点值+左子树不抢的最大值+右子树不抢的最大值
     * 不抢：max{左子树不抢的最大值，左子树抢的最大值} + max{右子树不抢的最大值，右子树抢的最大值}
     */
    class Solution2 {
        public int rob(TreeNode root) {
            int[] dp = dp(root);
            return Math.max(dp[0], dp[1]);
        }

        // dp[0]表示不抢，dp[1]表示抢
        public int[] dp(TreeNode root){
            if(root == null){
                return new int[2];
            }
            // 不抢，下一个可抢可不抢，找大的
            int[] left = dp(root.left);
            int[] right = dp(root.right);

            // 根抢，下一个左和右子树都不能抢
            int dp_rob = root.val + left[0] + right[0];
            // 不抢，下一个左和右可抢，可不抢，左右都求大值
            int dp_not = Math.max(left[1], left[0]) + Math.max(right[0], right[1]);
            return new int[]{dp_not, dp_rob};
        }
    }
}
