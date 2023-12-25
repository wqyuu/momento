package ingram.LC;

import java.util.*;

/**
 * @Author qywu11
 * @Date 2023/6/1 15:10
 * @Version 1.0
 */
public class A15_ThreeSum {

    static Map<Integer,Integer> map = new HashMap<>();

    public static List<List<Integer>> threeSum(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(map.containsKey(-(nums[i] + nums[j]))){
                    List<Integer> t = new ArrayList<>();
                    t.add(nums[i]);
                    t.add(nums[j]);
                    t.add(-(nums[i] + nums[j]));
                    res.add(t);
                }
            }
        }

        return res;
    }

    /**
     * 双指针、去重
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum2(int[] nums) {

        //结果放这里
        List<List<Integer>> result = new ArrayList<>();

        //对数组进行排序，方便使用双指针法
        Arrays.sort(nums);

        //对数组进行遍历，从小到大排序后，如果nums[i](第一个数都大于0)，不可能找到符合要求的结果，直接将result返回即可
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] > 0){
                return result;
            }

            //对a去重 即去重nums[i],a重复了直接跳过去,i>0在前面否则会空指针,直接continue 结束当前i的循环，程序将跳过当前迭代的剩余代码，直接进入下一次迭代。
            if(i>0 && nums[i] == nums[i-1] ){
                continue;
            }

            //双指针 定义左指针和右指针
            int left = i + 1;
            int right = nums.length - 1;

            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum > 0){
                    right --;
                }else if (sum < 0){
                    left ++;
                }else{
                    //Arrays.asList方法 用于将数组转换为 List(集合)
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));

                    //对b，c去重 即nums[left],nums[right]
                    while(left < right && nums[left] == nums[left+1]){
                        left++;
                    }
                    while(left < right && nums[right] == nums[right -1]){
                        right --;
                    }
                    //找到三元组后双指针继续向里面缩进继续查找
                    right --;
                    left++;
                }
            }
        }
        return result;

    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,-1,0,9,8,-6,3,2,5,-2};
        List<List<Integer>> res = threeSum(nums);
        res.forEach(System.out::println);
    }

}
