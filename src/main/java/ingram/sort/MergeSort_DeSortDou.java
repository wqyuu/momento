package ingram.sort;

/**
 * @Author qywu11
 * @Date 2022/7/14 9:17
 * @Version 1.0
 * 逆序对问题
 * 归并排序扩展解决问题 -- 每年必有
 * <p>
 * 在一个数组中，左边的数如果比右边的数大，则折两个数构成一个逆序对，请打印逆序对个数。
 * <p>
 * 分析
 * 逆序对问题的实质是记录左比右大的次数。与小和问题相比，只需要记录左侧较大数当前位置到左侧最终位置的个数即可。
 */
public class MergeSort_DeSortDou {


    /**
     * 有重复数据时有问题 后面数指针向后挪导致记录不上，缺少记录
     *
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 5, 3, 4, 2, 8, 5};// [5,3] [5,2] [5,4] [4,2] [8,5] [3,2]
        int res = process(0, arr.length - 1, arr);
        System.out.println(res);

        for (int j : arr) {
            System.out.print(j + "-");
        }
    }

    public static int process(int l, int r, int[] arr) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);

        return process(l, mid, arr) + process(mid + 1, r, arr) + merge(l, r, mid, arr);
    }

    /**
     * [1,2,7,4,7,0]   2
     *
     * @param l
     * @param r
     * @param m
     * @param arr
     * @return
     */
    public static int merge(int l, int r, int m, int[] arr) {

        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int[] help = new int[r - l + 1];
        int n = 0;
        while (p1 <= m && p2 <= r) {
            if (arr[p1] > arr[p2]) {
                System.out.println(p1 + "#" + p2 + ": " + arr[p1] + "-" + arr[p2]);
//                    n ++;
                n += m - p1 + 1; // 左边都大于右边，统计数量
                help[i++] = arr[p2++];
            } else {
                help[i++] = arr[p1++];
            }// 1,2,3,5,  2,4,5,8        -> 1,2,2,3,
//                help[i ++] = arr[p1] <= arr[p2] ? arr[p1 ++] : arr[p2 ++];
        }
        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }

        return n;

    }


}
