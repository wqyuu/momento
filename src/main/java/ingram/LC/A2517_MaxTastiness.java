package ingram.LC;

import java.util.Arrays;

/**
 * 给你一个正整数数组 price ，其中 price[i] 表示第 i 类糖果的价格，另给你一个正整数 k 。
 *
 * 商店组合 k 类 不同 糖果打包成礼盒出售。礼盒的 甜蜜度 是礼盒中任意两种糖果 价格 绝对差的最小值。
 *
 * 返回礼盒的 最大 甜蜜度。
 *
 * 示例 1：
 *
 * 输入：price = [13,5,1,8,21,2], k = 3
 * 输出：8
 * 解释：选出价格分别为 [13,5,21] 的三类糖果。
 * 礼盒的甜蜜度为 min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8 。
 * 可以证明能够取得的最大甜蜜度就是 8 。
 * 示例 2：
 *
 * 输入：price = [1,3,1], k = 2
 * 输出：2
 * 解释：选出价格分别为 [1,3] 的两类糖果。
 * 礼盒的甜蜜度为 min(|1 - 3|) = min(2) = 2 。
 * 可以证明能够取得的最大甜蜜度就是 2 。
 *
 *
 * -- ---- -----
 *
 * 贪心 + 二分查找
 *
 * 礼盒的甜蜜度是 k 种不同的糖果中，任意两种糖果价格差的绝对值的最小值。计算礼盒的甜蜜度时，可以先将 k 种糖果按照价格排序，然后计算相邻的价格差的绝对值，然后取出最小值。
 *
 * 要求甜蜜度的最大值，可以采用二分查找的方法。先假设一个甜蜜度 mid，然后尝试在排好序的 price 中找出 k 种糖果，并且任意两种相邻的价格差绝对值都大于 mid。如果可以找到这样的 k 种糖果，则说明可能存在更大的甜蜜度，需要修改二分查找的下边界；如果找不到这样的 k 种糖果，则说明最大的甜蜜度只可能更小，需要修改二分查找的上边界。
 *
 * 在假设一个甜蜜度 mid 后，在排好序的 price 中找 k 种糖果时，需要用到贪心的算法。即从小到大遍历 price 的元素，如果当前糖果的价格比上一个选中的糖果的价格的差大于 mid，则选中当前糖果，否则继续考察下一个糖果。
 *
 * 二分查找起始时，下边界为 0，上边界为 price 的最大值与最小值之差。二分查找结束时，即可得到最大甜蜜度。
 */
class A2517_MaxTastiness {


    /**
     * 13,5,1,8,21,2
     * @param price
     * @param k
     * @return
     */
    public static int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int left = 0, right = price[price.length - 1] - price[0];
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (check(price, k, mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
        /**
         *  1,2,5,8,13,21
         *  left = 0 r=21 mid = 10    1,13
         *  left=0 r=20 mid=10  1,13
         *  left=0 r=18 mid=9 1,13
         *  left=0 r=16 mid=8 1,13,21 *
         *  left=8 r=16 mid=12 1,13
         *  left=8 r=15 mid=12 1,13
         *  left=8 r=14 mid=11 1,13
         *  left=8 r=8 mid=9 1,13
         *
         *
         */
    }

    public  static boolean check(int[] price, int k, int tastiness) {
        int prev = Integer.MIN_VALUE / 2;
        int cnt = 0;
        for (int p : price) {
            if (p - prev >= tastiness) {
                cnt++;
                prev = p;
            }
        }
        return cnt >= k;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{13,5,1,8,21,2};
        int k = maximumTastiness(arr,3);
        System.out.println(k);
    }
}
