package stack;
//序列化二叉树的一种方法是使用前序遍历。当我们遇到一个非空节点时，我们可以记录下这个节点的值。如果它是一个空节点，我们可以使用一个标记值记录，例如 #。
//
//      _9_
//    /   \
//   3     2
//  / \   / \
// 4   1  #  6
/// \ / \   / \
//# # # #   # #
//
//
// 例如，上面的二叉树可以被序列化为字符串 "9,3,4,#,#,1,#,#,2,#,6,#,#"，其中 # 代表一个空节点。
//
// 给定一串以逗号分隔的序列，验证它是否是正确的二叉树的前序序列化。编写一个在不重构树的条件下的可行算法。
//
// 每个以逗号分隔的字符或为一个整数或为一个表示 null 指针的 '#' 。
//
// 你可以认为输入格式总是有效的，例如它永远不会包含两个连续的逗号，比如 "1,,3" 。
//
// 示例 1:
//
// 输入: "9,3,4,#,#,1,#,#,2,#,6,#,#"
//输出: true
//
// 示例 2:
//
// 输入: "1,#"
//输出: false
//
//
// 示例 3:
//
// 输入: "9,#,#,1"
//输出: false
// Related Topics 栈
// 👍 139 👎 0

import java.util.Stack;

public class Question_Medium_331 {
    class Solution {
        /**
         * #的个数比数字个数多1
         * 思路：
         * 槽位概念：非空和空节点占一个槽位，非空节点增加两个槽位即左右孩子
         * 判断条件：槽位不可能为负数，且遍历完成后槽位要等于0（刚好用完）
         * 时间复杂度：O(N)
         * 空间复杂度：O(1)
         * @param preorder
         * @return
         */
        public boolean isValidSerialization(String preorder) {
            /*
            // 方法1，遍历
            int slot = 1;
            for (int i = 0; i < preorder.length();i++){
                if (preorder.charAt(i) == ','){
                    // 非空和空节点都消耗一个槽位
                    --slot;
                    // 槽位是负数提前结束
                    if (slot < 0){
                        return false;
                    }
                    // 非空节点，槽位+2
                    if (preorder.charAt(i - 1) != '#'){
                        slot+=2;
                    }
                }
            }
            // 最后单独处理，不是空节点：-1+2=+1
            if (preorder.charAt(preorder.length() - 1) != '#'){
                slot+=1;
            }else{
                // 空节点 -1
                --slot;
            }
            // 判断槽位是不是为空
            return slot == 0;
            */

            // 方法2：用栈，原因：#的个数比数字个数大1
            // 遇到‘，’，看前一个字符，
            //                  如果是数字，压入栈
            //                  #，判断3
            // 3、
            //      栈为空，返回false
            //      不为空，栈定出栈；
            // 遍历完后，看最后一个字符：
            // ’#'且栈为空，true
            // 其余情况为，false
            if ("#".equals(preorder)){
                return true;
            }
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < preorder.length(); i++){
                if (preorder.charAt(i) == ','){
                    if (preorder.charAt(i - 1) == '#'){
                        if (stack.isEmpty()){
                            return false;
                        }
                        stack.pop();
                    }else{
                        stack.push(preorder.charAt(i - 1));
                    }
                }
            }
            if (preorder.charAt(preorder.length() - 1) == '#'){
                return stack.isEmpty();
            }else{
                return false;
            }
        }
    }
}
