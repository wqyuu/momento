package ingram.lc.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 *
 * 你可以按 任何顺序 返回答案。
 *
 *
 *
 * 示例 1：
 * 输入：n = 4, k = 2
 * 输出：
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class A02_combine {

    public static List<List<Integer>> combine(int n, int k) {

        return f(n,k,new ArrayList<>(),new ArrayList<>());
    }

    public static List<List<Integer>> f(int cur, int k,List<List<Integer>> res,List<Integer> list) {
        if(list.size() == k){
            res.add(new ArrayList<>(list));
            return res;
        }
        if(cur==0){
            return res;
        }
        for (int i = cur; i >0 ; i--) {
            list.add(i);
            System.out.println("递归之前 => " + list);
            f(i-1,k,res,list);
            System.out.println("递归之后 => " + list);
            list.remove(list.size()-1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(combine(4,2));
    }
}
