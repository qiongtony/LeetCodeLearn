package dynamicprogramming;

//给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
//
// 注意:
//
//
// 每个数组中的元素不会超过 100
// 数组的大小不会超过 200
//
//
// 示例 1:
//
// 输入: [1, 5, 11, 5]
//
//输出: true
//
//解释: 数组可以分割成 [1, 5, 5] 和 [11].
//
//
//
//
// 示例 2:
//
// 输入: [1, 2, 3, 5]
//
//输出: false
//
//解释: 数组不能分割成两个元素和相等的子集.
//
//
//
// Related Topics 动态规划
// 👍 655 👎 0


/**
 * 416. 分割等和子集
 * https://leetcode-cn.com/problems/partition-equal-subset-sum/
 */

public class Question_Medium_416 {
    class Solution {
        /**
         * 动态规划常规解法，可以看作是求背包是否可装到背包总重的一半
         * 状态：可选择的物品个数，最大装到的重量
         * 选择：是当前物品装/不装
         * dp[i][j]表示在可装i个物品的情况下，当前背包重量为j背包是否可装满
         * baseCase：dp[..][0]=true，表示不需要装东西，dp[0][..]=false，表示没有物品可选的时候不能装满
         * @param nums
         * @return
         */
        public boolean canPartition(int[] nums) {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                count += nums[i];
            }
            // 和为奇数不可能分割成功
            if (count % 2 != 0) {
                return false;
            }
            count = count / 2;
            boolean[][] dp = new boolean[nums.length + 1][count + 1];
            // 需要的重量是0
            for (int i = 0; i < nums.length + 1; i++) {
                dp[i][0] = true;
            }
            for (int i = 1; i <= nums.length; i++) {
                for (int j = 1; j <= count; j++) {
                    // 放不下当前的
                    if (j - nums[i - 1] < 0) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        // 可要，可不要
                        dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                    }
                }
            }
            return dp[nums.length][count];
        }

        /**
         * 状态压缩，根据上面的代码，可见，dp[i]只与dp[i-1]有关，所以我们只保留一维，表示重量为j时能否装满
         * 需要注意的是重量需要从大到小，因为从小到大，我们用到的就不是上一行的，而是同行的了！
         * @param nums
         * @return
         */
        public boolean canPartitionOptim(int[] nums){
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                count += nums[i];
            }
            // 和为奇数不可能分割成功
            if (count % 2 != 0) {
                return false;
            }
            count = count / 2;
            // dp[j]表示在某一行，需要j体重时是否符合要求
            boolean[] dp = new boolean[count + 1];
            dp[0] = true;
            // 可以选择前i个背包内的物品
            for (int i = 1; i <= nums.length; i++){
                // 重量从大到小
                for (int j = count; j >= 0; j--){
                    if (j - nums[i - 1] >= 0) {
                        dp[j] = dp[j] || dp[j - nums[i - 1]];
                    }
                }
            }
            return dp[count];

        }
    }
}
