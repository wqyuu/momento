package ingram.hw.window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A03_MinSubStr {


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
