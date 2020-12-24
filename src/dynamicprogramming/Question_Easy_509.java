package dynamicprogramming;

import java.util.HashMap;

public class Question_Easy_509 {
    class Solution {
        private HashMap<Integer, Integer> map;
        public Solution(){
            map = new HashMap<>();
        }

        /**
         * 自顶向下的解法
         * base case 0、1的解
         * 状态转移方程：
         *       0               ,n=0
         * f(n)= 1               ,n=1
         *       f(n - 1)+f(n -2),n>=2
         * 递归“减枝”（建立一个“备忘录”，避免重复求相同的值）
         * 递归的时间复杂度：子树的个数*子树求解的复杂度
         *                O(N)
         * 空间复杂度：O(N)
         *
         * @param N
         * @return
         */
        public int fibIterator(int N) {
            if (N == 0) {
                return 0;
            }
            if (N == 1) {
                return 1;
            }
            if (map.containsKey(N)) {
                return map.get(N);
            }
            int result = fib(N - 1) + fib(N - 2);
            map.put(N, result);
            return result;
        }

        /**
         * 动态规划求解
         * 时间复杂度：O(N)
         * 空间复杂度：O(1)
         * @param N
         * @return
         */
        public int fib(int N) {
            if (N == 0) {
                return 0;
            }
            if (N == 1) {
                return 1;
            }
            int p = 0;
            int q = 1;
            int result = 1;
            for (int i = 2; i <= N; i++) {
                result = p + q;
                p = q;
                q = result;
            }
            return result;
        }
    }
}
