package ingram.zuoBase.p14;

import java.util.Stack;

/**
 * @Author qywu11
 * @Date 2023/3/10 18:08
 * @Version 1.0
 *
 * 给定数组 arr，其中 arr [i] 表示 1 为底，高为 arr [i] 的矩形，则数组 arr 可以表示一个柱状图。这里求该柱状图所包含的矩形中，面积最大的矩形。
 * 例如：int arr [] = {2, 4, 7, 3, 5, 4, 6, 9, 4};
 * 在该柱状图中，面积最大矩形是 8 * 3 = 24；
 */
public class ArrayMaxArea {

    public static void main(String[] args) {

        int[] arr = {2, 4, 7, 3, 5, 4, 6, 9, 4};


        Stack<Integer> stack = new Stack<>();

        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]){
                Integer idx = stack.pop();
                Integer forwardX = stack.peek();
                int distance = i - forwardX - 1;
                int idxArea = arr[idx] * distance;
                max = Math.max(max,idxArea);
            }

            stack.push(i);
        }

        while (!stack.isEmpty()){
            Integer idx = stack.pop();
            int forwardX = 0;
            if(!stack.isEmpty()){
                forwardX = stack.peek();
            }
            int distance = arr.length  - forwardX - 1;
            int idxArea = arr[idx] * distance;
            max = Math.max(max,idxArea);
        }

        System.out.println(max);

    }
}
