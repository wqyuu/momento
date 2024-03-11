package ingram.hw.stack;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20.有效括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 */
public class A01_isValid {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        Map<Character,Character> map = new HashMap<>();
        map.put('(',')');
        map.put('[',']');
        map.put('{','}');

        for (int i = 0; i < s.length(); i++) {
            Character at = s.charAt(i);
            if(at=='('||at=='['||at=='{'){
                stack.push(at);
            }else {
                if(stack.isEmpty()){
                    return false;
                }
                Character pre = stack.pop();
                Character v = map.get(pre);
                if(v != at){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
