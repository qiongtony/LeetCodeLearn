package stack;

import java.util.Stack;

public class QuestionEasy_682 {
    class Solution {
        // 时间复杂度：O(N)；
        // 空间复杂度：O(N)
        public int calPoints(String[] ops) {
            // 用栈存储得分
            Stack<Integer> stack = new Stack<>();
            for(int i = 0; i < ops.length; i++){
                if(ops[i].equals("C")){
                    // 栈顶出栈
                    stack.pop();
                }else if (ops[i].equals("D")){
                    // 新栈顶为原栈顶元素的两倍
                    stack.push(2 * stack.peek());
                }else if (ops[i].equals("+")){
                    // +前面栈顶两个元素之和作为新栈顶
                    int top = stack.pop();
                    int newtop = top + stack.peek();
                    stack.push(top);
                    stack.push(newtop);
                }else{
                    // 新值
                    stack.push(Integer.valueOf(ops[i]));
                }
            }
            int result = 0;
            // 分数为，栈内所有元素之和
            while(!stack.isEmpty()){
                result += stack.pop();
            }
            return result;
        }
    }
}
