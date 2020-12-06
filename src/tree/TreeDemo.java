package tree;

import java.util.*;

public class TreeDemo {
    public static void main(String[]args){

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
