package ingram.lc.array;

/**
 * 238. 除自身以外数组的乘积
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 * 示例 2:
 *
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 */
public class A12_productExceptSelf {

    public static  int[] productExceptSelf(int[] nums) {

        int[] res = new int[nums.length];
        int[] pre = new int[nums.length];
        int[] post = new int[nums.length];

        pre[0] = 1;// 1 1 2 6
        for (int i = 1; i < nums.length; i++) {
            pre[i] = pre[i - 1] * nums[i-1];
        }

        post[nums.length-1] = 1;
        //  24 12 4 1
        for (int i = nums.length-2; i >= 0; i--) {
            post[i] = post[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
            res[i] = pre[i] * post[i];
        }

        return res;

    }

    public static void main(String[] args) {
        // -1,1,0,-3,3
        productExceptSelf(new int[]{-1,1,0,-3,3});
    }

}
