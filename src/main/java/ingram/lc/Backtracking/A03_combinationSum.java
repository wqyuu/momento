package ingram.lc.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 39. 组合总和
 * 中等
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
 * 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 */
public class A03_combinationSum {

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
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

        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }

        if(target < 0){
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            f(candidates,i,target - candidates[i],list,res);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{8,7,4,3},11));
//        System.out.println(combinationSum(new int[]{2,3,5},8));
    }
}
