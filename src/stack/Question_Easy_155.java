package stack;

import java.util.Stack;

public class Question_Easy_155 {
    class MinStack {
        // 用两个栈实现，一个栈是正常栈，一个栈栈顶是当前栈内最小的值
        Stack<Integer> normalStack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();
        /** initialize your data structure here. */
        public MinStack() {

        }

        public void push(int x) {
            normalStack.push(x);
            if(minStack.isEmpty()){
                minStack.push(x);
            }else{
                minStack.push(Math.min(minStack.peek(), x));
            }
        }

        public void pop() {
            normalStack.pop();
            minStack.pop();
        }

        public int top() {
            return normalStack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

    /**
     * 方法2：用一个栈实现
     * 关键点：当最小值变更时如何存起来
     * 当我们不断压栈时，当前的栈的最小值是不断变小的，栈可以根据最小值分成几块
     * 压栈：当最小值更新（<=curMin）时我们先将原最小值压栈，然后再将新的最小值压栈，并更新属性变量min
     * 出栈：当栈顶元素与min一样且栈不为空时，再出栈更新min值
     * 栈顶：返回栈顶
     * min：返回属性变量min
     */
    class MinStack2 {
        Stack<Integer> stack;
        int min;
        /** initialize your data structure here. */
        public MinStack2() {
            stack = new Stack<>();
        }

        public void push(int x) {
            if(stack.isEmpty()){
                min = x;
                stack.push(x);
            }else{
                if(x <= min){
                    stack.push(min);
                    min = x;
                }
                stack.push(x);
            }
        }

        public void pop() {
            if(stack.pop() == min && !stack.isEmpty()){
                min = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }
}
