package ingram.zuoBase.p13;

/**
 * @Author qywu11
 * @Date 2023/2/23 10:41
 * @Version 1.0
 * Manacher: 解决回文串问题
 * 关键求出最长回文半径
 * 基本处理时，使用暴力扩每个i位置的回文串，找到长度，如 12321   i=1 p=1,i=2 p=1,i=3 p=5, 对称线是实线->3
 * 但无法处理偶数回文，例如1122332211 i=1 p=1,i=2 p=1,i=3 p=1,...结果p都是1，找不到最长回文串，因为偶数是虚线对称 在33之间
 * 解决办法是在其中填充特殊字符： #1#1#2#2#3#3#2#2#1#1#  ,i=1 p=1,i=2 p=3,i=3 p=5,...i=11 p=21 找到最长回文位置33之间#
 * 这个时间复杂度为O(n^2)
 * -------
 * Manacher解法优化在不用去比中点后半部分 时间复杂度为O(n)
 *
 * 需要这些变量： C:中点位置   R:右边位置  i:当前位置   L:左边位置   R`:右边对称位置   L`:左边对称位置
 *    a # b # c # b # d # b # c # b # a # k
 *    i(C=0 L=0 R=0)
 *    R`(L)       i`  C   i(C=8 L=0 R=16)
 *
 *  【以下分为两种大情况，省略#示例】
 *  第一种情况：i来到的中心点位置不在回文边界里，暴力向外扩，没有优化
 *    # 1 # 2 # 1 # ...              R = -1
 * -1 0 1 2 3 4 5 6
 *  中心点 0位置范围：0~0  R更新 R = 0
 *  中心点 1位置范围：0~2  R更新 R = 2
 *
 *  中心点 2位置范围：2~2   R = 2  -----属于第二种情况
 *   ---
 *  第二种情况：来到的中心点位置来到回文边界内部   R内
 *      (L  i`  C  i   R ）
 *   设置R对C对称点为L，i对C对称点为i`,例如：
 *   [# 1  # 2 # 2 # 1 #] ....
 *  [L  i` c   i        R]
 *
 *   (2-1) i'回文串在L..R内部
 *   [a (b c b) d k s k d b c b a]...
 *   L     i'       c       i    R
 *   i'回文串在L..R内部，  i最小回文半径与i'回文半径相同
 *   例如：
 *   [ x( i` )y  o z( i )k       R]
 *   [L  i`      c    i        R]
 *   例子中()为i、i'回文边界，因为明确知道i'的回文范围是()，所以x!=y,根据沿着c对称
 *   看出，i回文是i'回文的逆序，因为c的回文范围是L-R，所以有y==z ，x==k z!=k，说明i无法扩充了，回文范围就是()内
 *
 *  (2-2) i'回文串在L..R外部
 * (..[x【 i` 】y)  o   z【——o——】p]
 *    [L  i` L’  c    R‘ i  R]
 * (a b [{c d e d c} b a) k a b {c d e d c}] f t
 *       L    i'  L'     c      R'   i    R
 *  上面x-y，z-p两段，x、y根据i`对称，所以x==y ， y和z关于c对称且在以c为中心回文段内，所以y==z
 *  由于以c为中心回文段是L..R证明x!=p 所以z!=p，i回文串是z..p内部【 】
 *
 *  (2-3) 压线
 *  [(---o---)  o    (---o---)]
 *  L    i'     c    R'  i   R
 *
 * k [(b c d c b) s (b c d c b)] ? s
 *   L     i'     c      i   R
 * 恰好i'的回文段在L上，i最小回文段在R上，能不能往外扩需要看?是什么,如果是s则可以向外扩
 */
public class Code06_Manacher {

    public static char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() *2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            // charArr[a,c,d,e]
            // i = 0 res[0] = #
            // i = 1 res[1] = a
            // i = 2 res[2] = #
            // i = 3 res[3] = b
            // ...
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String s){

        if(s == null || s.length() == 0){
            return 0;
        }
        // [#a#v#e#v#a#]
        char[] str = manacherString(s);
        // 回文半径数组
        int[] pArr = new int[str.length];
        int C = -1;
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            // 如果i在右侧R内则取 R-i和 i`半径较小值作为i的半径 ,i在R外，直接半径为1
            pArr[i] = R > i ? Math.min(R - i,pArr[2 * C - i]) : 1; //2 * C - i -> i`

            while (i + pArr[i] < str.length && i - pArr[i] > -1){
                if(str[i + pArr[i]] == str[i - pArr[i]]){ // 符合条件往外扩 1、 2-3
                    pArr[i] ++;
                }else {
                    break;
                }
            }
            // 更往右的话，更新R 更新C
            if(i + pArr[i] > R){
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max,pArr[i]);
        }
        return max - 1;
    }


    public static void main(String[] args) {
        int max = maxLcpsLength("abcbabcbab");
        System.out.println(max);
    }
















}
