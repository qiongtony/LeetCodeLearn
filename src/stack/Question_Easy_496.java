package stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Question_Easy_496 {
    // 解法1：暴力法
    // 时间复杂度：O(N2)
    // 空间复杂度：O(1)
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 最直观的，每个元素遍历一遍nums后面的数组
        int[] result = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            // 初始化
            result[i] = -1;
            for(int j = 0; j < nums2.length; j++){
                if(nums2[j] == nums1[i]){
                    while(j < nums2.length){
                        if (nums2[j] > nums1[i]){
                            result[i] = nums2[j];
                            j = nums2.length;
                            break;
                        }
                        j++;
                    }
                }
            }
        }
        return result;
    }

    /**
     * 解法2：用单调栈解决
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        // 栈存储没有找到后面大的元素的值
        Stack<Integer> stack = new Stack<>();
        // 存储的key为元素，value为该元素后面大的元素，没有则为-1
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums2.length;i++){
            // 当前数组值比之前的元素大，匹配成功
            while(!stack.isEmpty() && stack.peek() < nums2[i]){
                map.put(stack.pop(), nums2[i]);
            }
            // 前面的元素都试过了，将当前元素压入栈
            stack.push(nums2[i]);
        }
        // 处理没匹配的元素
        while(!stack.isEmpty()){
            map.put(stack.pop(), -1);
        }
        int[] result = new int[nums1.length];
        for(int i = 0; i < nums1.length; i++){
            result[i] = map.get(nums1[i]);
        }
        return result;
    }
}
