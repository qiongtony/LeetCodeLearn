package dynamicprogramming;

public class DPDemo {

    public class Package{
        public int getMaxPackageWeight(int[] weights, int w){
            int[][] arr = new int[weights.length][w + 1];
            // 第一行，只有0和arr[0]
            int maxValue = 0;
            arr[0][0] = 1;
            if (weights[0] <= w) {
                arr[0][weights[0]] = 1;
                maxValue = weights[0];
            }

            for (int i = 1; i < (weights.length); i++){
                int max = -1;
                // 不加入背包的情况
                for (int j = 0; j < (w + 1);j++){
                    arr[i][j] = arr[i - 1][j];
                }
                // 加入背包且不超重的情况
                for (int j = 0; j <= (w - weights[i]); j++){
                    arr[i][j + weights[i]] = 1;
                    maxValue = Math.max(maxValue, j + weights[i]);
                }
            }
            return maxValue;
        }
    }
}
