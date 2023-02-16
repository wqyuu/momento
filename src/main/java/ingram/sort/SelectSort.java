package ingram.sort;

/**
 * @Author qywu11
 * @Date 2022/11/14 17:18
 * @Version 1.0
 * 选择排序 从0位置开始，找到后面最小，跟0交换，然后从1位置开始，继续不断的交换  时间复杂度是O(n^2)
 * 时间复杂度O(N^2)  空间复杂度O(1)  稳定性  N
 */
public class SelectSort {

    public static void sort(int[] arr){
        if( arr == null || arr.length < 2){
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) { // 0-n
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                min = arr[j] > arr[min] ? min : j;
            }
            swap(arr,min,i);
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
