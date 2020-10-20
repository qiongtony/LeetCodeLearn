package stack;

public class Question_Easy_206 {
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(2);
        head.next = next;

        Solution solution = new Solution();
        solution.print(head);
        head = solution.reverseList(head);
        solution.print(head);
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

        // 递归版本，时间复杂度O(n),空间复杂度O(1)
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

        public void print(ListNode head){
            if (head == null){
                System.out.println("null");
                return;
            }
            StringBuilder sb = new StringBuilder();
            ListNode p = head;
            while (p != null){
                sb.append(p.val + "->");
                p = p.next;
            }
            sb.append("null");
            System.out.println(sb.toString());
        }
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
