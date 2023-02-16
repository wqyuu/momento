package ingram.sort;

import java.util.Random;

/**
 * @Author qywu11
 * @Date 2022/11/15 16:49
 * @Version 1.0
 * 
 * 二分局部最小
 * 找某数
 * 找某数最左
 */
public class BinarySearch {
    
    public static int baseSearch(int[] arr,int x){

        int low = 0;
        int high = arr.length;
        // 123456789  6
        while (low <= high){
            int mid = (low + high)/2;
            if(arr[mid] == x){
                return mid;
            }else if(arr[mid] < x){
                low = mid + 1;
            }else if(arr[mid] > x){
                high = mid + 1;
            }
        }
        
        return -1;
    }

    // 找某数最左
    public static int baseSearch1(int[] arr,int x){

        int low = 0;
        int high = arr.length;
        // 1,2,2,2,2,3,3,3,4,4,4,5  3
        while (low <= high){
            int  mid = (low + high)/2;
            if(arr[mid] == x){
                while (arr[mid] == x){
                    mid --;
                }
                return mid + 1;
            }else if(arr[mid] < x){
                low = mid + 1;
            }else if(arr[mid] > x){
                high = mid + 1;
            }

        }

        return -1;
    }

    // 找某数最左
    public static int baseSearch2(int[] arr){

        int low = 0;
        int high = arr.length;
        int  mid = (low + high)/2;
        // 1,2,2,2,2,3,3,3,4,4,4,5  3
        while ( arr[low] < arr[mid]  && arr[mid]  < arr[high]){



        }

        return -1;
    }

    public static void main(String[] args) {
//        int[] arr = {4,5,2,8,10,3,21,6,4,19,40,55};
//        int x = 3;
//        int rs = baseSearch(arr,x);
//        System.out.println(rs);

        Random r = new Random();
        r.nextInt(10);
        System.out.println(r.nextInt());
        // 1 chaof 2 yaxue 3 nanc 4 lurou 5 mla 6 fen 7 m 8 jz 9 bao 10 zha
    }














    /*
     * 循环实现二分查找算法arr 已排好序的数组x 需要查找的数-1 无法查到数据
     */
    public static int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length-1;
        while(low <= high) {
            int middle = (low + high)/2;
            if(x == arr[middle]) {
                return middle;
            }else if(x <arr[middle]) {
                high = middle - 1;
            }else {
                low = middle + 1;
            }
        }
        return -1;
    }
    //递归实现二分查找
    public static int binarySearch(int[] dataset,int data,int beginIndex,int endIndex){
        int midIndex = (beginIndex+endIndex)/2;
        if(data <dataset[beginIndex]||data>dataset[endIndex]||beginIndex>endIndex){
            return -1;
        }
        if(data <dataset[midIndex]){
            return binarySearch(dataset,data,beginIndex,midIndex-1);
        }else if(data>dataset[midIndex]){
            return binarySearch(dataset,data,midIndex+1,endIndex);
        }else {
            return midIndex;
        }
    }
    
}
