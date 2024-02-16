package ingram.graph;

import java.util.*;

/**
 * @Author qywu11
 * @Date 2022/12/12 16:30
 * @Version 1.0
 */
public class MySets {

    public HashMap<Node,List<Node>> setMap;

    public MySets(List<Node> nodes){
        for (Node cur: nodes){
            List<Node> set = new ArrayList<>();
            set.add(cur);
            setMap.put(cur,set);
        }
    }

    public MySets() {

    }

    public boolean isSameSet(Node from,Node to){
        List<Node> fromSet = setMap.get(from);
        List<Node> toSet = setMap.get(to);
        return fromSet == toSet;
    }

    public void union(Node from,Node to){
        List<Node> fromSet = setMap.get(from);
        List<Node> toSet = setMap.get(to);

        for (Node toNode : toSet){
            fromSet.add(toNode);
            setMap.put(toNode,fromSet);
        }
    }



    public static class EdgeComparator implements Comparator<Edge>{

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph){
        MySets mySets = new MySets();

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge: graph.edgs){
            priorityQueue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if(!mySets.isSameSet(edge.from,edge.to)){
                result.add(edge);
                mySets.union(edge.from,edge.to);
            }
        }
        return result;
    }

    public static Set<Edge> primMST(Graph graph){
        // 解锁的边进入小根堆
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> set = new HashSet<>();
        Set<Edge> result = new HashSet<>(); // 一次挑选的边在result里

        for (Node node : graph.nodes.values()){
            // node 是开始点
            if(!set.contains(node)){
                set.add(node);
                for (Edge edge: node.edges){
                    priorityQueue.add(edge);
                }
                while (!priorityQueue.isEmpty()){
                    Edge edge = priorityQueue.poll();
                    Node toNode = edge.to; // 可能新的点
                    if(!set.contains(toNode)){
                        set.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges){
                            priorityQueue.add(nextEdge);
                        }
                    }
                }


            }
        }



        return result;
    }
}
