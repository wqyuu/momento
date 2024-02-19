package ingram.DCodeRecord;

import java.util.*;

/**
 * @Author qywu11
 * @Date 2024/2/7 11:38
 * @Version 1.0
 */
public class SildeWindow {


    /**
     * 239. 滑动窗口最大值
     * 困难
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回 滑动窗口中的最大值 。
     *
     * 示例 1：
     * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     * 输出：[3,3,5,5,6,7]
     * 解释：
     * 滑动窗口的位置                最大值
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     *
     * 示例 2：
     * 输入：nums = [1], k = 1
     * 输出：[1]
     * @param arr
     * @param w
     * @return
     */
    public int[] maxSlidingWindow(int[] arr, int w) {
        if( arr == null || w < 1 || arr.length < w){
            return null;
        }
        LinkedList<Integer> qMax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 窗口 R向右移动，弹出所有小于新数字的值
            while (!qMax.isEmpty() && arr[qMax.peekLast()] <= arr[i]){
                qMax.pollLast();
            }
            // 加入新数字
            qMax.addLast(i);

            // 左侧过窗口，弹出左边首个数字
            if(w == i - qMax.peekFirst()){
                qMax.pollFirst();
            }
            // 位置大于窗口后，记录每个窗口最大数字到res中
            if(i >= w - 1){
                res[index ++] = arr[qMax.peekFirst()];
            }
        }
        return res;
    }

    /**
     * 1456. 定长子串中元音的最大数目
     * 中等
     * 给你字符串 s 和整数 k 。
     * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
     * 英文中的 元音字母 为（a, e, i, o, u）。
     *
     * 示例 1：
     * 输入：s = "abciiidef", k = 3
     * 输出：3
     * 解释：子字符串 "iii" 包含 3 个元音字母。
     * 示例 2：
     * 输入：s = "aeiou", k = 2
     * 输出：2
     * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
     * @param s
     * @param k
     * @return
     */
    public int maxVowels(String s, int k) {
        List<Character> set = Arrays.asList('a','e','i','o','u');
        LinkedList<Integer> win = new LinkedList<>();
        int max = 0;
        int len = 0;
        for (int i = 0; i < s.length(); i++) {
            if(!win.isEmpty() && k == i - win.peekFirst()){
                Integer first = win.pollFirst();
                if(set.contains(s.charAt(first))){
                    len --;
                }
            }
            if(set.contains(s.charAt(i))){
                len ++;
            }
            win.addLast(i);
            max = Math.max(max,len);
        }
        return max;
    }

    /**
     * 3. 无重复字符的最长子串
     * 中等
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 示例 1:
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            while (list.contains(s.charAt(i))){
                list.remove(0);
            }
            list.add(s.charAt(i));
            max = Math.max(max,list.size());
        }
        return max;
    }

    /**
     * 1004. 最大连续1的个数 III
     * 中等
     *
     * 给定一个二进制数组 nums 和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
     * 示例 1：
     * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
     * 输出：6
     * 解释：[1,1,1,0,0,1,1,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
     * 示例 2：
     * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
     * 输出：10
     * 解释：[0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
     * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int N = nums.length;
        int res = 0;
        int left = 0, right = 0;
        int zeros = 0;
        while (right < N) {
            if (nums[right] == 0)
                zeros ++;
            while (zeros > k) {
                if (nums[left++] == 0)
                    zeros --;
            }
            res = Math.max(res, right - left + 1);
            right ++;
        }
        return res;
    }


    Map<Character,Integer> posMap = new HashMap<>();
    Map<Character,Integer> oriMap = new HashMap<>();
    /**
     *
     * 76. 最小覆盖子串
     * 困难
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。
     * 如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * 注意：
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
     *
     * 示例 1：
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
     *
     * 示例 2：
     * 输入：s = "a", t = "a"
     * 输出："a"
     * 解释：整个字符串 s 是最小覆盖子串
     * @param s [a,v,d,c,b,s,a,r,c,t,b,o,a,f,c,b,l,a,q,q,w,b,c]
     * @param t [a,b,c]
     * @return
     */
    public  String minWindow(String s, String t) {
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
    private  boolean check(){
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
