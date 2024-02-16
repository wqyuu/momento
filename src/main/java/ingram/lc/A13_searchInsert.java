package ingram.lc;

public class A13_searchInsert {

    public static int searchInsert(int[] nums, int target) {

        int j = nums.length ;
        int i = 0;
        while (i<j){
            int mid = (j - i)/2 + i;
            if(target <= nums[mid]){
                j = mid;
            }else{
                i = mid + 1;
            }
        }

        return i;
    }

    public static void main(String[] args) {
        searchInsert(new int[]{1,3,5,6},2);
    }
}
