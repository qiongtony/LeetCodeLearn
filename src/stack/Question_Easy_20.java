package stack;

import java.util.Stack;
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
// 有效字符串需满足：
//
//
// 左括号必须用相同类型的右括号闭合。
// 左括号必须以正确的顺序闭合。
//
//
// 注意空字符串可被认为是有效字符串。
//
// 示例 1:
//
// 输入: "()"
//输出: true
//
//
// 示例 2:
//
// 输入: "()[]{}"
//输出: true
//
//
// 示例 3:
//
// 输入: "(]"
//输出: false
//
//
// 示例 4:
//
// 输入: "([)]"
//输出: false
//
//
// 示例 5:
//
// 输入: "{[]}"
//输出: true
// Related Topics 栈 字符串
// 👍 1930 👎 0

/**
 * 解决方案：遇到右括号才需要判断是否闭合，这符合栈后进先出的特点
 *          栈存储左括号，当遇到右括号，看看栈顶是不是左括号，是的话出栈，不是的话说明不闭合
 *          最后判断一下栈是不是空的，不为空说明还有没闭合的左括号，也不闭合
 * 时间复杂度：O(N)
 * 空间复杂度：O(N)
 */
public class Question_Easy_20 {
    class Solution {
        public boolean isValid(String s) {
            if(s == null || s.length() == 0){
                return true;
            }
            Stack<Character> stack = new Stack<>();
            for(int i = 0; i < s.length();i++){
                char c = s.charAt(i);
                if(c == ')' || c == '}' || c == ']'){
                    if(stack.isEmpty()){
                        return false;
                    }
                    switch(c){
                        case ')':{
                            if(stack.pop() != '('){
                                return false;
                            }
                            break;
                        }
                        case '}':{
                            if(stack.pop() != '{'){
                                return false;
                            }
                            break;
                        }
                        case ']':{
                            if(stack.pop() != '['){
                                return false;
                            }
                            break;
                        }
                    }
                }else{
                    stack.push(c);
                }
            }
            return stack.isEmpty();
        }
    }
}
