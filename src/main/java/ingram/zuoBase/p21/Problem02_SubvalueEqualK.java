package ingram.zuoBase.p21;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @Author qywu11
 * @Date 2023/4/20 16:23
 * @Version 1.0
 * 查找相差距离为K的两个数组合有多少个，例如
 * [3,2,5,7,0,0] k=2
 * 找到差值为2的数据
 * 去重 (0,2) (3,5) (5,7)
 *
 * Hash方法
 */
public class Problem02_SubvalueEqualK {
    public static List<List<Integer>> allPair(int[] arr,int k){
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (Integer cur:set){
            if(set.contains(cur + k)){
                res.add(Arrays.asList(cur,cur + k));
            }
        }
        return res;
    }
}
