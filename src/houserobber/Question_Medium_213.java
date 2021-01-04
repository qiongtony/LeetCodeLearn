package houserobber;

/**
 *
 * 213. 打家劫舍 II
 * https://leetcode-cn.com/problems/house-robber-ii/
 */

public class Question_Medium_213 {

    public int rob(int[] nums) {
        if (nums == null || nums.length < 1){
            return 0;
        }
        if (nums.length == 1){
            return nums[0];
        }
        // 第一间房子被抢，最后不抢；最后一间房子被抢，第一间房子不抢
        // 这里不直接加，是因为可能该间不抢，抢下一间的值可能比较大，这里的环相当于把条件收紧了，要第一间可能被抢下会影响最后一间和第二间
        return Math.max(dp(0, nums.length - 2, nums), dp(1, nums.length - 1, nums));
    }

    public int dp(int start, int end, int[ ] nums){
        int dp_1 = 0;
        int dp_2 = 0;
        for (int i = end; i >= start; i--){
            int temp = Math.max(dp_1, dp_2 + nums[i]);
            dp_2 = dp_1;
            dp_1 = temp;
        }
        return dp_1;
    }
}
