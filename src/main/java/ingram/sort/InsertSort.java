package ingram.sort;

/**
 * @Author qywu11
 * @Date 2022/11/14 17:18
 * @Version 1.0
 * 插入排序 前i数，取arr[i]往前一个个比较，把arr[i]放在刚好小于的前面(比如 1873 把3放到1387这个位置)，最坏情况下是O(n^2)
 */
public class InsertSort {

    public static void sort(int[] arr){
        if( arr == null || arr.length < 2){
            return;
        }

        for (int i = 1; i < arr.length; i++) { // 数组范围 前1个数，2个数，范围不断增大让数有序，最后整体有序
            for (int j = i; j > 0 && arr[j] < arr[j-1]; j--) {  // 后一个数比前一个数小，或者到最前面没数了才停下来，否则一直往前交换
                swap(arr,j,j-1);
            }
        }


    }

    public static void swap(int[] arr,int i,int j){
        int t = arr[i];
        arr[i] =  arr[j];
        arr[j] = t;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,3,7,43,8,2};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

}
