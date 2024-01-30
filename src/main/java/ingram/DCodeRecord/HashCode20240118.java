package ingram.DCodeRecord;
import java.util.*;
class HashCode20240118 {
        // 242.字母异位词
    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
     * 示例 1:
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {

        Map<Character,Integer> map1 = new HashMap<>();
        Map<Character,Integer> map2 = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            map1.put(s.charAt(i),map1.getOrDefault(s.charAt(i),0)+1);
        }
        for (int i = 0; i < t.length(); i++) {
            map2.put(t.charAt(i),map2.getOrDefault(t.charAt(i),0)+1);
        }

        for (Character k:map1.keySet()){
            if(map2.containsKey(k)){
                if(!Objects.equals(map2.get(k), map1.get(k))){
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;
    }

    /**
     * 349. 两个数组的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        for (int j : nums1) {
            s1.add(j);
        }
        for (int j : nums2) {
            s2.add(j);
        }
        int n = Math.min(s1.size(), s2.size());
        int k = 0;
        int[] res = new int[n];
        for (Integer i:s1){
            if(s2.contains(i)){
                res[k++] = i;
            }
        }

        return Arrays.copyOf(res,k);
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList();
        int len = nums.length;
        if(nums == null || len < 3) return ans;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len ; i++) {
            if(nums[i] > 0) break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            if(i > 0 && nums[i] == nums[i-1]) continue; // 去重
            int L = i+1;
            int R = len-1;
            while(L < R){
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0){
                    ans.add(Arrays.asList(nums[i],nums[L],nums[R]));
                    while (L<R && nums[L] == nums[L+1]) L++; // 去重
                    while (L<R && nums[R] == nums[R-1]) R--; // 去重
                    L++;
                    R--;
                }
                else if (sum < 0) L++;
                else if (sum > 0) R--;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("jjooui","iouojj"));
        System.out.println(isAnagram("jjooui","iouojij"));
        System.out.println(Arrays.toString(intersection(new int[]{1, 2, 10, 29, 100}, new int[]{1, 3, 1})));
        System.out.println(Arrays.toString(intersection(new int[]{1, 2, 2}, new int[]{1, 2, 1})));
    }
}
