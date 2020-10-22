package list;

public class Question_Easy_206 {
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(2);
        head.next = next;

        Solution solution = new Solution();
//        solution.print(head);
//        head = solution.reverseList(head);
//        solution.print(head);

        head = solution.reverseListRecursionReal(head);
        ListNode.print(head);
    }

    public static class Solution{
        // 迭代版本，时间复杂度O(n),空间复杂度O(1)

        /**
         * 实现思路：分为两步
         * 1、当前节点指向前一个节点
         * 2、从第一个开始重复第一个步骤；
         * 第一步需要两个变量，第二步需要记录下一节点的地址也需要一个变量
         * 由于第一步把当前节点的顺序倒序了，所以第二步应该先执行，所以为了解决链表断的问题
         * 实际执行的顺序是2->1，如此
         * @param head
         * @return
         */
        public ListNode reverseList(ListNode head) {
            ListNode prev = null;
            ListNode cur = head;
            while(cur != null){
                ListNode next = cur.next;
                cur.next = prev;
                prev = cur;
                cur = next;
            }
            return prev;
        }

        public ListNode reverseListRecursionReal(ListNode head) {
            // 递归终止的条件，下一节点为空，返回的是反转后的头
            if(head == null || head.next == null){
                return head;
            }
            ListNode cur = reverseListRecursionReal(head.next);
            head.next.next = head;
            head.next = null;
            return cur;
        }

        // 尾递归版本，时间复杂度O(n),空间复杂度O(1)
        public ListNode reverseListRecursion(ListNode head) {
            if(head == null){
                return head;
            }
            head = reverse(null, head);
            return head;
        }

        public ListNode reverse(ListNode prev, ListNode head){
            if(head == null){
                return prev;
            }
            ListNode next = head.next;
            ListNode cur = head;
            cur.next = prev;
            prev = cur;
            return reverse(prev, next);
        }
    }
}
