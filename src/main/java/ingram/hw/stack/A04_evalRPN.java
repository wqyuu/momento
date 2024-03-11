package ingram.hw.stack;

import java.util.*;

/**
 * 150. 逆波兰表达式求值
 * 中等
 * 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。
 *
 * 请你计算该表达式。返回一个表示表达式值的整数。
 *
 * 注意：
 * 有效的算符为 '+'、'-'、'*' 和 '/' 。
 * 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
 * 两个整数之间的除法总是 向零截断 。
 * 表达式中不含除零运算。
 * 输入是一个根据逆波兰表示法表示的算术表达式。
 * 答案及所有中间计算结果可以用 32 位 整数表示。
 *
 *
 * 示例 1：
 * 输入：tokens = ["2","1","+","3","*"]
 * 输出：9
 * 解释：该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 *
 * 示例 2：
 * 输入：tokens = ["4","13","5","/","+"]
 * 输出：6
 * 解释：该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 *
 * 示例 3：
 * 输入：tokens = ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
 * 输出：22
 * 解释：该算式转化为常见的中缀算术表达式为：
 *   ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 */
public class A04_evalRPN {


    /**
     * 官方
     * @param tokens
     * @return
     */
    public int evalRPN_LC(String[] tokens) {
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.pop();
                int num1 = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();
    }

    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }


    public static int evalRPN(String[] tokens) {

        List<String> calFlags = Arrays.asList("+","/","*","-");
        //  ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
        Stack<String> stack = new Stack<>();
        int i = 0;
        while (i< tokens.length || !stack.isEmpty()) {
            if (!stack.isEmpty() && calFlags.contains(stack.peek())){
                String opt = stack.pop();
                String n1 = stack.pop();
                String n2 = stack.pop();
                String x = "";
                if(opt.equals("+")){
                    x =  (Integer.parseInt(n1) + Integer.parseInt(n2)) +"";
                }else if(opt.equals("-")){
                    x =  (Integer.parseInt(n2) - Integer.parseInt(n1)) +"";
                }else if(opt.equals("*")){
                    x =  (Integer.parseInt(n2) * Integer.parseInt(n1)) +"";
                }else if(opt.equals("/")){
                    x =  (Integer.parseInt(n2) / Integer.parseInt(n1)) +"";
                }
                stack.push(x);
            }
            if(i < tokens.length){
                stack.push(tokens[i]);
            }else {
                return Integer.parseInt(stack.pop());
            }
            i ++;

        }

        return Integer.parseInt(stack.pop());
    }

    public static void main(String[] args) {
        int rs  = evalRPN(new String[]{"10","6","9","3","+","-11","*","/","*","17","+","5","+"});
        System.out.println(rs);
    }
}
