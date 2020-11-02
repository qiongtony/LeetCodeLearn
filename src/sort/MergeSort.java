package sort;

public class MergeSort implements Sort{
    int count = 0;
    @Override
    public int sort(int[]a){
        count = 0;
        if (a == null || a.length <= 1){
            return count;
        }
        mergeSort(a, 0, a.length - 1);
        return count;
    }

    public void mergeSort(int[]a, int start, int end){
        if (start >= end){
            return;
        }
        int mid = (end + start) / 2;
        System.out.println("start = " + start + " mid = " + mid + " end = " + end);
        mergeSort(a, start, mid);
        mergeSort(a, mid + 1, end);
        merge(a, start, mid, end);
    }

    private void merge(int[]a, int start, int mid, int end){
        int[] copy = new int[end - start +1];
        int i = start;
        int j = mid +1;
        int index = 0;
        while (i <= mid && j <= end){
            count++;
            if (a[i] <= a[j]){
                copy[index++] = a[i++];
            }else{
                copy[index++] = a[j++];
            }
        }
        int startIndex = i;
        int endIndex = end;
        // 第一个数组还没用完
        if (i <= mid){
            endIndex = mid;
        }else {
            startIndex = j;
        }
        while (startIndex <= endIndex){
            copy[index++] = a[startIndex++];
        }
        for (int t = 0; t < copy.length; t++){
            a[t + start] = copy[t];
        }
    }
}
