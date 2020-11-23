package sort;

/**
 * 冒泡排序
 */
public class BubbleSort implements Sort{

    @Override
    public int sort(int[] a){
        int count = 0;
        if (a == null || a.length <= 1){
            return count;
        }
        for (int i = 0; i < a.length; i++){
            boolean changed = false;
            for(int j = 0; j < (a.length - i - 1);j++){
                if (a[j] > a[j + 1]){
                    count++;
                    changed = true;
                    int tmp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = tmp;
                }
            }
            if (!changed){
                return count;
            }
        }
        return count;
    }
}
