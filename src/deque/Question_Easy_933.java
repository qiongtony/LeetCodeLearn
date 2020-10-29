package deque;

import java.util.Deque;
import java.util.LinkedList;

public class Question_Easy_933 {
    class RecentCounter {
        Deque<Integer> deque;
        public RecentCounter() {
            deque = new LinkedList<>();
        }

        /**
         * 用队列存储符号要求的请求
         * 新请求时：
         *      从队头开始找不符合的元素，不符合出队
         *      将新请求压入队尾
         *
         * 时间复杂度：O(N)
         * 空间复杂度：O(N)
         * @param t
         * @return
         */
        public int ping(int t) {
            int limit = t - 3000;
            while (!deque.isEmpty()){
                if (deque.peekFirst() < limit || deque.peekFirst() > t){
                    deque.pollFirst();
                }else{
                    break;
                }
            }
            deque.addLast(t);
            return deque.size();
        }
    }

}
