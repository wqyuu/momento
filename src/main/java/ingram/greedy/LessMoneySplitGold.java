package ingram.greedy;

import java.util.PriorityQueue;

/**
 * @Author qywu11
 * @Date 2023/1/3 17:02
 * @Version 1.0
 * 贪心策略  金币问题：用最少的金币分割金条  （哈夫曼树问题）
 */
public class LessMoneySplitGold {

    public static int lessMoney(int [] arr){
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }
        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1){
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }
        return sum;
    }

}
