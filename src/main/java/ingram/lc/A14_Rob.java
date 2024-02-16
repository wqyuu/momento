package ingram.lc;

/**
 * 198 打家劫舍
 */
public class A14_Rob {

    /**
     * 输入：[1,2,3,1]
     * 输出：4
     * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     * 偷窃到的最高金额 = 1 + 3 = 4 。
     *
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        int max = robRe(nums, 0, 0);
        return max;
    }


    public static int robRe(int[] nums, int index, int res) {
        if (index >= nums.length) {
            return res;
        }
        if (index == nums.length - 1) {
            return res + nums[index];
        }
        int t1 = robRe(nums, index + 2, res + nums[index]);
        int t2 = robRe(nums, index + 3, res + nums[index]);
        res = Math.max(t1, t2);
        return res;
    }


    public int robLeet(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int[] dp = new int[length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[length - 1];
    }

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1, 2, 3, 1}));
        System.out.println(rob(new int[]{2, 7, 9, 3, 1}));
    }
}
