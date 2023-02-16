package ingram.graph;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author qywu11
 * @Date 2022/12/9 11:06
 * @Version 1.0
 * 图结构
 */
public class Graph {
    public HashMap<Integer,Node> nodes;
    public HashSet<Edge> edgs;

    public Graph(){
        nodes = new HashMap<>();
        edgs = new HashSet<>();
    }
}
