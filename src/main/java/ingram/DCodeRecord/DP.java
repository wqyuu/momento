package ingram.DCodeRecord;

/**
 * @Author qywu11
 * @Date 2024/2/7 17:10
 * @Version 1.0
 */
public class DP {

    /**
     * 70. 爬楼梯
     * 简单
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     *
     * 示例 1：
     * 输入：n = 2
     * 输出：2
     * 解释：有两种方法可以爬到楼顶。
     * 1. 1 阶 + 1 阶
     * 2. 2 阶
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int res = 0;
        return f(n,res);
    }

    public  int f(int n,int res) {
        if(n < 0){
            return res;
        }
        if(n == 0){
            res++;
            return res;
        }
        return f(n-2,res) + f(n-1,res);
    }


    /**
     * 198. 打家劫舍
     * 中等
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
     *
     * 示例 1：
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     *      偷窃到的最高金额 = 1 + 3 = 4 。
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int[] res = new int[nums.length+1];
        res[0] = 0 ;
        res[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            res[i] =  Math.max(res[i - 1],nums[i - 1] + res[i - 2]);
        }
        return res[nums.length];
    }
}
