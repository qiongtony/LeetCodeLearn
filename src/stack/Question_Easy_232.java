package stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;


public class Question_Easy_232 {
    /**
     * 使用两个栈实现队列
     * 一个栈：入栈
     * 一个栈：出栈（每次pop或peek时要看下本栈是不是空，空的话把入栈的全部压进来）
     * push：O（1）
     * pop/peek：O（n）
     * 均摊：O（1）
     */
    class MyQueue {
        Deque<Integer> stack;
        Deque<Integer> supportStack;
        /** Initialize your data structure here. */
        public MyQueue() {
            stack = new LinkedList<>();
            supportStack = new LinkedList<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            stack.push(x);

        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (supportStack.isEmpty()){
                while(!stack.isEmpty()){
                    supportStack.push(stack.pop());
                }
            }


            return supportStack.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (supportStack.isEmpty()){
                while(!stack.isEmpty()){
                    supportStack.push(stack.pop());
                }
            }


            return supportStack.peek();
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack.isEmpty() && supportStack.isEmpty();
        }
    }
}
