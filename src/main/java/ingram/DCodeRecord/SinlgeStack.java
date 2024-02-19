package ingram.DCodeRecord;

import java.util.Stack;

/**
 * @Author qywu11
 * @Date 2024/2/7 16:51
 * @Version 1.0
 */
public class SinlgeStack {

    /**
     * 739. 每日温度
     * 中等
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，
     * 下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 示例 1:
     * 输入: temperatures = [73,74,75,71,69,72,76,73]
     * 输出: [1,1,4,2,1,1,0,0]
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0 ; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]){
                int p = stack.pop();
                if(!stack.isEmpty()){
                    int pre = stack.peek();
                    res[p] = pre - p;
                }else {
                    res[p] = 0;
                    // 0
                }
            }

            stack.push(i);
        }
        while (!stack.isEmpty()){
            int p = stack.pop();
            if(!stack.isEmpty()){
                int pre = stack.peek();
                res[p] = pre - p;
            }else {
                res[p] = 0;
            }
        }

        return res;
    }

    /**
     * 84. 柱状图中最大的矩形
     * 困难
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     *
     * 示例 1:
     * 输入：heights = [2,1,5,6,2,3]
     * 输出：10
     * 解释：最大的矩形为图中红色区域，面积为 10
     * @param nums
     * @return
     */
    public int largestRectangleArea(int[] nums) {
        Stack<Integer> stack = new Stack<>(); //设置一个从小到大单调栈
        int left = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                int cur = stack.pop();
                left = stack.isEmpty() ? 0 : stack.peek() + 1;
                //左边界为 stack.peek() + 1，但要注意空栈的处理
                max = Math.max(max, nums[cur] * (i - left));
                //右边界为当前 i ！
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            left = stack.isEmpty() ? 0 : stack.peek() + 1;
            max = Math.max(max, nums[cur] * (nums.length - left));
            //右边界此时为数组的最右端
        }
        return max;
    }
}
