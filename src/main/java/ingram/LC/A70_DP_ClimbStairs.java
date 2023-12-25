package ingram.LC;

class A70_DP_ClimbStairs {

    public static int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; ++i) {
            p = q; 
            q = r; 
            r = p + q;
        }
        return r;
    }

    public static int climbStairs2(int n) {
        if(n < 0){
            return 0;
        }
        if(n == 0){
            return 1;
        }
        int a1 = climbStairs2(n-1);
        int a2 = climbStairs2(n-2);
        return a1 + a2;
    }

    public static int climbStairs3(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return  dp[n];
    }

    public static void main(String[] args) {
       int x1 = climbStairs2(5);
       int x2 = climbStairs(5);
       int x3 = climbStairs3(5);
       System.out.println(x1);
       System.out.println(x2);
       System.out.println(x3);
    }
}
