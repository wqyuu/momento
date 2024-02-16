package ingram.lc;

public class A09_findKthLargest {

    public static int findKthLargest(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {
            headInsert(nums,i);
        }
        int heapSize = nums.length;
        swap(nums,0, --heapSize);
        while (heapSize > 0){
            heapIfy(nums,0,heapSize );
            swap(nums,0, --heapSize);
        }
        return nums[nums.length - k];
    }

    public static void headInsert(int[] nums,int index){
        while (nums[index] > nums[(index - 1)/2]){
            swap(nums,index,(index - 1)/2);
            index = (index - 1)/2;
        }
    }


    // 0
    // 1 2
    // 34 56
    public static void heapIfy(int[] nums,int index,int heapSize){
        int left = 2*index + 1;
        while (left < heapSize){

            int max = left + 1 < heapSize && nums[left + 1] > nums[left]?left + 1:left;
            max = nums[max] > nums[index] ? max : index;
            // 父和孩子一样
            if(max == index){
                break;
            }
            swap(nums,max,index);
            index = max;
            left = 2*index + 1;
        }
    }

    public static void swap(int[] nums,int a,int b){
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{1,2,7,8,20,38,19,20},2));
    }


}
