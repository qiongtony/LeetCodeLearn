package basis;

public class EightQueen {
    // 存储的结果，下标表示第几行，值表示第几列
    private int[] result;

    public EightQueen() {
        this.result = new int[8];
    }

    public void cal8queens(int row){
        if (row == 8){
            printQueens(result);
        }
        for (int i = 0; i < 8;i++){
            if (isOk(row, i)){
                result[row] = i;
                cal8queens(row + 1);
            }
        }
    }

    // 判断的依据是该行、该列、左对角线、右对角线除原下标外，没有值，左右对角线只需要算前面的，因为我是你的对角线内的点，你肯定也是我对角线内的点
    private boolean isOk(int row, int column){
        // 左对角线
        int leftCol = column - 1;
        // 右对角线
        int rightCol = column + 1;
        for (int i = row -1; i>= 0; i--){
            // 该列是不是有数据
            if (result[i] == column){
                return false;
            }
            if (leftCol >= 0 && result[i] == leftCol){
                return false;
            }
            if (rightCol <= 8 && result[i] == rightCol){
                return false;
            }
            leftCol--;
            rightCol++;
        }
        return true;
    }

    private void printQueens(int[] result){
    }

    // 记录背包的最大承重
    private int maxW = Integer.MIN_VALUE;

    /**
     * 0-1背包问题
     * 物品只有两种状态：1、在背包里；2、不在背包；递归的处理这两种情况下存储的最大重量
     * @param items     所有物品，value表示物品的重量
     * @param w         背包的最大承重
     */
    public void pack(int []items,int w){
        pack(0, 0, items, w);
    }

    /**
     *
     * @param i         处理物品的下标
     * @param cw        背包已有的承重
     * @param items
     * @param w
     */
    private void pack(int i, int cw, int[] items, int w){
        if (cw == w  || i >= items.length){
            return;
        }

        pack(i + 1, cw, items, w);
        if (cw + items[i] <= w){
            maxW = Math.max(cw + items[i], maxW);
            pack(i + 1, cw + items[i], items, w);
        }
    }
}
