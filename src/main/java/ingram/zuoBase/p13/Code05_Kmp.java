package ingram.zuoBase.p13;

/**
 * @Author qywu11
 * @Date 2023/2/20 18:59
 * @Version 1.0
 */
public class Code05_Kmp {


    /**
     * 判断字串s是否包含m，若包含返回s中包含m最左的索引，否则返回-1
     * 利用kmp，跳过重复检查，acsbcsaacsbcssaa ,csbcss  在这两个中正常检查最多需要匹配A.length*B.length次
     * [1]
     * acsbcs[a]acsbcssaa
     *  csbcs[s]
     * [2]
     * acsbcs[a]acsbcssaa
     *     cs[b]css
     * [3]
     * acsbcs[a]acsbcssaa
     *       [c]sbcss
     * acsbcsa[a]csbcssaa
     *        [c]sbcss
     * 分三种情况讨论，(1)两个串能对上，一起向后移动索引i,j
     * (2)两个串对不上，移动B，把位置往前跳，采用跳过把csbcss跳到[2]位置 b  ,相当于把B串往后推
     * (3)B串跳到头位置0，把A串往后移动
     * @param s
     * @param m
     * @return
     */
    public static int getIndexOf(String s,String m){

        if(s ==  null || m == null || m.length()<1 ||s.length()<m.length()){
            return -1;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(str2); // O(M)

        // O(N)
        while (i1 < str1.length && i2 < str2.length){
            if(str1[i1] == str2[i2]){
                i1 ++;
                i2 ++; // 相当于移动父串
            }else if(next[i2] == -1){
                i1 ++; // 相当于移动子串
            }else {
                i2 = next[i2]; // 相当于移动子串到next[i2]位置
            }
        }
        // i1 越界 或者 i2越界
        return i2 == str2.length ? i1 - i2 : -1;
    }

    /**
     * abbstabbeabbstabb[k]f
     * n = f,n-1 = k cn=8 abbstabb
     * cn = next[8] ==> cn=3 abb
     *
     * @param ms
     * @return
     */
    public static int[] getNextArray(char[] ms){
        if(ms.length == 1){
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2; // next数组位置
        int cn = 0;
        while (i < next.length){
            if(ms[i - 1] == ms[cn]){
//                next[i] = cn + 1;
//                i++;
//                cn ++;
                next[i ++] = ++ cn;
            }else if(cn > 0){ // i-1位置的值不等于cn位置值，且cn是大于0的继续像前面找next中的前缀，和i-1位置值进行对比
                cn = next[cn];
            }else { // cn不大于0,说明找到最前面也没找到跟i-1这个后缀一样的，设置next=0,继续下一个i
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        int index = getIndexOf("acsbcsaacsbcssaa","csbcss");
        System.out.println(index);
    }


    public static int kmp(String text, String pattern) {
        int[] next = getNext(pattern);
        int i = 0;
        int j = 0;
        while (i < text.length() && j < pattern.length()) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == pattern.length()) {
            return i - j;
        } else {
            return -1;
        }
    }

    public static int[] getNext(String pattern) {
        int[] next = new int[pattern.length()];
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < pattern.length() - 1) {
            if (j == -1 || pattern.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
