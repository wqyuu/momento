package ingram.lc.window;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 239. 滑动窗口最大值
 * 困难
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。
 * 滑动窗口每次只向右移动一位。返回 滑动窗口中的最大值 。
 *
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 */
public class A05_maxSlidingWindow {

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }

    public int[] maxSlidingWindowME(int[] nums, int k) {
        Deque<Integer> queue = new LinkedList<>();
        int[] array = new int[nums.length - k + 1];
        int j = 0;
        // [1,3,-1,-3,5,3,6,7], k = 3
        // [3,-1,-3]  -1
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peek()] <= nums[i]){
                queue.pollLast();
            }
            queue.addLast(i);
            if( i - queue.peekFirst()==k){
                queue.pollFirst();
            }

            if(i+1 >= k){
                array[j++] = nums[queue.peekFirst()];
            }

        }
        return array;
    }

    public static int[] getMaxWindow(int[] arr,int w){
        if( arr == null || w < 1 || arr.length < w){
            return null;
        }
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 窗口 R向右移动，弹出所有小于新数字的值
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]){
                qMax.pollLast();
            }
            // 加入新数字
            qMax.addLast(i);

            // 左侧过窗口，弹出左边首个数字
            if(w == i - qMax.peekFirst()){
                qMax.pollFirst();
            }
            // 位置大于窗口后，记录每个窗口最大数字到res中
            if(i >= w - 1){
                res[index ++] = arr[qMax.peekFirst()];
            }
        }
        return res;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,3,-1,-3,5,3,6,7};
        getMaxWindow(nums,3);

        int[] nums1 = new int[]{1,-1};
        getMaxWindow(nums1,1);
    }
}
