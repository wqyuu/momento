package ingram.hw.sin_stack;

import java.util.Stack;

class A01_dailyTemperatures {
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
}
