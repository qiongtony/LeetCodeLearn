package deque;

import java.util.HashMap;

public class Question_Hard_239 {
    class Solution {
        /**
         * 暴力法：分成N-K个数组，求每个数组的最大值
         * 时间复杂度：O(N)
         * 空间复杂度：O(N)
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums == null || nums.length == 0){
                return nums;
            }
            if(k == 1){
                return nums;
            }
            // 每个滑动窗口的最大值
            int[] result = new int[nums.length - k + 1];
            for(int i = 0; i < result.length; i++){
                int max = nums[i];
                for(int j = 1; j < k;j++){
                    max = Math.max(max, nums[i + j]);
                }
                result[i] = max;
            }
            return result;
        }
    }
}
