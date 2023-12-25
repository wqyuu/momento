package ingram.sort;

/**
 * @Author qywu11
 * @Date 2022/7/14 9:17
 * @Version 1.0
 * 小和问题 转换思路 一个数右边多少数比它大
 *
 * 在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组的小和。
 * 例子：[1,3,4,2,5]
 * 1左边比1小的数，没有；
 * 3左边比3小的数，1；
 * 4左边比4小的数，1、3；
 * 2左边比2小的数，1；
 * 5左边比5小的数，1、3、4、2；
 * 所以小和为1+1+3+1+1+3+4+2=16
 *
 * 归并排序扩展解决问题
 */
public class MergeSort_SmallNum {


        public static void main(String[] args) {
            int[] arr = new int[]{1,2,5,3,4,2,8,5};
            int res = process(0,arr.length-1,arr);
            System.out.println(res);
        }

        public static int process(int l,int r,int[] arr){
            if(l == r){
                return 0;
            }

            int mid = l + ((r-l) >> 1);
            return process(l,mid,arr) + process(mid+1,r,arr) + merge(l,r,mid,arr);
        }

        public static int merge(int l,int r,int m,int[] arr){

            int p1 = l;
            int p2 = m + 1;
            int num = 0;
            int i = 0;
            int[] helps = new int[r-l+1];
            while (p1 <=m && p2 <= r){
                System.out.println("L:"+l+" R:"+r+" p1:"+p1+" p2:"+p2+" num:"+num+" i:"+i);
                num += arr[p1] < arr[p2] ?(r-p2+1)*arr[p1]:0; // 只有跨左右两组时才会产生小和，不会漏也不会重算
                helps[i++] = arr[p1] < arr[p2]?arr[p1++]:arr[p2++]; // 只有左比右小才拷贝左组，否则右大于等于左，都拷贝右
            }
            while (p1 <= m){
                helps[i++] = arr[p1++];
            }
            while (p2 <= r){
                helps[i++] = arr[p2++];
            }
            for (int j = 0; j < helps.length; j++) {
                arr[l+j] = helps[j];
            }

            return num;
        }



}
