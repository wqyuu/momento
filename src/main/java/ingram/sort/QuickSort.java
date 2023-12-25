package ingram.sort;

import java.util.Arrays;

/**
 * @Author qywu11
 * @Date 2022/11/23 10:27
 * @Version 1.0
 *
 * 快排
 * 荷兰国旗问题
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

    // 经典快排 时间复杂度最差 O(N^2)   空间复杂度O(N)  加上随机取划分值复杂度最差 O(N*logN)
    public static void quickSort(int[] arr, int L, int R) {
        if (L < R) {
            // 等概率随机选一个数，与最后一个数做交换，然后用交换后的R做为第一次划分值
            //swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1); // p[0] 是等于区域左边界 p[0] - 1是小于区域右边界
            quickSort(arr, p[1] + 1, R); // p[1] 是等于区域右边界 p[1] + 1是大于区域左边界
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
        // [ 1,3,10,6,37,29,22]
        // 1,3                   [less:1  --  l:2]
        // 1,3,10            [less:2  --  l:3]
        // 1,3,10,6,          [less:2  --  l:3]
        // 1,3,10,6,29         [less:3  --  l:4]
        // 1,3,10,6,22,37,29         [less:3  --  l:4]
        while (l < more) {
            if (arr[l] < arr[r]) { // 左小于右侧 左组 向右扩，把小于区域后一个跟当前换
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
