package ingram.lc.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 40. 组合总和 II
 * 中等
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 输出:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 */
public class A04_combinationSum2 {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        f(candidates,0,target,new ArrayList<>(),res);
        return res;
    }

    /**
     *  * 输入：candidates = [2,3,6,7], target = 7
     *  * 输出：[[2,2,3],[7]]
     * @param candidates
     * @param index
     * @param target
     * @param list
     * @param res
     */
    public static void f(int[] candidates, int index,int target,List<Integer> list,List<List<Integer>> res){

        if(target == 0 && !res.contains(list)){
            res.add(new ArrayList<>(list));
            return;
        }

        if(target < 0){
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i-1]) {
                continue;
            }
            list.add(candidates[i]);
            f(candidates,i+1,target - candidates[i],list,res);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{10,1,2,7,6,1,5},8));
//        System.out.println(combinationSum(new int[]{2,3,5},8));
    }
}
