package sort;

/**
 * 二分查找相关问题
 */
public class BSearchSort {

    // 在元素可重复的有序数组里查找第一个等于给定值元素
    public static int firstValue(int[] a, int value){
        if (a == null){
            return -1;
        }

        return -1;
    }

    // 变体二：查找最后一个值等于给定值的元素
    public static int lastValue(int[] a, int value){
        if (a == null){
            return -1;
        }
        int low = 0;
        int high = a.length - 1;
        while (low <= high){
            int mid = low + (high - low) / 2;
            if (a[mid] < value){
                low = mid + 1;
            }else if (a[mid] > value){
                high = mid - 1;
            }else {
                // 相等有三种情况：
                //              1、已经是最后一个元素，那肯定是最后的给定值，返回
                //              2、下一个元素也是给定值，那最后的值肯定在a[mid + 1, high]里
                //              3、下一个元素不是给定值，返回下标
                if (mid == (a.length - 1)){
                    return mid;
                }else if (a[mid + 1] == value){
                    low = mid + 1;
                }else{
                    return mid;
                }
            }
        }
        return -1;
    }

    // 变体三：查找第一个大于等于给定值的元素，这个其实类似于变体二，把等于的条件扩大未大于等于
    public static int firstNotLessThanValue(int []a, int value) {
        if (a == null) {
            return -1;
        }
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] < value) {
                low = mid + 1;
            } else{
                // 第一个元素，返回位置
                if (mid == 0) {
                    return mid;
                    // 上一个元素不小于给定值，第一个肯定在a[low, mid -1]内
                } else if (a[mid - 1] >= value) {
                    high = mid - 1;
                } else {
                    // 上一个元素小于给定值，返回位置
                    return mid;
                }
            }
        }
        return -1;
    }

    // 变体四：查找最后一个小于等于给定值的元素
    public static int lastNotLargeThanValue(int [] a, int value){
        if (a == null) {
            return -1;
        }
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] > value){
                high = mid - 1;
            }else{
                if (mid == (a.length - 1)){
                    return mid;
                }else if (a[mid + 1] > value){
                    return mid;
                }else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
