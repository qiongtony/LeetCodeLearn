package tree;

import java.util.*;

public class TreeDemo {
    public static void main(String[]args){
        System.out.println(" 3/2 = " + (calculate("3/2")));
    }

    /**
     * 计算器实现的拆解：
     * 1、根据字符串返回数字：如“123”,返回指123
     * 2、运算符优先级：括号>"*"/"/"> +/-，高优先级要先算
     * 3、将运算符的与上一个数字当作一个整体：如“1+2*3-4”，拆分为"+1"、“+2”、“*3”、“-4”，经过优先级排序后的执行顺序是：“*3”、“+2”、”+1“、”-4“
     * 4、特殊的处理：遇到非数字且非空格或者字符串到最好一个，需要处理这个数值，以免单个数或者最后是空格的情况，如”3“、”1+2 “
     * 5、括号的处理，左右括号包住的相当于又是表达式，所以可以先将表达式求值，然后按普通的运算符去处理，比较麻烦的是要记下每次包住的范围而Java没有引用需要创个数组或Integer来记录，下次从后面开始处理，
     * @param s
     * @return
     */
    public static int calculate(String s) {
        char sign = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length();i++){
            // 处理数字，如123 = ((1) * 10 + 2) * 10 + 3
            if (isDigit(s.charAt(i))){
                num = num * 10 + (s.charAt(i) - '0');
            }
            // 空格要在这里处理，不然可能漏掉最后一个数，如"3 + 2 "这种坑爹的写法
            if ((!isDigit(s.charAt(i)) && s.charAt(i) != ' ') | i == (s.length() - 1)){
                // 求出上一个的值：+num or -num，然后清空数字，记录下当前的操作符供下次使用
                switch (sign){
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        int pre = stack.pop();
                        stack.push(num * pre);
                        break;
                    case '/':
                        int pre2 = stack.pop();
                        stack.push(pre2 / num);
                        break;
                }
                sign = s.charAt(i);
                num = 0;
            }
        }
        int result = 0;
        while (!stack.isEmpty()){
            result += stack.pop();
        }
        return result;
    }

    /**
     * 递归求解带括号的表达式
     * @param s
     * @param index
     * @return
     */
    private int dfs(String s, int[] index){
        char sign = '+';
        int num = 0;
        Stack<Integer> stack = new Stack<>();
        for (; index[0] < s.length();index[0]++){
            char ch = s.charAt(index[0]);
            if (ch == '('){
                index[0]++;
                num = dfs(s, index);
            }
            // 处理数字，如123 = ((1) * 10 + 2) * 10 + 3
            if (isDigit(ch)){
                num = num * 10 + (ch - '0');
            }
            // 1+(2+4)-3 +1 +6 -3 1+(2+3)
            // 空格要在这里处理，不然可能漏掉最后一个数，如"3 + 2 "这种坑爹的写法
            if ((!isDigit(ch) && ch != ' ') || index[0] == (s.length() - 1)){
                // 求出上一个的值：+num or -num，然后清空数字，记录下当前的操作符供下次使用
                switch (sign){
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        int pre = stack.pop();
                        stack.push(num * pre);
                        break;
                    case '/':
                        int pre2 = stack.pop();
                        stack.push(pre2 / num);
                        break;
                }
                sign = ch;
                num = 0;
            }
            if (ch == ')'){
                break;
            }
        }
        int result = 0;
        while (!stack.isEmpty()){
            result += stack.pop();
        }
        return result;
    }

    private static boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }


    class TreeSort{
        /**
         * 前序遍历
         * preSort(r) = print r + preSort(r->left) + preSort(r.right)
         * @param root
         */
        private void preSort(Tree root){
            if (root == null){
                return;
            }
            System.out.println(root.getVal());
            preSort(root.left);
            preSort(root.right);
        }

        /**
         * 前序遍历：循环编译
         * 因为前序遍历的顺序是根左右，根就是弹出的Tree，而左要在原根的右边Tree前出来，所以应该是后进先出，用栈，这样同级的进入右左，同级的左先出来
         * @param root
         * @return
         */
        private List<Integer> preSort2(Tree root){
            List<Integer> list = new ArrayList<>();
            Stack<Tree> stack = new Stack<>();
            stack.push(root);
            // 栈压入右、左，出栈左右
            while (!stack.isEmpty()){
                Tree p = stack.pop();
                list.add(p.getVal());
                if (p.right != null){
                    stack.push(p);
                }
                if (p.left != null){
                    stack.push(p.left);
                }
            }
            return list;
        }

        /**
         * 中序遍历
         * midSort(r) = midSort(r.left) + print(r) + midSort(r.right)
         * @param root
         */
        private void midSort(Tree root){
            if (root == null){
                return;
            }
            midSort(root.left);
            System.out.println(root.getVal());
            midSort(root.right);
        }

        /**
         * 中序遍历：循环版
         * @param root
         * @return
         */
        private List<Integer> midSort2(Tree root){
            List<Integer> list = new ArrayList<>();
            if (root == null){
                return list;
            }
            Stack<Tree> stack = new Stack<>();
            stack.push(root);
            Tree cur = root;
            while (!stack.isEmpty()){
                // 一直往左，加节点压入栈中
                if (cur != null){
                    cur = cur.left;
                    if (cur != null) {
                        stack.push(cur);
                    }
                }else{
                    // 左边到尽头了，将节点值加入list，处理右子树
                    Tree tmp = stack.pop();
                    list.add(tmp.val);
                    if (tmp.right != null){
                        cur = tmp.right;
                        stack.push(tmp.right);
                    }
                }
            }
            return list;
        }

        /**
         * 后序遍历
         * postSort(r) = postSort(r.left) + postSort(r.right) + print(r)
         * @param root
         */
        public void postSort(Tree root){
            if (root == null){
                return;
            }
            postSort(root.left);
            postSort(root.right);
            System.out.println(root.getVal());
        }

        public List<Integer> postSort2(Tree root){
            List<Integer> list = new ArrayList<>();
            if (root == null){
                return list;
            }
            Stack<Tree> stack = new Stack<>();
            // 上次操作的节点，操作过了，就不需要再添加到栈处理了
            Tree cur = root;
            stack.push(root);
            while(!stack.isEmpty()){
                // 优先添加左子树
                if (stack.peek().left != null && stack.peek().left != cur && stack.peek().right != cur){
                    stack.push(stack.peek().left);
                    // 还没添加右子树的话添加右子树
                }else if (stack.peek().right != null && stack.peek().right != cur){
                    stack.push(stack.peek().right);
                }else{
                    // 弹出，添加根节点到list
                    cur = stack.pop();
                    list.add(cur.val);
                }
            }
            return list;
        }

        /**
         * 层序遍历
         * 将该层的节点存储到队列中；
         * 1、将队列出队转换成List，遍历List将节点的值存到List<Value>内，并将不为空左右子树入队；
         * 2、重复步骤1直到队列为空。
         * @param root
         * @return
         */
        public List<List<Integer>> centSort(Tree root){
            if (root == null){
                return new ArrayList<>();
            }
            List<List<Integer>> valueList = new ArrayList<>();
            Queue<Tree> treeQueue = new LinkedList<>();
            List<Tree> treeList = new ArrayList<>();
            treeQueue.add(root);
            while (!treeQueue.isEmpty()){
                treeList.add(treeQueue.poll());
                if (treeQueue.isEmpty()) {
                    List<Integer> values = new ArrayList<>();
                    for (Tree tree : treeList){
                        values.add(tree.getVal());
                        if (tree.left != null) {
                            treeQueue.add(tree.left);
                        }
                        if (tree.right != null){
                            treeQueue.add(tree.right);
                        }
                    }
                    valueList.add(values);
                    treeList.clear();
                }
            }
            return valueList;
        }
    }

}
