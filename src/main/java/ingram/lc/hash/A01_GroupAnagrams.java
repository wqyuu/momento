package ingram.lc.hash;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 字母异位词 是由重新排列源单词的所有字母得到的一个新单词。
 */
public class A01_GroupAnagrams {

    /**
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {

        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            boolean flag = false;
            String b = strs[i];
            for (int j = 0; j < res.size(); j++) {
                List<String> cList = res.get(j);
                String a = cList.get(0);
                flag = isAna(a,b);
                if(flag){
                    cList.add(b);
                    break;
                }
            }
            if(!flag){
                List<String> list = new ArrayList<>();
                list.add(b);
                res.add(list);
            }
        }

        return res;
    }

    static boolean isAna(String a,String b){
        if(a.length() != b.length()){
            return false;
        }
        Map<Character,Integer> mapA = new HashMap<>();
        Map<Character,Integer> mapB = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            mapA.put(a.charAt(i),mapA.getOrDefault(a.charAt(i),0)+1);
            mapB.put(b.charAt(i),mapB.getOrDefault(b.charAt(i),0)+1);
        }
        for (Character c:mapA.keySet()) {
            if(!mapB.containsKey(c)){
                return false;
            }
            if(mapB.get(c).intValue() != mapA.get(c).intValue()){
                return false;
            }
        }
        return true;
    }


    public  List<List<String>> groupAnagramsLeetcode(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());

    }


    public static void main(String[] args) {
        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(groupAnagrams(new String[]{""}));
        System.out.println(groupAnagrams(new String[]{"a"}));
    }
}
