package stack;

public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public static void print(ListNode head){
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
