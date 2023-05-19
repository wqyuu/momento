package ingram.zuoBase.p20;

/**
 * @Author qywu11
 * @Date 2023/4/10 16:27
 * @Version 1.0
 */
public class Problem01_CordCoverMaxPoint {

    // 长度为L的绳子最多覆盖几个点，保证arr有序  1,2,3,4,5,7,8,10
    public static int maxPoint(int[] arr,int L) {

        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr,i,arr[i]-L);
            res = Math.max(res,i - nearest + 1);
        }
        return res;
    }
    
    // 在arr[0,..R]范围上，找到满足>=value的最左位置
    // 二分法
    public static int nearestIndex(int[] arr,int R,int value) {

        int L = 0;
        int index = R;
        while (L < R){
            int mid = L + ((R - L) >>1);
            if(arr[mid] >= value){
                index = mid;
                R = mid - 1;
            }else {
                L = mid + 1;
            }
        }
        return index;
    }
    
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,7,8,10};
        int res = maxPoint(arr,5);
        System.out.println(res);
    }
    
}
