package stack;

import java.util.Stack;

public class Question_Medium_150 {

    class Solution {
        /**
         * 使用栈求逆波兰式的值
         * 时间复杂度：O(N)
         * 空间复杂度：O(1)
         * @param tokens
         * @return
         */
        public int evalRPN(String[] tokens) {
            int result = 0;
            Stack<Integer> stack = new Stack<>();
            // + - * /
            for(int i = 0; i < tokens.length; i++){
                if(tokens[i].equals("+")){
                    stack.push(stack.pop() + stack.pop());
                }else if (tokens[i].equals("-")){
                    int minute = stack.pop();
                    int minus = stack.pop();
                    stack.push(minus - minute);
                }else if (tokens[i].equals("*")){
                    stack.push(stack.pop() * stack.pop());
                }else if (tokens[i].equals("/")){
                    int dividend = stack.pop();
                    int divisor = stack.pop();
                    stack.push(divisor / dividend);
                }else {
                    stack.push(Integer.valueOf(tokens[i]));
                }
            }
            return stack.pop();
        }
    }
}
