package ingram.zuoBase.p21;

/**
 * @Author qywu11
 * @Date 2023/4/20 18:25
 * @Version 1.0
 *
 * 从左往右尝试模型
 * [1-26]数字，可以转为英文字母[a-z]，两个数字放一块也可以结合为一个字母，如12->l 也可以是ab
 * abcdefghijklmnopq
 * 按以上规则转换，给定一个数字，可以转为多少种字母组合
 *
 */
public class Problem04_NumToStringWays {

    // str[index...] 能转出多少种有效字符串表达
    public static int process(char[] str,int index){
        // 到尾部了
        if(index == str.length){
            return 1;
        }
        // index及其后续还是有数字字符的
        // 0.。
        if(str[index] == '0'){ // 开头为0
            return 0;
        }
        // index及其后续还是有数字字符的,且不以0开头，以1-9开头
        int res = process(str,index + 1); // 做了一个决定，让str[index]自己作为一个部分
        if(index == str.length - 1){ // 除了index之外，后续没字符串了
            return res;
        }
        // index+1依然没越界
        // index和index+1共同构成一个部分 <27
        if((( str[index] - '0') * 10 + str[index + 1] - '0' ) < 27){
            res += process(str,index + 2);
        }
        return res;
    }

    public static int dpWays(int num){
        if(num < 1){
            return 0;
        }
        char[] str = String.valueOf(num).toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        dp[N -1] = str[N-1] == '0' ? 0 : 1;
        for (int i = N - 2; i >= 0; i--) {
            if(str[i] == '0'){
                dp[i] = 0;
            }else {
                dp[i] = dp[i + 1] + ((( str[i] - '0') * 10 + str[i + 1] - '0' ) < 27 ? dp[i+2] : 0);
            }
        }
        return dp[0];
    }

}
