package ingram.lc.window;

import ingram.sort.HeapSort;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 *
 *  示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 */
public class A01_findAnagrams {

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> list =  new ArrayList<>();
        int l = 0 ,r = 0;
        int count = 0;
        Map<Character,Integer> windows = new HashMap<>();
        Map<Character,Integer> needs = new HashMap<>(); // 记录字符出现的次数
        for (int i = 0; i < p.length(); i++) {
            if(needs.containsKey(p.charAt(i))){
                needs.put(p.charAt(i),needs.get(p.charAt(i))+1);
            }else{
                needs.put(p.charAt(i),1);
            }
        }

        while(r<s.length()){

            char c = s.charAt(r);
            if(needs.containsKey(c)){
                windows.put(c,windows.getOrDefault(c, 0)+1);
                if(needs.get(c).equals(windows.get(c))){
                    count ++;
                }
            }
            r++;
            while(count == needs.size()){
                //如果当前窗口的长度等于p的长度,说明证号匹配,则将左端索引left添加进list中
                if(r-l == p.length()) {
                    list.add(l);
                }
                //否则判断左边left索引指向的元素
                char c2 = s.charAt(l);
                if(needs.containsKey(c2)) { // 如果needs中包含该元素
                    windows.put(c2, windows.get(c2)-1); // 则将对应的次数减1
                    // 如果当前窗口中出现的次数小于needs中的,则匹配的个数count也要减1
                    if(windows.get(c2) < needs.get(c2)) {
                        count --;
                    }
                }
                l++; // left右移一位
            }
        }

        return list;
    }
}
