package ingram.lc.twoPoint;

/**
 * 283. 移动零
 *
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 * 示例 1:
 *
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 */
public class A05_MoveZeroes {

    public void moveZeroes(int[] nums) {

        // 4,9,29,30,55,90,100,55,90,100
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0){
                nums[j++] = nums[i];
            }
        }
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }














    public void moveZeroes20231217(int[] nums) {

        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=0){
                nums[j++] = nums[i];
            }
        }
        while (j<nums.length){
            nums[j++] = 0;
        }

    }
}
