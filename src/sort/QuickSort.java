package sort;

public class QuickSort implements Sort{
    @Override
    public int sort(int[] a) {
        if (a == null || a.length <= 1){
            return 0;
        }
        quickSort(a, 0, a.length - 1);
        return 0;
    }

    public void quickSort(int[]a,int left, int right){
        if (left >= right){
            return;
        }
        int p = partition(a, left, right);
        quickSort(a, left, p - 1);
        quickSort(a, p + 1, right);
    }

    private int partition(int[]a, int left, int right){
        int i = left;
        int pivot = a[right];
        // 将a[left..right]分成三部分，a[left..i-1]小于哨兵，a[i+1..right]大于哨兵值
        for (int j = left;j < right;j++){
            if (a[j] < pivot){
                if (i == j){
                    i++;
                }else{
                    swap(a, i, j);
                    i++;
                }
            }
        }
        swap(a, i, right);
        return i;
    }

    private void swap(int[] a, int left, int right){
        int tmp = a[left];
        a[left] = a[right];
        a[right] = tmp;
    }
}
