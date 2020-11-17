package tree;

public class Tree {
    public Tree left;
    public Tree right;
    private int value;

    public Tree(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
