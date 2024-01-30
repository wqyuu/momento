package ingram.DCodeRecord;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author qywu11
 * @Date 2024/1/16 18:01
 * @Version 1.0
 */
public class Array20240116 {
	
	// 977.有序数组的平方
	public int[] sortedSquares(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		int[] ans = new int[right + 1];
		int p = right;
		while(left <= right){
			if(Math.abs(nums[left]) > Math.abs(nums[right])){
				ans[p--] = nums[left] * nums[left];
				left ++;
			}else{
				ans[p--] = nums[right] * nums[right];
				right --;
			}
		}
		return ans;
    }


    // 76.最小数组
    // [1,2,3,7,1,8] 8
    public int minSubArrayLen(int s, int[] nums) {
        int left = 0;
        int total = 0;
        int min = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            total += nums[right];
            while (total>=s){
                min = Math.min(min,right - left + 1);
                total -= nums[left++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    static Map<Character,Integer> posMap = new HashMap<>();
    static Map<Character,Integer> oriMap = new HashMap<>();
    /**
     * 209
     * @param s [a,v,d,c,b,s,a,r,c,t,b,o,a,f,c,b,l,a,q,q,w,b,c]
     * @param t [a,b,c]
     * @return
     */
    public static String minWindow(String s, String t) {
        int min = Integer.MAX_VALUE;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < t.length(); i++) {
            char cur = t.charAt(i);
            oriMap.put(cur,oriMap.getOrDefault(cur,0)+1);
        }

        List<Character> characterList = new ArrayList<>();
        List<Integer> postList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(t.indexOf(cur)!=-1){
                characterList.add(cur);
                postList.add(i);
                posMap.put(cur,posMap.getOrDefault(cur,0)+1);
            }

            while (check()){
                int j = postList.get(0);
                StringBuffer w = new StringBuffer();
                int len = i - j + 1;
                if(len < min){
                    int start = j;
                    while (start <= i){
                        w.append(s.charAt(start++));
                    }
                    min = len;
                    sb = w;
                }
                posMap.put(characterList.get(0),posMap.getOrDefault(characterList.get(0),0)-1);
                postList.remove(postList.get(0));
                characterList.remove(characterList.get(0));
            }
        }

        return sb.toString();

    }

    private static boolean check(){
        boolean flag = true;
        for (Character oKey: oriMap.keySet()){
            int oNum = oriMap.get(oKey);
            if(posMap.containsKey(oKey)){
                int pNum = posMap.get(oKey);
                if(pNum<oNum){
                    flag = false;
                }
            }else {
                flag = false;
            }
        }
        return flag;
    }
}
