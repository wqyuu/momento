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
        // 下面两个(1)、(2)只能有一个生效
        // (1)p1没越界
        while (p1 <= mid){
            helps[i++] = arr[p1 ++];
        }
        // (2)p2没越界
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
            System.out.print(j+" ");
        }

        System.out.println(" ---- 20230602 ---- ");

        process20230602(0,arr.length-1,arr);
        for (int j : arr) {
            System.out.print(j+" ");
        }
    }


    static void process20230602(int L, int R, int[] arr){

        int mid = L + ((R - L)>>1);
        // 左边
        process(L,mid,arr);
        // 右边
        process(mid+1,R,arr);
        // 合并
        merge20230602(L,R,arr);
    }

    static void merge20230602(int L, int R, int[] arr){
        int mid = L + ((R - L)>>1);
        int i = 0;
        int p1 = 0;
        int p2 = mid + 1;
        int[] help = new int[R-L+1];
        while (p1 <= mid && p2 <= R){
           help[i++] = arr[p1] <= arr[p2] ? arr[p1 ++] : arr[p2 ++];
        }
        while (p1 <= mid){
            help[i++] =arr[p1 ++];
        }
        while (p2 <= R){
            help[i++] =arr[p2 ++];
        }

        for (int j = 0; j < help.length; j++) {
            arr[L + j] = help[j];
        }
    }

}
