package tree;

/**
 * 116. 填充每个节点的下一个右侧节点指针
 * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 */
public class Question_Medium_116 {
    class Solution {
        public Node connect(Node root) {
            if(root == null){
                return root;
            }
            root.next = null;
            connectTwoNode(root.left, root.right);
            return root;
        }

        /**
         * 链接两个节点
         * 根节点左指向右
         * 左右节点的子节点链接
         * 左节点的右子节点链接右节点的左子节点
         * @param left
         * @param right
         */
        public void connectTwoNode(Node left, Node right){
            if(left == null || right == null){
                return ;
            }
            left.next = right;
            connectTwoNode(left.left, left.right);
            connectTwoNode(right.left, right.right);
            connectTwoNode(left.right, right.left);
        }
    }


    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

}
