package ingram.forceRecursion;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author qywu11
 * @Date 2023/1/9 16:13
 * @Version 1.0
 * 打印字符串形成的全排列
 */
public class PrintAllPermutation {

    // 分支限界
    public static List<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.length() < 1) {
            return res;
        }

        char[] chs = str.toCharArray();
        process(chs, 0, res);
        return res;
    }

    /**
     * @param chs 字符数组
     * @param i   当前来到位置
     * @param res 全排列结果
     */
    public static void process(char[] chs, int i, ArrayList<String> res) {
        if (i == chs.length) {  // 到达最后位置，保存这次结果
            res.add(String.valueOf(chs));
        }

        boolean[] visit = new boolean[26];
        for (int j = i; j < chs.length; j++) {
            if (!visit[chs[j] - 'a']) {
                visit[chs[j] - 'a'] = true;
                swap(chs, i, j);
                process(chs, i + 1, res);
                swap(chs, i, j);
            }
        }

    }

    public static void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }

    public static void main(String[] args) {
        List<String> res = permutation("abc");
        res.forEach(System.out::println);
    }

}
