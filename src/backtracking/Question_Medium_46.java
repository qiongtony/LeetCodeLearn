package backtracking;

import java.util.ArrayList;
import java.util.List;

//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例:
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//]
// Related Topics 回溯算法
// 👍 1037 👎 0

public class Question_Medium_46 {

    /**
     * 解法，使用回溯算法
     * 我们需要记住我们当前的路径（已选中的数），以及可选择列表
     * 遍历时，从可选列表选一个数加入路径，然后进行回溯，回溯后将该选择移除重新添加到可选择列表
     * 涉及的几个点：路径、选择列表、结束条件
     * 本题的结束条件是路径是数组的长度，这时候说明这次回溯已经把数全排好位置了，记住该次的排列顺序
     *  时间复杂度为：O(N！)
     */
    class Solution {
        // 存储所有的排列可能性
        private List<List<Integer>> result;
        public Solution(){
            result = new ArrayList<>();
        }

        public List<List<Integer>> permute(int[] nums) {
            return permute(nums, new ArrayList<>());
        }

        public List<List<Integer>> permute(int[] nums, ArrayList<Integer> list){
            // 排好了
            if (list.size() == nums.length){
                result.add(new ArrayList<Integer>(list));
                return result;
            }
            // 遍历
            for (int i = 0; i < nums.length;i++){
                if (list.contains(nums[i])){
                    continue;
                }
                // 添加到路径
                list.add(nums[i]);
                // 回溯
                permute(nums, list);
                // 移除路径
                list.remove(list.size() - 1);
            }
            return result;
        }
    }
}
