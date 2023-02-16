package ingram.sort;

import java.util.Arrays;

/**
 * @Author qywu11
 * @Date 2022/11/23 10:27
 * @Version 1.0
 */
public class QuickSort {


    public static void quickSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    // 随机快排
//    public static void quickSort(int[] arr, int L, int R) {
//        if (L < R) {
//            swap(arr, L + (int) (Math.random() * (R - L + 1)), R); //先随机取出一个数放到最后
//            int[] p = partition(arr, L, R);
//            quickSort(arr, L, p[0] - 1);
//            quickSort(arr, p[1] + 1, R);
//        }
//    }

    // 经典快排
    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            //swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr, p[1] + 1, R);
        }
    }

    /**
     * 1,7,21,5,51,98,3,2
     *
     * 1,5,21,51,7,98,3,2
     *
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, more, r);
        return new int[] { less + 1, more };
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) {
        int[] arr =  new int[]{3,7,21,5,1,51,98,2};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }


}
