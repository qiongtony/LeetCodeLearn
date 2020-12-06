package tree;

public class Tree {
    public Tree left;
    public Tree right;
    public int val;

    public Tree(int value) {
        this.val = value;
    }

    public int getVal() {
        return val;
    }
}
