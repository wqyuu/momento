package ingram.DCodeRecord;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author qywu11
 * @Date 2024/1/30 14:18
 * @Version 1.0
 * 贪心
 */
public class Greedy20240130 {

    /**
     * 455.分发饼干
     * 对每个孩子 i，都有一个胃口值  g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
     * 示例  1:
     * 输入: g = [1,2,3], s = [1,1]
     * 输出: 1 解释:你有三个孩子和两块小饼干，3 个孩子的胃口值分别是：1,2,3。虽然你有两块小饼干，由于他们的尺寸都是 1，你只能让胃口值是 1 的孩子满足。所以你应该输出 1。
     * @param g
     * @param s
     * @return
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g); // 1,2
        Arrays.sort(s); // 1,2,3
        int j = 0;
        int count = 0;
        for (int i = 0; i < s.length; i++) {
            if(j < s.length && s[i] >= g[j]){
                j ++;
                count ++;
            }
        }

        return count;
    }

    /**
     * 53. 最大子序和
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * 示例:
     * 输入: [-2,1,-3,4,-1,2,1,-5,4]
     * 输出: 6
     * 解释:  连续子数组  [4,-1,2,1] 的和最大，为  6
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int max = nums[0];
        int total = 0;
        for (int num : nums) {
            total = (total + num) > 0 ? (total + num) : num;
            max = Math.max(total, max);
        }
        return max;
    }

    /**
     * 376. 摆动序列
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为摆动序列。第一个差（如果存在的话）可能是正数或负数。少于两个元素的序列也是摆动序列。
     * 例如， [1,7,4,9,2,5] 是一个摆动序列，因为差值 (6,-3,5,-7,3)  是正负交替出现的。相反, [1,4,7,2,5]  和  [1,7,4,5,5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * 给定一个整数序列，返回作为摆动序列的最长子序列的长度。 通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序。
     * 示例 1:
     * 输入: [1,7,4,9,2,5]
     * 输出: 6
     * 解释: 整个序列均为摆动序列。
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {

        if (nums.length <= 1) {
            return nums.length;
        }
        //当前差值
        int curDiff = 0;
        //上一个差值
        int preDiff = 0;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            //得到当前差值
            curDiff = nums[i] - nums[i - 1];
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始时的preDiff
            if ((curDiff > 0 && preDiff <= 0) || (curDiff < 0 && preDiff >= 0)) {
                count++;
                preDiff = curDiff;
            }
        }
        return count;
    }

    // 122.买卖股票的最佳时机 II
    public static int maxProfit(int[] prices) {
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            max += Math.max((prices[i] - prices[i-1]), 0);
        }
        return max;
    }

    // #1005.K次取反后最大化的数组和
    public static int largestSumAfterKNegations(int[] nums, int k) {

//        for (int i = 0; i < nums.length; i++) {
//            heapInsert(nums,i);
//        }
        for (int i = 0; i < k; i++) {
            Arrays.sort(nums);
            for (int j = 0; j < nums.length; j++) {
                heapInsert(nums,j);
            }
            nums[0] = -nums[0];
        }
        int result = 0;
        for (int num : nums) {
            result += num;
        }
        return result;
    }
    public static void heapInsert(int[] nums,int index){
        while (nums[index] < nums[(index - 1)/2]){
            int tmp = nums[index];
            nums[index] = nums[(index - 1)/2];
            nums[(index - 1)/2] = tmp;
            index = (index - 1)/2;
        }
    }


    /**
     * 55. 跳跃游戏
     * 给定一个非负整数数组，你最初位于数组的第一个位置。
     * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
     * 判断你是否能够到达最后一个位置。
     *
     * 示例  1:
     * 输入：nums = [2,3,1,1,2]
     * 输出：true
     * 解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
     * nums = [3,2,1,0,4] false
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        return can(nums,0);
    }

    public static boolean can(int[] nums,int cur){
        if(nums[cur] + cur + 1 >= nums.length){
            return true;
        }
        for (int i = 1; i <= nums[cur]; i++) {
            cur = cur + i;
            if(can(nums,cur)){
                return true;
            }
        }
        return false;
    }


    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            while (list.contains(s.charAt(i))){
                list.remove(0);
            }
            list.add(s.charAt(i));
            max = Math.max(max,list.size());
        }
        return max;
    }
    // ababc

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String ans = strs[0];
        for (int i = 1; i < strs.length; i++) {
            int j = 0;
            for (; j < ans.length() && j < strs[i].length(); j++) {
                if (ans.charAt(j) != strs[i].charAt(j)) break;
            }
            ans = ans.substring(0, j);
            if (ans.equals("")) return ans;
        }
        return ans;
    }

}
