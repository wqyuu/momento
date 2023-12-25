package ingram.zuoBase.p11;
/**
 * @Author qywu11
 * @Date 2023/12/4 10:06
 * @Version 1.0
 */
public class BinarySort {

    public static int searchInsert(int[] nums, int target) {
        int i = 0 ;
        int j = nums.length;

        while (i<j){
            int mid = (j - i)/2 +i;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target){
                j = mid;
            }else{
                i = mid + 1;
            }
        }

        return i;

    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int i = 0;
        int j = matrix.length;
        while (i < j){
            int mid = (j - i)/2 + i;
            int start = matrix[mid][0];
            int end = matrix[mid][matrix[mid].length - 1];
            if(target >= start && target <= end){
                int s = 0;
                int e = matrix[mid].length;
                while (s<e){
                    int midv = (e - s)/2 +s;
                    if(matrix[mid][midv] == target) {
                        return true;
                    } else if(matrix[mid][midv] > target){
                        e = midv;
                    }else{
                        s = midv + 1;
                    }
                }
                return false;
            }else if(target > end){
                i = mid + 1;
            }else{
                j = mid;
            }
        }

        return false;
    }

    /**
     * nums = [5,7,7,8,8,10], target = 8
     * 输出：[3,4]
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int s = 0;
        int e = nums.length;
        while (s<e){
            int midv = (e - s)/2 +s;
            if(nums[midv] == target) {
                int[] res = new int[2];
                res[0] = midv;
                res[1] = midv;
                int i = midv-1;
                while (i>=0 && nums[i] == target){
                    res[0] = i;
                    i --;
                }
                int j = midv+1;
                while (j< nums.length && nums[j] == target){
                    res[1] = j;
                    j ++;
                }
                return res;
            } else if(nums[midv] > target){
                e = midv;
            }else{
                s = midv + 1;
            }
        }
        return new int[]{-1,-1};
    }

	    /**
     * 输入：nums = [4,5,6,7,0,1,2], target = 0
     * 输出：4
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;

    }

    // [4,5,6,7,0,1,2]
    // [5,6,7,0,1,2,4]
    // [6,7,0,1,2,4,5]
    // [7,0,1,2,4,5,6]
    // [0,1,2,4,5,6,7]
    // [1,2,4,5,6,7,0]
    public static int findMin(int[] nums) {

        int j = nums.length - 1;
        int i = 0;
        while (i < j){
            int mid = (j - i)/2 + i;
            if(nums[j] > nums[mid] ){
                j = mid;
            }else{
                i = mid + 1;
            }
        }

        return nums[i];
    }
    public static void main(String[] args) {
//        searchInsert(new int[]{1,3,5,6},2);

//        searchMatrix(new int[][]{{1,2,3},{4,5,6}},5);
//        searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}},3);
//        searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}},13);
//        searchMatrix(new int[][]{{1}},1);
        searchRange(new int[]{5,7,7,8,8,10},8);
    }
}
