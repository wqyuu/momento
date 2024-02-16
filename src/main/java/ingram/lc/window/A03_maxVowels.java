package ingram.lc.window;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 1456. 定长子串中元音的最大数目
 * 相关企业
 * 给你字符串 s 和整数 k 。
 * 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。
 * 英文中的 元音字母 为（a, e, i, o, u）。
 *
 * 示例 1：
 * 输入：s = "abciiidef", k = 3
 * 输出：3
 * 解释：子字符串 "iii" 包含 3 个元音字母。
 *
 * 示例 2：
 * 输入：s = "aeiou", k = 2
 * 输出：2
 * 解释：任意长度为 2 的子字符串都包含 2 个元音字母。
 *
 * 示例 3：
 * 输入：s = "leetcode", k = 3
 * 输出：2
 * 解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
 *
 * 示例 4：
 * 输入：s = "rhythms", k = 4
 * 输出：0
 * 解释：字符串 s 中不含任何元音字母。
 *
 * 示例 5：
 * 输入：s = "tryhard", k = 4
 * 输出：1
 */
public class A03_maxVowels {

    public int maxVowelsME(String s, int k) {
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

    public int maxVowels(String s, int k) {
        int n = s.length();
        int vowel_count = 0;
        for (int i = 0; i < k; ++i) {
            vowel_count += isVowel(s.charAt(i));
        }
        int ans = vowel_count;
        for (int i = k; i < n; ++i) {
            vowel_count += isVowel(s.charAt(i)) - isVowel(s.charAt(i - k));
            ans = Math.max(ans, vowel_count);
        }
        return ans;
    }

    public int isVowel(char ch) {
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ? 1 : 0;
    }
}
