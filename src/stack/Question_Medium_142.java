package stack;

import java.util.HashSet;
import java.util.Set;

public class Question_Medium_142 {
    public class Solution {

        /**
         * 根据Set来判断环的入口，当节点已存在时，说明到环的入口
         * @param head
         * @return
         */
        public ListNode detectCycleSet(ListNode head){
            if (head == null){
                return null;
            }
            Set<ListNode> nodeSet = new HashSet<>();
            ListNode p = head;
            while(p != null) {
                if (nodeSet.contains(p)) {
                    return p;
                } else {
                    nodeSet.add(p);
                    p = p.next;
                }
            }
            return null;
        }

        /**
         * 假设链表长度为a + b（b为环的长度）
         * 设置快慢指针：
         * 1.如果快指针到头，那说明没环，返回null；
         * 2.如果快指针与慢指针相遇，说明：
         *                           f = 2s
         *                           根据跑步绕圈的模拟，可知相遇时f = s + nb，即为快的多跑了n圈
         *                           二者解可知 s = nb
         *                           所以慢指针只要在走a步就能回到环的起点了，如何走a步呢？
         *                           设置一个临时变量ptr从head开始，当慢指针与该临时变量相遇则为走了a步
         * @param head
         * @return
         */
        public ListNode detectCycle(ListNode head) {
            if (head == null){
                return null;
            }
            ListNode fast = head;
            ListNode slow = head;
            while (fast != null && fast.next != null){
                slow = slow.next;
                fast = fast.next.next;
                // 链表有环第一次相遇
                if (slow == fast){
                    ListNode tmp = head;
                    while (tmp != slow) {
                        tmp = tmp.next;
                        slow = slow.next;
                    }
                    return tmp;
                }
            }
            return null;
        }
    }
}
