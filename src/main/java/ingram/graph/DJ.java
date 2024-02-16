package ingram.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @Author qywu11
 * @Date 2022/12/22 14:43
 * @Version 1.0
 */
public class DJ {



    static Map dijkstra1(Node head){
        HashMap<Node,Integer> distanceMap = new HashMap<>();
        distanceMap.put(head,0);

        HashSet<Node> selectNodes = new HashSet<>();
        Node minNode = getMinDistanceAndUnselectNode(distanceMap,selectNodes);
        while (minNode != null){
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges){
                Node toNode = edge.to;
                if(!distanceMap.containsKey(toNode)){
                    distanceMap.put(toNode,distance + edge.weight);
                }
                distanceMap.put(edge.to, Math.min(distanceMap.get(toNode),
                        distance + edge.weight));
            }
            selectNodes.add(minNode);
            minNode = getMinDistanceAndUnselectNode(distanceMap,selectNodes);
        }
        return distanceMap;
    }

    public static Node getMinDistanceAndUnselectNode(
            HashMap<Node,Integer> distanceMap,
            HashSet<Node> touchNodes
    ){
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()){
            Node node = entry.getKey();
            int distince = entry.getValue();
            if(!touchNodes.contains(node) && distince < minDistance){
                minDistance = distince;
                minNode  = node;
            }
        }
        return minNode;
    }

    public static class NodeRecord{
        public Node node;
        public int distance;

        public NodeRecord(Node node,int distance){
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap{

        private Node[] nodes;
        private HashMap<Node,Integer> headIndexMap;
        private HashMap<Node,Integer> distanceMap;
        private int size;

        public NodeHeap(int size){
            nodes = new Node[size];
            headIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            this.size = 0;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Node node,int distance){
            if(inHeap(node)){
                distanceMap.put(node,Math.min(distanceMap.get(node),distance));
                insertHeapify(node,headIndexMap.get(node));
            }
            if(!isEntered(node)){
                nodes[size] = node;
                headIndexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeapify(node,size ++);
            }
        }

        public NodeRecord pop(){
            NodeRecord nodeRecord = new NodeRecord(nodes[0],distanceMap.get(nodes[0]));
            swap(0,size - 1);
            headIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0,--size);
            return nodeRecord;
        }
        private void insertHeapify(Node node, int index){
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1)/2])){
                swap(index,(index - 1)/2);
                index = (index - 1)/2;
            }
        }

        private void heapify(int index,int size){
            int left = index * 2 +1;
            while (left < size){
                int smallest = left + 1 < size && distanceMap.get(nodes[left + 1])
                        < distanceMap.get(nodes[left]) ? left + 1 : left;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index])
                        ? distanceMap.get(nodes[smallest]) : distanceMap.get(nodes[index]);
                if(smallest == index){
                    break;
                }
                swap(smallest,index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private boolean isEntered(Node node){
            return headIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node){
            return  isEntered(node) && headIndexMap.get(node) != -1;
        }

        private void swap(int index1,int index2){
            headIndexMap.put(nodes[index1],index2);
            headIndexMap.put(nodes[index2],index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }

    }

    // dijkstra优化
    // 从head出发，所有head能到达节点
    public static HashMap<Node,Integer> dijkstra2(Node head,int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head,0);
        HashMap<Node,Integer> result = new HashMap<>();
        while (!nodeHeap.isEmpty()){
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges){
                nodeHeap.addOrUpdateOrIgnore(edge.to,edge.weight + distance);
            }
            result.put(cur,distance);
        }
        return result;
    }


}
