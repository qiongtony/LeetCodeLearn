package list;

public class Question_Easy_141 {
    public static void main(String[] args){
        ListNode head = new ListNode(1);
        ListNode next = new ListNode(2);
        ListNode third = new ListNode(3);
        head.next = next;
        third.next = next;

        Solution solution = new Solution();
        System.out.println("链表是否存在环的结果为：" + solution.hasCycle(head));
    }

    public static class Solution{
        public boolean hasCycle(ListNode head) {
            if (head == null){
                return false;
            }
            // 使用快慢指针，快指针先进入环，最终会与慢指针在环内相遇
            ListNode slow = head;
            ListNode fast = head;
            while(fast != null && fast.next != null){
                if (slow == fast){
                    return true;
                }
                slow = slow.next;
                fast = fast.next.next;
            }
            return false;

        }
    }
}
