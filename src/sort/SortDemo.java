package sort;

public class SortDemo {
    public static void main(String[] args){
        System.out.println("冒泡排序：");
        sort(new BubbleSort());
        System.out.println("插入排序：");
        sort(new InsertionSort());
        System.out.println("选择排序：");
        sort(new SelectionSort());
        System.out.println("归并排序：");
        sort(new MergeSort());

        System.out.println("快速排序：");
        sort(new QuickSort());
    }

    public static void sort(Sort sort){
        int[] a = new int[]{3,2,1,5,6,7,8};
        StringBuilder sb = new StringBuilder();
        sb.append("before sort = ");
        for (int value : a){
            sb.append(value + " ");
        }
        System.out.println(sb.toString());
        int count = sort.sort(a);

        sb = new StringBuilder();
        sb.append("after sort = ");
        for (int value : a){
            sb.append(value + " ");
        }
        sb.append(" \n count = " + count);
        System.out.println(sb.toString());
    }
}
