package stack;

import java.util.Stack;

public class Question_Easy_844 {
    class Solution {
        // 解法1
        public boolean backspaceCompare(String S, String T) {
            Stack<Character> sStack = new Stack<>();
            Stack<Character> tStack = new Stack<>();
            generateStack(sStack, S);
            generateStack(tStack, T);
            if (sStack.size() != tStack.size()){
                return false;
            }
            while (!sStack.isEmpty()){
                if (sStack.pop() != tStack.pop()){
                    return false;
                }
            }
            return true;
        }

        public void generateStack(Stack<Character> stack, String s){
            for(int i = 0; s != null && i < s.length();i++){
                char c = s.charAt(i);
                if (c == '#'){
                    if(!stack.isEmpty()){
                        stack.pop();
                    }
                }else{
                    stack.push(c);
                }
            }
        }
    }

    // 解法2，使用2个指针从字符串后面往回移
    public boolean backspaceCompare2(String S, String T) {
        // 两个指针从后面开始
        int i = S != null ? S.length() - 1 : -1;
        int j = T != null ? T.length() - 1 : -1;
        int sNeedDeleteCount = 0;
        int tNeedDeleteCount = 0;
        while(i >= 0 || j >= 0){
            while(i >= 0){
                // 后退，需要先把字符跳过去，才能得到真正显示的字符
                if (S.charAt(i) == '#'){
                    sNeedDeleteCount ++;

                }else if (sNeedDeleteCount > 0){
                    sNeedDeleteCount --;
                }else{
                    break;
                }
                i--;
            }

            while(j >= 0){
                if (T.charAt(j) == '#'){
                    tNeedDeleteCount++;
                }else if (tNeedDeleteCount > 0){
                    tNeedDeleteCount--;
                }else{
                    break;
                }
                j--;
            }
            if (i >= 0 && j >= 0){
                if (S.charAt(i) != T.charAt(j)){
                    return false;
                }
            }else{
                if (i >= 0 || j >= 0){
                    return false;
                }
            }
            i--;
            j--;
        }
        return true;

    }
}
