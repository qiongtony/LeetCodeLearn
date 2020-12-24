package binarySearch;

public class Question_Easy_704_binary_search {
    class Solution {
        /**
         * 二分查找
         * @param nums
         * @param target
         * @return
         */
        public int search(int[] nums, int target) {
            if(nums == null){
                return -1;
            }
            // 使用左右闭区间
            int left = 0;
            int right = nums.length - 1;
            // <=，是因为用的是左右闭区间[0,0]也是
            while(left <= right){
                int mid = left + (right - left) / 2;
                if(nums[mid] > target){
                    right = mid - 1;
                }else if(nums[mid] == target){
                    return mid;
                }else if(nums[mid] < target){
                    left = mid + 1;
                }
            }
            return -1;
        }
    }
}
