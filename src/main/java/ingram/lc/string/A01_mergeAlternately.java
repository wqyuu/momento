package ingram.lc.string;

public class A01_mergeAlternately {
    /**
     * 1768. 交替合并字符串
     *
     */
    static class A01_MergeAlternately {
        public static String mergeAlternately(String word1, String word2) {
            int i1 = 0;
            int i2 = 0;
            StringBuilder sb1 = new StringBuilder();
            while(i1 < word1.length() && i2 < word2.length()){
                sb1.append(word1.charAt(i1++));
                sb1.append(word2.charAt(i2++));
            }
            while(i1 < word1.length()){
                sb1.append(word1.charAt(i1++));
            }
            while(i2 < word2.length()){
                sb1.append(word2.charAt(i2++));
            }
            return sb1.toString();
        }

        public static void main(String[] args) {
            String res = mergeAlternately("abc","jklw");
            System.out.println(res);
        }
    }
}
