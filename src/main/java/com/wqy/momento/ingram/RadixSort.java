package com.wqy.momento.ingram;

/**
 * @Author qywu11
 * @Date 2022/7/20 8:59
 * @Version 1.0
 */
public class RadixSort {

    public static void radixSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        radixSort(arr,0, arr.length - 1,maxbits(arr));
    }

    // 获取位数
    public static int maxbits(int[] arr){

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max,arr[i]);
        }
        int res = 0;
        while (max!=0){
            res ++;
            max /= 10;
        }
        return res;
    }

    // arr[begin...end排序]
    public static void radixSort(int[] arr,int L,int R, int digit){
        final int radix = 10;
        int i = 0,j = 0;
        int[] bucket = new int[R - L + 1];
        // 有多少数组就准备多少个辅助空间
        for (int d = 1; d <= digit; d++) {
            // 10个空间
            int[] count = new int[radix]; // count[0...9]
            // 计算个位、十位、百位的j有多少个
            for (i = L; i <= R ; i++) {
                j = getDigit(arr[i],d);
                count[j]++;
            }
            // 计算累加和
            // count[0] 当前位(d位)是0的数字有多少个
            // count[1] 当前位(d位)是0、1的数字有多少个
            // count[2] 当前位(d位)是0、1、2的数字有多少个
            // count[i] 当前位(d位)是-i的数字有多少个
            for ( i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }
            // 从右向左遍历
            for (i = R; i >= L ; i--) {
                j = getDigit(arr[i],d);
                bucket[count[j] -1] = arr[i];
                count[j] --;
            }
            for (i = L,j =  0 ; i <= R; i++, j++) {
                arr[i] = bucket[j];
            }

        }
    }

    public static int getDigit(int x,int d){
        return ((x / (int) Math.pow(10,d - 1)) % 10);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,12,3,100,28,66,28,99};
        radixSort(arr);
        for (int j : arr) {
            System.out.println(j);
        }
    }
}
