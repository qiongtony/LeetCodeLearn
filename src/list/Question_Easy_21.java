package list;

public class Question_Easy_21 {
    class Solution {
        /**
         * 递归解法：
         * 递归的操作 1、l1 + merge(l1.next, l2) l1.val < l2.val
         *          2、l2 + merge(l1, l2.next) l1.val >= l2.val
         * 终止条件，传来的l1或l2为空
         * 时间复杂度：O(m+n)
         * 空间复杂度：O(m+n)---递归调用需要消耗栈空间，最多调用次数是m+n
         * @param l1
         * @param l2
         * @return
         */
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 终止条件，有一个链表为空了，返回另一个链表
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }

        // 小的值作为下一个节点，指向剩余排好序的链表
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
        }

        /**
         * 迭代解法:
         * 创建哨兵节点和prev节点，哨兵节点用来返回新链表的头，prev节点用来表明有序的链表的尾节点
         *
         * 1.有序链表尾部连上指向当前l1和l2中值小的头，更新有序链表尾部，l1和l2值小者改为下一节点
         * 重复1直到l1或者l2为空，让有序链表尾部接上不为空的剩余链表---剩余链表肯定都是比有序链表大的有序值
         * 根据哨兵节点返回头节点
         *
         * 时间复杂度：O(m+n)
         * 空间复杂度：O(1)
         * @param l1
         * @param l2
         * @return
         */
        public ListNode mergeTwoListsIteration(ListNode l1, ListNode l2){
            if (l1 == null && l2 != null){
                return l2;
            }
            if (l1 != null && l2 == null){
                return l1;
            }
            // 创建哨兵节点，这样更好处理合并的操作
            ListNode preHead = new ListNode(-1);
            ListNode prev = preHead;
            while (l1 != null && l2 != null){
                if (l1.val < l2.val){
                    prev.next = l1;
                    prev = prev.next;
                    l1 = l1.next;
                }else{
                    prev.next = l2;
                    prev = prev.next;
                    l2 = l2.next;
                }
            }
            prev.next = l1 == null ? l2 : l1;
            return preHead.next;
        }
    }
}
