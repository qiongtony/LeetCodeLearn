public class Heap {

    private int [] a;
    private int count;

    public Heap() {
        a = new int[16];
    }

    /**
     * 往堆中插入元素
     * @param a
     * @param index
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
}
