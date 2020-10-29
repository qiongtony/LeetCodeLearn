package recursion;

import java.util.HashMap;
import java.util.Map;

public class Question_Easy_1137 {
    class Solution {
        public Map<Integer, Integer> map = new HashMap<>();

        /**
         * 用递归实现
         * 分解：
         * f(n) = f(n-3) + f(n-2) + f(n-1)
         * 终止条件：
         * f(0)=0,f(1)=1,f(2)=1
         * @param n
         * @return
         */
        public int tribonacci(int n) {
            if(n == 0){
                return 0;
            }
            if(n == 1){
                return 1;
            }
            if(n == 2){
                return 1;
            }
            return value(n- 1) + value(n- 2) + value(n- 3);
        }

        public int value(int n){
            if (map.containsKey(n)){
                return map.get(n);
            }
            int value = tribonacci(n);
            map.put(n, value);
            return value;
        }
    }
}
