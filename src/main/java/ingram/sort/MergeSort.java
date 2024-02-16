package ingram.sort;

/**
 * @Author qywu11
 * @Date 2022/11/17 10:19
 * @Version 1.0
 * 归并排序
 *  时间复杂度O(N*logN)  空间复杂度O(N)  稳定性 Y
 */
public class MergeSort {

    static void process(int L, int R, int[] arr){
        if(L == R){
            return;
        }
        int mid = L + ((R-L) >>1);

        process(L,mid,arr);
        process(mid+1,R,arr);
        merge(L,mid,R,arr);
    }

    static void merge(int L, int mid, int R, int[] arr){

        int[] helps = new int[R - L + 1];
        int p1 = L;
        int p2 = mid + 1;
        int i = 0;
        while (p1 <= mid && p2 <= R){
            helps[i++] = arr[p1] <= arr[p2] ? arr[p1 ++] : arr[p2 ++];
        }
        while (p1 <= mid){
            helps[i++] = arr[p1 ++];
        }
        while (p2 <= R){
            helps[i++] = arr[p2 ++];
        }
        for (int j = 0; j < helps.length; j++) {
            arr[L + j] = helps[j];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,12,3,100,28,66,28,99};
        process(0,arr.length-1,arr);
        for (int j : arr) {
            System.out.println(j);
        }
    }

}
