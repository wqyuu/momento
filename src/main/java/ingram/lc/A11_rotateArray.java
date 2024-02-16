package ingram.lc;

/**
 * 189. 轮转数组
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 * 示例 1:
 *
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 */
public class A11_rotateArray {


    public void rotate(int[] nums, int k) {
        if(nums.length <= 1){
            return;
        }
        if(k>nums.length){
            k = k - nums.length;
        }
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
        }
        int n = nums.length;
        // i~n
        int i = n - k;
        // 0 ~ j
        int j = n - k - 1;

        int z = 0;
        while (i < n){
            nums[z++] = res[i];
            i ++;
        }
        int s = 0;
        while (s <= j){
            nums[z++] = res[s];
            s ++;
        }
    }
}
