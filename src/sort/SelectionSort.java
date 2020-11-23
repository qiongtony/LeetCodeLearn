package sort;

public class SelectionSort implements Sort{
    @Override
    public int sort(int[] a) {
        int count = 0;
        if (a == null || a.length <= 1){
            return count;
        }
        for (int i = 0; i < a.length;i++){
            int minIndex = i;
            for (int j = i + 1;j < a.length;j++){
                count++;
                if (a[minIndex] > a[j]){
                    minIndex = j;
                }
            }
            int tmp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = tmp;
        }
        return count;
    }
}
