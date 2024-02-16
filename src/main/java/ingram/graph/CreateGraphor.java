package ingram.graph;

/**
 * @Author qywu11
 * @Date 2022/12/9 11:30
 * @Version 1.0
 */
public class CreateGraphor {

    public static Graph create(Integer[][] matrix) {
        Graph graph = new Graph();

        for (int i = 0; i < matrix.length; i++) {
            Integer from = matrix[i][0];
            Integer to = matrix[i][1];
            Integer wight = matrix[i][2];
            Node fromNode = new Node(from);
            Node toNode = new Node(to);


            if(!graph.nodes.containsKey(from)){
                graph.nodes.put(from, fromNode);
            }
            if(!graph.nodes.containsKey(to)){
                graph.nodes.put(to, fromNode);
            }


            fromNode.out ++;
            toNode.in ++;
            Edge edge = new Edge(wight, fromNode, toNode);

            fromNode.nexts.add(toNode);
            fromNode.edges.add(edge);
            toNode.edges.add(edge);


            graph.edgs.add(edge);
        }


        return graph;
    }
}
