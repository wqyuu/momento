package ingram.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * @Author qywu11
 * @Date 2022/12/9 15:34
 * @Version 1.0
 */
public class DFS {

    /**
     * 深度优先遍历
     * @param node
     */
    public static void dfs(Node node){

        if(node == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);

        System.out.println(node.value);
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            for (Node next: cur.nexts){
                if(!set.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
}
