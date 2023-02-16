package ingram.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author qywu11
 * @Date 2023/1/3 17:13
 * @Version 1.0
 * 贪心策略 最小权值字符串排列，权值为c>b>a排列，多个字符的串，为  b000 > acba b后面000为补充字符
 */
public class LowestLexicography {

    public static class MyComparator implements Comparator<String>{

        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString(String[] strs){
        if(strs == null || strs.length == 0){
            return "";
        }
        Arrays.sort(strs,new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs1 = {"l","fr","a","xty","wqy"};
        System.out.println(lowestString(strs1));
    }
}
