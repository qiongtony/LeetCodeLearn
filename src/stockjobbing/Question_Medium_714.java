package stockjobbing;

/**
 * 714. 买卖股票的最佳时机含手续费
 *
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/
 */
public class Question_Medium_714 {

    class Solution {
        public int maxProfit(int[] prices, int fee) {
            if(prices == null || prices.length <= 1){
                return 0;
            }
            int dp_0 = 0,dp_1 = -prices[0];
            // dp[i][0] = max{dp[i - 1][0], dp[i - 1][1] + prices[i] - fee}
            // dp[i][1] = max{dp[i - 1][1], dp[i - 1][0] - prices[i]}
            for(int i = 1; i < prices.length;i++){
                int temp = dp_0;
                dp_0 = Math.max(dp_0, dp_1 + prices[i] - fee);
                dp_1 = Math.max(dp_1, dp_0 - prices[i]);
            }
            return dp_0;
        }
    }

}
