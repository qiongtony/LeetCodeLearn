package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class TreeDemo {
    public static void main(String[]args){
        Integer.parseInt("2０００");
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        try {
            atomicBoolean.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            System.out.println(root.getValue());
            preSort(root.left);
            preSort(root.right);
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
            System.out.println(root.getValue());
            midSort(root.right);
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
            System.out.println(root.getValue());
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
                        values.add(tree.getValue());
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
