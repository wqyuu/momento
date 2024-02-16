package tencent;

public class ComputeNum {

    /**
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * @param args
     */
    public static int cal(int nums[],int k){
        int res = -1;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == k){
                res = i;
            }
        }
        return res;
    }

    public static int cal2(int nums[],int k){
        int n = nums.length;
        int l = 0;

        while(l < n){
            int mid = (l + n) /2;
            if(nums[mid] == k){
                return mid;
            }else if(nums[mid] > k){
                n  = mid - 1;
            }else{
                l = mid + 1;
            }
        }

       return -1;
    }

    public static void main(String[] args) {
        int nums[] = new int[]{-1,0,3,5,9,12} ;
        int k = 9;
        int res = cal2(nums,k);
        System.out.println(res);
    }
}
