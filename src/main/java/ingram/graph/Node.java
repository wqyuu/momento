package ingram.graph;

import java.util.ArrayList;

/**
 * @Author qywu11
 * @Date 2022/12/9 11:08
 * @Version 1.0
 * 图的  点
 */
public class Node {
    public int value;
    public int in;
    public int out;
    public ArrayList<Node> nexts;
    public ArrayList<Edge> edges;

    public Node(int value){
        this.value = value;
        in = 0;
        out = 0;
        nexts = new ArrayList<>();
        edges = new ArrayList<>();
    }

}
