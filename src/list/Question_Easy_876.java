package list;
//给定一个带有头结点 head 的非空单链表，返回链表的中间结点。
//
// 如果有两个中间结点，则返回第二个中间结点。
//
//
//
// 示例 1：
//
// 输入：[1,2,3,4,5]
//输出：此列表中的结点 3 (序列化形式：[3,4,5])
//返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
//注意，我们返回了一个 ListNode 类型的对象 ans，这样：
//ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next =
//NULL.
//
//
// 示例 2：
//
// 输入：[1,2,3,4,5,6]
//输出：此列表中的结点 4 (序列化形式：[4,5,6])
//由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
//
//
//
//
// 提示：
//
//
// 给定链表的结点数介于 1 和 100 之间。
//
// Related Topics 链表
// 👍 271 👎 0

/**
 * 实现方式
 * 1：数组
 * 由于链表长度为1到100之间，创一个100长度的数组，将所有的节点存在相应的数组下标内；
 * 根据得到的链表长度求出下标，得到中间节点；
 *
 * 2：不用数组
 * 通过遍历链表得到长度；
 * 求出链表中间节点的位置；
 * 从开始记下节点的位置值，当等于中间的位置时返回
 *
 * 3：使用快慢指针
 *
 */
public class Question_Easy_876 {
    /**
     * 使用快慢指针找到中间节点
     * 时间复杂度：O(n)
     * 空间复杂度:O(1)
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
