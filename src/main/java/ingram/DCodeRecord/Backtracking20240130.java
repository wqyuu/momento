package ingram.DCodeRecord;

import java.util.*;

/**
 * @Author qywu11
 * @Date 2024/1/30 14:22
 * @Version 1.0
 * 回溯
 */
public class Backtracking20240130 {

    /**
     * 77. 组合
     * 已解答
     * 中等
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     *
     * 示例 1：
     * 输入：n = 4, k = 2
     * 输出：
     * [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
     * @param n
     * @param k
     * @return
     */
    public  List<List<Integer>> combine(int n, int k) {
        return fCombine(n,k,new ArrayList<>(),new ArrayList<>());
    }

    public  List<List<Integer>> fCombine(int cur, int k,List<List<Integer>> res,List<Integer> list) {
        if(list.size() == k){
            res.add(new ArrayList<>(list));
            return res;
        }
        if(cur==0){
            return res;
        }
        for (int i = cur; i >0 ; i--) {
            list.add(i);
            fCombine(i-1,k,res,list);
            list.remove(list.size()-1);
        }
        return res;
    }

    /**
     * 39. 组合总和
     * 中等
     * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
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
     * @param candidates
     * @param target
     * @return
     */
    public  List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        fCombinationSum(candidates,0,target,new ArrayList<>(),res);
        return res;
    }
    public  void fCombinationSum(int[] candidates, int index,int target,List<Integer> list,List<List<Integer>> res){

        if(target == 0){
            res.add(new ArrayList<>(list));
            return;
        }

        if(target < 0){
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            list.add(candidates[i]);
            fCombinationSum(candidates,i,target - candidates[i],list,res);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 216. 组合总和 III
     * 中等
     * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
     * 只使用数字1到9
     * 每个数字 最多使用一次
     * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。
     *
     * 示例 1:
     * 输入: k = 3, n = 7
     * 输出: [[1,2,4]]
     * 解释:
     * 1 + 2 + 4 = 7
     * 没有其他符合的组合了
     * @param k
     * @param n
     * @return
     */
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> ans = new ArrayList<>();
        dfsCombinationSum3(1, 9, k, n,ans,new ArrayList<>());
        return ans;
    }

    public void dfsCombinationSum3(int cur, int n, int k, int sum,List<List<Integer>> ans,List<Integer> temp) {
        if (temp.size() + (n - cur + 1) < k || temp.size() > k) {
            return;
        }
        if (temp.size() == k) {
            int tempSum = 0;
            for (int num : temp) {
                tempSum += num;
            }
            if (tempSum == sum) {
                ans.add(new ArrayList<Integer>(temp));
                return;
            }
        }
        temp.add(cur);
        dfsCombinationSum3(cur + 1, n, k, sum,ans,temp);
        temp.remove(temp.size() - 1);
        dfsCombinationSum3(cur + 1, n, k, sum,ans,temp);
    }

    // 回溯法来解决n个for循环的问题
    private static final Map<Character,List<Character>> letterMap = new HashMap<>();
    static {
        letterMap.put('0',Arrays.asList(' '));
        letterMap.put('1',Arrays.asList('*'));
        letterMap.put('2',Arrays.asList('a','b','c'));
        letterMap.put('3',Arrays.asList('d','e','f'));
        letterMap.put('4',Arrays.asList('g','h','i'));
        letterMap.put('5',Arrays.asList('j','k','l'));
        letterMap.put('6',Arrays.asList('m','n','o'));
        letterMap.put('7',Arrays.asList('p','q','r','s'));
        letterMap.put('8',Arrays.asList('t','u','v'));
        letterMap.put('9',Arrays.asList('w','x','y','z'));
    }

    /**
     * 17. 电话号码的字母组合
     * 中等
     * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
     * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
     *
     * 示例 1：
     * 输入：digits = "23"
     * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        //注意边界条件
        if(digits==null || digits.length()==0) {
            return res;
        }
        add( res, digits,0,new StringBuilder());
        return res;
    }
    public static void add(List<String> res,String str,int index,StringBuilder sb){
        if(index == str.length()){
            res.add(sb.toString());
            return;
        }

        Character c = str.charAt(index);
        List<Character> list = letterMap.get(c);
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            add(res,str,index + 1,sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    /**
     * 93.复原IP地址
     * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
     * 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
     * 示例 1：
     * 输入：s = "25525511135"
     * 输出：["255.255.11.135","255.255.111.35"]
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        getIp(s,0,res,new StringBuilder(),0);
        return res;
    }

    public static void getIp(String s,int index,List<String> res,StringBuilder sb,int size){
        if(index == s.length() && size == 4){
            String ipStr = new String(sb);
            res.add(ipStr.substring(0,ipStr.length()-1));
            return;
        }
        if(index > s.length()){
            return;
        }

        for (int i = index; i < s.length(); i++) {
            String cur = s.substring(index,i+1);
            if(isIp(cur)){
                sb.append(cur).append(".");
                getIp(s,i+1,res,sb,size+1);
                sb.delete(sb.length()-cur.length()-1,sb.length());
            }
        }
    }

    static boolean isIp(String str){
        // 防止023这种数据
        if(str.length()>1 && str.indexOf("0") == 0){
            return false;
        }
        if(str.length()>3){
            return false;
        }
        long ip = Long.parseLong(str);
        return ip >= 0 && ip <= 255;
    }



    // 输入：nums = [1,2,3] 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        subBack(nums, 0, res,  new ArrayList<>());
        return res;
    }

    public static void subBack(int[] nums,int index,List<List<Integer>> rs,List<Integer> list){
        rs.add(new ArrayList<>(list));
        if(index >= nums.length){
            return;
        }

        for (int i = index; i < nums.length; i++) {
            list.add(nums[i]);
            subBack(nums, i+1, rs, list);
            list.remove(list.size() - 1);
        }
    }

    static List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    static LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果

    /**
     * 78.子集
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例: 输入: nums = [1,2,3] 输出: [ [3],   [1],   [2],   [1,2,3],   [1,3],   [2,3],   [1,2],   [] ]
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets1(int[] nums) {
        subsetsHelper(nums, 0);
        return result;
    }

    private static void subsetsHelper(int[] nums, int startIndex){
        result.add(new ArrayList<>(path));//「遍历这个树的时候，把所有节点都记录下来，就是要求的子集集合」。
        if (startIndex >= nums.length){ //终止条件可不加
            return;
        }
        for (int i = startIndex; i < nums.length; i++){
            path.add(nums[i]);
            System.out.println("subsetsHelper re pre:"+path);
            subsetsHelper(nums, i + 1);
            path.removeLast();
            System.out.println("subsetsHelper re post:"+path);
        }
    }

    /**
     * 90.子集II
     * 给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     * 说明：解集不能包含重复的子集。
     * 示例:
     * 输入：nums = [1,2,2] 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        swd(nums, 0, new ArrayList<>(), res);
        return res;
    }

    public static void swd(int[] nums,int startIndex,List<Integer> list, List<List<Integer>> res){
        res.add(new ArrayList<>(list));
        if(startIndex >= nums.length){
            return;
        }
        for (int i = startIndex; i < nums.length; i++) {
            if (i > startIndex && nums[i] == nums[i - 1] ) {
                continue;
            }
            list.add(nums[i]);
            swd(nums, i + 1, list, res);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 491.递增子序列
     * 给定一个整型数组, 你的任务是找到所有该数组的递增子序列，递增子序列的长度至少是2。
     * 示例:
     * 输入: [4, 6, 7, 7]
     * 输出: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
     * @param nums
     * @return
     */
    public static List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        f(nums, 0, new ArrayList<>(), res);
        return res;

    }

    public static void f(int[] nums,int index,List<Integer> list,List<List<Integer>> res){
        if(list.size()>1){
            res.add(new ArrayList<>(list));
        }

        Set<Integer> useSet = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if( (!list.isEmpty() && nums[i] < list.get(list.size() - 1)) || useSet.contains(nums[i])){
                continue;
            }
            useSet.add(nums[i]);
            list.add(nums[i]);
            f(nums, i+1, list, res);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 46.全排列
     * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     * 输入：nums = [1,2,3] 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     * @param nums
     * @return
     */
    public  static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums,new HashSet<>(),new ArrayList<>(),res);
        return res;
    }

    public static void dfs(int[] nums,Set<Integer> set,List<Integer> list,List<List<Integer>> res){
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i])){
                continue;
            }
            set.add(nums[i]);
            list.add(nums[i]);
            dfs(nums, set, list, res);
            set.remove(nums[i]);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 47.全排列 II
     * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
     *
     * 示例 1：
     * 输入：nums = [1,1,2]
     * 输出： [[1,1,2], [1,2,1], [2,1,1]]
     * 示例 2：
     * 输入：nums = [1,2,3]
     * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
     */
    public  static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        dfs2(nums,used,new ArrayList<>(),res);
        return res;
    }

    public static void dfs2(int[] nums,boolean[] used,List<Integer> list,List<List<Integer>> res){
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if ( i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            //如果同⼀树⽀nums[i]没使⽤过开始处理
            if (!used[i]) {
                used[i] = true;//标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
                list.add(nums[i]);
                dfs2(nums, used,list,res);
                list.remove(list.size() - 1);//回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
                used[i] = false;//回溯
            }
        }
    }

    /**
     * 131.分割回文串
     * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
     *
     * 返回 s 所有可能的分割方案。
     * 示例: 输入: "aab" 输出: [ ["aa","b"], ["a","a","b"] ]
     * @param s
     * @return
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        f(s,new ArrayList<>(),res,0);
        return res;
    }

    public static void f(String s,List<String> list, List<List<String>> res,int index){
        if(s.length() == index){
            res.add(new ArrayList<>(list));
            return;
        }

        // a ab
        for (int i = index; i < s.length(); i++) {
            String cur = s.substring(index,i+1);
            if(!isParm(cur)){
                continue;
            }
            list.add(cur);
            f(s,list,res,i+1);
            list.remove(list.size() - 1);
        }
    }

    public static boolean isParm(String s){
        int start = 0,end = s.length() -1;
        while (start < end){
            if(s.charAt(start++) != s.charAt(end--)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(findSubsequences(new int[]{4,6,7,7}));
//        System.out.println(findSubsequences(new int[]{4,4,3,2,1}));
//        System.out.println(permute(new int[]{1,2,3}));
        System.out.println(permute2(new int[]{1,1,1}));


//        System.out.println("023".indexOf("0"));
//        System.out.println("203".indexOf("0"));
//        System.out.println(restoreIpAddresses("24423110"));
//        System.out.println(restoreIpAddresses("1111"));
//        System.out.println(restoreIpAddresses("0000"));
//        System.out.println(restoreIpAddresses("010010"));
//        System.out.println(restoreIpAddresses("101023"));
//        System.out.println(restoreIpAddresses("25525511135"));
//        System.out.println(restoreIpAddresses("9999999999999999999"));
//        System.out.println(subsets(new int[]{1,2,3}));
//        System.out.println(subsets1(new int[]{1,2,3}));
//        System.out.println(subsets(new int[]{1}));
//        System.out.println(subsets(new int[]{1,1,2}));
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
    }
}
