public class SortDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 6, 3, 2, 1};
        quickSort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void bubbleSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        for (int i = 0; i < a.length; i++) {
            boolean changed = false;
            for (int j = 1; j < (a.length - i); j++) {
                if (a[j - 1] > a[j]) {
                    swap(a, j - 1, j);
                }
            }
            if (!changed) {
                return;
            }
        }
    }

    /**
     * 插入排序，比冒泡排序优的原因，操作只有赋值操作，而冒泡交换操作是三次
     *
     * @param a
     */
    public static void insertSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        for (int i = 1; i < a.length; i++) {
            int tmp = a[i];
            int j = i - 1;
            for (; j >= 0; j--) {
                if (a[j] > tmp) {
                    // 数据移动
                    a[j + 1] = a[j];
                } else {
                    break;
                }
            }
            a[j + 1] = tmp;
        }
    }


    /**
     * 选择排序
     * 将待排序数组分为两个区域：
     * 已排序区和待排序区，遍历一遍待排序区，找到最小元素的位置，与待排序区第一个元素交换位置，待排序区第一个元素位置就进入到已排序区了
     * 重复1步骤直到待排序区为空
     *
     * @param a
     */
    public static void selectionSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        // 找到未排序区最小的元素，与未排序区第一个元素交换位置
        for (int i = 0; i < a.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            swap(a, i, minIndex);
        }
    }

    /**
     * 归并排序：
     * 利用递归思想排序：
     * 待排序数组=两个有序子数组的排序
     *
     * @param a
     */
    public static void mergeSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }
        sort(a, 0, a.length - 1);
    }

    public static void sort(int[] a, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(a, left, mid);
        sort(a, mid + 1, right);

        merge(a, left, mid, right);
    }

    public static void merge(int[] a, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int l = left;
        int r = mid + 1;
        int index = 0;
        while (l <= mid && r <= right) {
            if (a[l] < a[r]) {
                temp[index++] = a[l++];
            } else {
                temp[index++] = a[r++];
            }
        }
        int start = l, end = right;
        if (l <= mid) {
            end = mid;
        }
        {
            start = r;
        }
        for (; index < temp.length; index++) {
            temp[index] = a[start++];
        }
        for (int i = 0; i < temp.length; i++) {
            a[i + left] = temp[i];
        }
    }

    public static void quickSort(int[] a) {
        if (a == null || a.length <= 1) {
            return;
        }

    }

    public static void quickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
//        int mid = partition(a, 0, a.length - 1);
        int mid = partition2(a, 0, a.length - 1);
        quickSort(a, 0, mid);
        quickSort(a, mid + 1, r);
    }

    public static int partition(int[] a, int l, int r) {
        int q = a[r];
        // i的位置的值小于分区值
        int i = l;
        for (int j = l; j < r; j++) {
            // j找到大于分区值的位置，交换i和j的位置，让分区的值符合规则
            if (a[j] < q) {
                swap(a, i, j);
                i++;
            }
        }
        swap(a, i, r);
        a[i] = q;
        return i;
    }

    public static int partition2(int[] a, int l, int r) {
        int q = a[r];
        int start = l;
        int end = r;
        while (start <= end) {
            // 从左往右找第一个大于中值的下标
            while (a[start] < q) {
                start++;
            }
            // 从右往左找第一个大于中值的下标
            while (a[end] > q) {
                end--;
            }
            if (start <= end) {
                swap(a, start, end);
            }
            start++;
            end--;
        }
        a[start] = q;
        return start;
    }

    public static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    /**
     * 从有序元素可重复的数组中找到第一个给定值的下标
     * 重点在条件第一个，这个问题可以拆解成普通的二分查找以及当找到值时判断第一个这两个问题
     * 第二个问题我只需要看下当前是不是第一个元素或者上一个是不是不等于查找值，满足时当前下标的值就是第一个查找值
     * 否则要取前面的部分继续重复
     * @param a
     * @return
     */
    public static int sort(int[]a, int number){
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
            int mid = low + (high - low) / 2;
            //
            if (a[mid] == number){
                if (mid == 0 || a[mid - 1] != number){
                    return mid;
                }else{
                    high = mid -1;
                }
            }else{
                if (a[mid] < number){
                    high = mid - 1;
                }else{
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
