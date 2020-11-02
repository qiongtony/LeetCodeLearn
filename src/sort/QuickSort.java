package sort;

public class QuickSort implements Sort{
    @Override
    public int sort(int[] a) {
        if (a == null || a.length <= 1){
            return 0;
        }
        quick(a, 0, a.length - 1);
        return 0;
    }

    public void quick(int[] a, int left, int right){
        if (left >= right){
            return;
        }
        int q = partition(a, left, right);
        quick(a, left, q - 1);
        quick(a, q, right);

    }

    public int partition(int[] a,int left, int right){
        int pivot = a[right];
        int i = left;
        // 将a[left..right]分成三部分，a[left..i-1]小于哨兵，a[i+1..right]大于哨兵值
        for (int j = left; j < right; j++){
            if (a[j] < pivot){
                if (i == j){
                    i++;
                }else{
                    // 交换值
                    int tmp = a[i];
                    a[i++] = a[j];
                    a[j] = tmp;
                }
            }
        }
        int tmp = a[i];
        a[i] = a[right];
        a[right] = tmp;
        return i;
    }
}
