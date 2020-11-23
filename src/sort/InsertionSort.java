package sort;

public class InsertionSort implements Sort{
    @Override
    public int sort(int[] a) {
        int count = 0;
        if (a == null || a.length <= 1) {
            return count;
        }
        for (int i = 1; i < a.length; i++) {
            int tmp = a[i];
            int j = i;
            while (j > 0) {
                if (a[j - 1] > tmp) {
                    count++;
                    a[j] = a[j - 1];
                    j--;
                } else {
                    break;
                }
            }
            a[j] = tmp;
        }
        return count;
    }
}
