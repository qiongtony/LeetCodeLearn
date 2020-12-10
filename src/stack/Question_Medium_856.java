package stack;

import java.util.Stack;

public class Question_Medium_856 {
    class Solution {
        // 方法1、暴力法
        // 首先括号匹配是遇到右括号时找前面遇到的第一个左括号，符合栈后进先出的特点，所以考虑用栈
        public int scoreOfParentheses(String S) {
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < S.length(); i++){
                // 左括号当作0
                if (S.charAt(i) == '('){
                    stack.push(0);
                }else{
                    // 右括号时上一个是左括号，配对值为1
                    if (stack.peek() == 0){
                        stack.pop();
                        stack.push(1);
                    }else {
                        // 这里是嵌套的情况要把所有包住的括号加起来乘以2（A+B）这种
                        int result = 0;
                        while (!stack.isEmpty() && stack.peek() != 0){
                            result+= stack.pop();
                        }
                        // 把本次的左括号给除掉
                        stack.pop();
                        stack.push(result * 2);
                    }
                }
            }
            int result = 0;
            // 计算总的值
            while (!stack.isEmpty()){
                result+= stack.pop();
            }
            return result;
        }
    }
}
