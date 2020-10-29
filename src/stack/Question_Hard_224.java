package stack;

import java.util.Stack;

//实现一个基本的计算器来计算一个简单的字符串表达式的值。
//
// 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格 。
//
// 示例 1:
//
// 输入: "1 + 1"
//输出: 2
//
//
// 示例 2:
//
// 输入: " 2-1 + 2 "
//输出: 3
//
// 示例 3:
//
// 输入: "(1+(4+5+2)-3)+(6+8)"
//输出: 23
//
// 说明：
//
//
// 你可以假设所给定的表达式都是有效的。
// 请不要使用内置的库函数 eval。
//
// Related Topics 栈 数学
// 👍 295 👎 0
public class Question_Hard_224 {
    class Solution {
        public int calculate(String s) {
            if (s == null || s.length() == 0){
                return 0;
            }
            char[] chars = s.toCharArray();
            Stack<Integer> s0 = new Stack<>();
            Stack<Character> s1 = new Stack<>();
            int tmp = -1;
            for(int i = 0; i < chars.length;i++){
                // 空格跳过
                if (chars[i] == ' '){
                    continue;
                }
                if (isNum(chars[i])){
                    // 计算数值123 = ((1) * 10 + 2) * 10 + 3
                    if (tmp == -1){
                        tmp = chars[i] - '0';
                    }else{
                        tmp = tmp * 10 + chars[i] - '0';
                    }
                }else{
                    // 数值未被压入栈中，压入
                    if (tmp != -1){
                        s0.push(tmp);
                        tmp = -1;
                    }
                    /**
                     *   操作符：
                     *      1、操作符栈栈顶是左括号，这时候直接将操作符压入栈
                     *      2、不是左括号，不断出栈，计算当前部分表达式的值，最后将新的操作符压入栈
                     *
                     */

                    if (isOperation(chars[i])){
                        while (!s1.isEmpty()){
                            if (s1.peek() == '('){
                                break;
                            }
                            if (s1.pop() == '+'){
                                s0.push(s0.pop() + s0.pop());
                            }else{
                                int num1 = s0.pop();
                                int num2 = s0.pop();
                                s0.push(num2 - num1);
                            }
                        }
                        s1.push(chars[i]);
                    }else if (chars[i] == ')'){
                        // 右括号，需要求出该部分表达式的值，以左括号为终止条件
                        while (s1.peek() != '('){
                            if (s1.pop() == '+'){
                                s0.push(s0.pop() + s0.pop());
                            }else{
                                int num1 = s0.pop();
                                int num2 = s0.pop();
                                s0.push(num2 - num1);
                            }
                        }
                        s1.pop();
                    }else{
                        // 左括号直接压入
                        s1.push(chars[i]);
                    }
                }
            }
            // 最后一个数还没压入，压入操作数栈栈顶
            if (tmp != -1){
                s0.push(tmp);
            }
            while (!s1.isEmpty()){
                if (s1.pop() == '+'){
                    s0.push(s0.pop() + s0.pop());
                }else{
                    int num1 = s0.pop();
                    int num2 = s0.pop();
                    s0.push(num2 - num1);
                }
            }
            return s0.isEmpty() ? 0 : s0.pop();
        }

        public boolean isOperation(char c){
            return c == '+' || c == '-';
        }

        public boolean isNum(char c){
            return c >= '0' && c <= '9';
        }
    }
}
