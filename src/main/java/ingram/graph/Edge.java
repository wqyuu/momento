package ingram.graph;

/**
 * @Author qywu11
 * @Date 2022/12/9 11:19
 * @Version 1.0
 * 图 - 边
 */
public class Edge {
    public int weight;
    public Node from;
    public Node to;

    public Edge(int weight,Node from,Node to){
        this.weight = weight;
        this.from = from;
        this.to = to;
    }
}
