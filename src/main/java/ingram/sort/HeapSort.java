package ingram.sort;

/**
 * @Author qywu11
 * @Date 2022/7/14 9:18
 * @Version 1.0
 * 堆排序
 * 时间复杂度O(N*logN)  空间复杂度O(1)  稳定性  N
 */
public class HeapSort {

    // todo
    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        // 9 2 7 1 3 5 8 0
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }
        int heapSize = arr.length;
        // 根与最后节点交换，并截取size-- 0 2 7 1 3 5 8 | 9
        swap(arr,0,--heapSize);
        while (heapSize>0){
            // 根节点交换到下面，把大数交换上来 8 2 3 4 5 1 0
            heapFiy(arr,0,heapSize);
            // 根与最后节点交换，并截取size-- 0 2 3 4 5 1 | 8
            swap(arr,0,--heapSize);
        }
    }

    public static void heapInsert(int[]arr , int index){
        // 当前数大于父位置数
        while(arr[index] > arr[(index - 1)/2]){
            swap(arr,index,(index - 1)/2);
            index = (index - 1)/2;
        }
    }

    public static void  swap(int[]arr , int a,int b){
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    /**
     *
     * @param index 父节点
     * @param arr
     * @param heapSize
     */
    public static void heapFiy(int[] arr,int index,int heapSize){

        int left = index*2 + 1;

        while (left < heapSize){ // 下方还有孩子，左孩子不越界，代表右孩子也不会越界，下标比右小

            // 左右孩子取最大
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left]?left + 1:left;
            // 较大孩子，父，取最大
            largest = arr[largest] > arr[index] ? largest : index;
            // 父和孩子一样
            if(largest == index){
                break;
            }
            // swap 较大孩子和父做交换
            swap(arr,largest,index);
            index = largest;
            // 继续往下走
            left = index * 2 + 1;
        }
    }
}
