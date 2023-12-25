package ingram.LC;

class A198_DP_Rob {


    // 1,2,8,3,10
    // 100,2,1,3,2,1,90,10,5
    public static int rob(int[] nums) {
        return process(nums,0,0);
    }


    public static int process(int[] nums,int index,int res) {

        if(index > nums.length - 1){
            return res;
        }

//        System.out.println(nums[index]);

        if(index == nums.length - 1 || index == nums.length - 2){
            return res + nums[index];
        }

        res += nums[index];
        int r1 = process(nums,index + 2,res);
        int r2 = process(nums,index + 3,res);
        res = Math.max(r1,r2);

        return res ;
    }



    public static int rob1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }

        int N = nums.length;
        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int k = 2; k <= N; k++) {
            dp[k] = Math.max(dp[k-1], nums[k-1] + dp[k-2]);
        }        return dp[N];
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,1,6,8,10,2};
        int res1 = rob(arr);
        int res2 = rob1(arr);
        System.out.println(res1);
        System.out.println(res2);
    }

}