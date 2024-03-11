package ingram.hw.window;

import java.util.LinkedList;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 */
public class A07_lengthOfLongestSubstring {


    public static int lengthOfLongestSubstring(String s) {

        LinkedList<Character> list = new LinkedList<>();

        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            while (list.contains(s.charAt(i))){
                list.pollFirst();
            }
            list.addLast(s.charAt(i));
            max = Math.max(max, list.size());
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("dvdf"));
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
