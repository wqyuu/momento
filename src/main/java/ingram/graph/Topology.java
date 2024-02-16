package ingram.graph;

import java.util.*;

/**
 * @Author qywu11
 * @Date 2022/12/12 15:49
 * @Version 1.0
 *
 * 图--拓扑排序
 */
public class Topology {

    public static List<Node> sortedTopology(Graph graph){

        // key: 某一个node
        // value: 剩余的入度
        HashMap<Node,Integer> inMap = new HashMap<>();
        // 入度为0的点，才进入这个队列
        Queue<Node> zeroInQueue = new LinkedList<>();

        for (Node node: graph.nodes.values()){
            inMap.put(node,node.in);
            if(node.in == 0){
                zeroInQueue.add(node);
            }
        }


        List<Node> rs = new ArrayList<>();
        while (!zeroInQueue.isEmpty()){
            Node cur = zeroInQueue.poll();
            rs.add(cur);

            for (Node next: cur.nexts){
                inMap.put(next,inMap.get(next) - 1);
                if(inMap.get(next) == 0){
                    zeroInQueue.add(next);
                }
            }
        }

        return rs;

    }
}
