package ingram.zuoBase.p21;

/**
 * @Author qywu11
 * @Date 2023/4/20 16:12
 * @Version 1.0
 * 动态规划
 * N个节点能组成多少种树，要求不重复
 */
public class Problem06_UniqueBST {

    /**
     * 递归实现
     * @param n
     * @return
     */
    public static int process(int n){
        if(n < 0){
            return 0;
        }
        if(n == 0){
            return 1;
        }
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }

        int res = 0;
        for (int leftNum = 0; leftNum <= n - 1; leftNum++) {
            int leftWays = process(leftNum);
            int rightWays = process(n - 1- leftNum);
            res += leftWays * rightWays;
        }
        return res;
    }

    /**
     * 动态规划实现
     * @param n
     * @return
     */
    public static int numTrees(int n){
        if(n < 2){
            return 1;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < n + 1; i++) {  // 左树节点个数
            for (int j = 0; j <= i -1 ; j++) { // 右树剩余的节点个数
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }
}
