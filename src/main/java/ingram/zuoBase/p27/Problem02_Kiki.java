package ingram.zuoBase.p27;

/**
 * 需要自己找边界条件的递归
 * 普通递归找不完，找平凡解增加限制
 *
 * 分析出平凡解：如果我通过只点赞使 start 到达 end，需要 A C币。那么最优解不能大于 A
 * 分析出特殊限制：start 的值可以大于 end 的值，然后通过私聊使 start 退回到 end，但是 start 不能大于 2 * end
 * ————————————————
 *
 * 题目：
 * CC 里面有一个土豪很喜欢一位女直播 kiki 唱歌，平时就经常给她点赞、送礼、私聊。最近 CC 直播平台在举行中秋之星直播唱歌比赛，假设一开始该女主播的初始人气值为 start，能够晋升下一轮人气需要刚好达到 end，土豪给主播增加人气可以采取一下三种方法：
 *
 * 点赞；花费 x C币，人气 + 2
 * 送礼；花费 y C 币，人气 * 2
 * 私聊；花费 z C币，人气 - 2
 * 其中 end 远大于 start 且 end 为偶数，请写一个程序帮助土豪计算一下，最少花费多少 C 币就能帮助该主播 kiki 将人气刚好达到 end，从而能够晋级下一轮？
 * 限制 0 < x , y , z < = 10000 ; 0 < s t a r t , e n d < 1000000 0<x,y,z<=10000 ;\quad 0<start,end<10000000<x,y,z<=10000;0<start,end<1000000
 * 【例如】
 * 输入：start = 3，end = 100，x = 1，y = 2，z = 6
 * 输出：6
 * ————————————————
 *
 *
 */
public class Problem02_Kiki {

    public static int minCcoins1(int add, int times, int del, int start, int end) {
        if (start > end) {
            return -1;
        }
        return process(0, end, add, times, del, start, end * 2, ((end - start) / 2) * add);
    }

    public static int process(int pre, int aim, int add, int times, int del, int cur, int limitAim, int limitCoin) {
        if (pre > limitCoin) {
            return Integer.MAX_VALUE;
        }
        if (cur < 0) {
            return Integer.MAX_VALUE;
        }
        if (cur > limitAim) {
            return Integer.MAX_VALUE;
        }
        if (aim == cur) {
            return pre;
        }
        int min = Integer.MAX_VALUE;
        int p1 = process(pre + add, aim, add, times, del, cur + 2, limitAim, limitCoin);
        if (p1 != Integer.MAX_VALUE) {
            min = p1;
        }
        int p2 = process(pre + del, aim, add, times, del, cur - 2, limitAim, limitCoin);
        if (p2 != Integer.MAX_VALUE) {
            min = Math.min(min, p2);
        }
        if ((aim & 1) == 0) {
            int p3 = process(pre + times, aim, add, times, del, cur * 2, limitAim, limitCoin);
            if (p3 != Integer.MAX_VALUE) {
                min = Math.min(min, p3);
            }
        }
        return min;
    }

    public static int minCcoins2(int add, int times, int del, int start, int end) {
        if (start > end) {
            return -1;
        }
        int limitCoin = ((end - start) / 2) * add;
        int limitAim = end * 2;
        int[][] dp = new int[limitCoin + 1][limitAim + 1];
        for (int pre = 0; pre <= limitCoin; pre++) {
            for (int aim = 0; aim <= limitAim; aim++) {
                if (aim == start) {
                    dp[pre][aim] = pre;
                } else {
                    dp[pre][aim] = Integer.MAX_VALUE;
                }
            }
        }
        for (int pre = limitCoin; pre >= 0; pre--) {
            for (int aim = 0; aim <= limitAim; aim++) {
                if (aim + 2 <= limitAim && pre + add <= limitCoin) {
                    dp[pre][aim] = Math.min(dp[pre][aim], dp[pre + add][aim + 2]);
                }
                if (aim - 2 >= 0 && pre + del <= limitCoin) {
                    dp[pre][aim] = Math.min(dp[pre][aim], dp[pre + del][aim - 2]);
                }
                if (aim * 2 <= limitAim && pre + times <= limitCoin) {
                    dp[pre][aim] = Math.min(dp[pre][aim], dp[pre + times][aim * 2]);
                }
            }
        }
        return dp[0][end];
    }

    public static void main(String[] args) {
        int add = 6;
        int times = 5;
        int del = 1;
        int start = 10;
        int end = 30;
        System.out.println(minCcoins1(add, times, del, start, end));
        System.out.println(minCcoins2(add, times, del, start, end));
    }
}