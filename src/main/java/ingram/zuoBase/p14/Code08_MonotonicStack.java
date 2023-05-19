package ingram.zuoBase.p14;

import java.util.Stack;

/**
 * @Author qywu11
 * @Date 2023/3/9 15:11
 * @Version 1.0
 *
 *给定一个整数数组arr，返回子数组中指标A最大的值。指标A定义如下：
 *
 * 对于每个子数组，如果该子数组中的最小值为min，那么指标A=min*sum，其中sum为子数组的累加和。
 *
 * 示例
 *
 * 输入：[3,1,6,4,5,2]
 * 输出：60
 * 解释：对于[6,4,5]这个子数组，min=4，sum=15，指标A=60，是所有子数组中最大的值。
 *
 * 思路
 *
 * 使用单调栈可以在O(n)的时间复杂度内解决该问题。
 *
 * 单调栈是一种常用的数据结构，它可以帮助我们在O(n)的时间复杂度内解决很多需要找到左边或右边第一个比当前元素小（或大）的问题。
 *
 * 具体思路如下：
 *
 * 维护一个单调递增的栈，用来存储数组元素的下标。
 * 遍历数组元素，如果当前元素比栈顶元素小，则弹出栈顶元素，并计算以该元素为最小值的子数组的指标A。
 * 计算指标A时，需要根据弹出元素的下标计算子数组的累加和，即sum = arr[i] * (i - stack[top-1] - 1) + sum(stack[top-1]+1, i)。
 * 计算每个子数组的指标A，并更新最大值。
 *
 *
 *
 */
public class Code08_MonotonicStack {

    public static void main(String[] args) {
        int a = max2(new int[]{3,1,6,4,5,2});
        System.out.println(a);
    }


    public static int maxA(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int n = arr.length;
        int[] sum = new int[n];  // 存储以每个位置为最小值的子数组的累加和
        int res = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                int idx = stack.pop();
                // 计算以arr[idx]为最小值的子数组的累加和
                int s = arr[idx] * (i - idx);
                if (idx > 0) {
                    s += sum[idx-1];
                }
                sum[i] += s;
                res = Math.max(res, arr[idx] * sum[i]);
            }
            stack.push(i);
        }
        // 清空剩余元素
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            int s = arr[idx] * (n - idx);
            if (idx > 0) {
                s += sum[idx-1];
            }
            sum[n-1] += s;
            res = Math.max(res, arr[idx] * sum[n-1]);
        }
        return res;
    }

    public static int max2(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];//先把sum求出来
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                // sums[i - 1] - sums[stack.peek()] 计算i-1元素能到达最左最右的累加和
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }//扫尾工作
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }
}
