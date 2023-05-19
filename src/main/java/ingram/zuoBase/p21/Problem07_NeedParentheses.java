package ingram.zuoBase.p21;

/**
 * @Author qywu11
 * @Date 2023/4/20 15:43
 * @Version 1.0
 *
 * 判断括号串是否完整，计算需要补充多少会完整
 */
public class Problem07_NeedParentheses {

    public static int needParentheses(String str){

        int count = 0;
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == '('){
                count ++;
            }else { // 遇到的是）
                if(count == 0){ // 到右侧count为0，说明这个右括号是单独的，需要补1个左括号
                    ans ++;
                }else {
                    count --;
                }
            }
        }
        return count + ans;
    }

    public static void main(String[] args) {
       int x =  needParentheses(")))");
        System.out.println(x);
    }
}
