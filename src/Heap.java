public class Heap {

    private int [] a;
    private int count;

    public Heap() {
        a = new int[16];
    }

    /**
     * 往堆中插入元素
     * @param a
     * @param value
     */
    public void insert(int[] a, int value){
        if (count >= a.length){
            return;
        }
        a[++count] = value;
        int startIndex = count;
        while (startIndex > 1) {
            if (a[startIndex] > a[startIndex / 2]) {
                swap(a, startIndex, startIndex / 2);
                startIndex = startIndex / 2;
            }else{
                break;
            }
        }
    }

    /**
     * 删除堆顶元素
     * @param a
     */
    public void deleteMax(int[] a){
        swap(a, 1, count);
        count--;
        int index = 1;
        while (index <= count) {
            int maxPos = index;
            if ((index * 2) <= count && a[index * 2] > a[index]) maxPos = index * 2;
            if ((index * 2 + 1) <= count && a[maxPos] > a[index * 2 + 1]) maxPos = index * 2 + 1;
            if (maxPos != index) {
                swap(a, maxPos, index);
                index = maxPos;
            }else{
                break;
            }
        }
    }

    public void swap(int[]a, int aIndex, int bIndex){
        int tmp = a[aIndex];
        a[aIndex] = a[bIndex];
        a[bIndex] = tmp;
    }

    /**
     * 堆排序：
     * 1、建堆；
     * 2、排序；
     */
    public void buildHeap(int []a, int n){
        for (int i = n / 2; i >= 1; --i){
            heapify(a, n, i);
        }
    }

    public void heapify(int[] a, int count, int startIndex){
        int index = startIndex;
        int maxPos = startIndex;
        while (index <= count){
            if (index * 2 <= count && a[index * 2] > a[maxPos]) maxPos = index * 2;
            if ((index * 2 + 1) <= count && a[index * 2 + 1] > a[maxPos]) maxPos = index * 2 + 1;
            if (maxPos != index){
                swap(a, maxPos, index);
                index = maxPos;
            }else{
                break;
            }
        }
    }

    /**
     * 堆排序
     * @param a
     * @param n
     */
    public void heapSort(int[] a, int n){
        buildHeap(a, n);
        for(int k = n; k > 1;){
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }
}
