package ingram.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @Author qywu11
 * @Date 2022/12/9 15:29
 * @Version 1.0
 */
public class BFS {

    /**
     * 宽度优先遍历
     * @param node
     */
    public static void bfs(Node node){
        if(node == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        Set<Node> set = new HashSet<>();
        queue.add(node);
        set.add(node);
        while (!queue.isEmpty()){
            Node n = queue.poll();
            System.out.println(n.value);
            for (Node next: n.nexts){
                if(!set.contains(next)){
                    set.add(next);
                    queue.add(next);
                }
            }
        }

    }
}
