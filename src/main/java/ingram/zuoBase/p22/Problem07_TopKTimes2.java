package ingram.zuoBase.p22;

import java.util.HashMap;

public class Problem07_TopKTimes2 {

    public static class Node {
        public String str; // 输入的字符串
        public int times; // 词频

        public Node(String s, int t) {
            str = s;
            times = t;
        }
    }

    public static class TopKRecord {
        private Node[] heap;
        private int heapSize;
        private HashMap<String, Node> strNodeMap; // 词频表
        private HashMap<Node, Integer> nodeIndexMap; // 堆位置map

        public TopKRecord(int K) {
            heap = new Node[K];
            heapSize = 0;
            strNodeMap = new HashMap<String, Node>();
            nodeIndexMap = new HashMap<Node, Integer>();
        }

        public void add(String str) {
            Node curNode = null;
            int preIndex = -1; // 堆位置map的位置，默认不在堆位置map中设置-1
            if (!strNodeMap.containsKey(str)) { // 不在词频表中
                curNode = new Node(str, 1);
                strNodeMap.put(str, curNode);
                nodeIndexMap.put(curNode, -1);
            } else {
                curNode = strNodeMap.get(str);
                curNode.times++; // 原来就在词频表中，词频增加
                preIndex = nodeIndexMap.get(curNode);
            }
            if (preIndex == -1) { // 不在堆中
                if (heapSize == heap.length) { // 堆满了
                    if (heap[0].times < curNode.times) { // 跟最上面的元素词频做对比，大于原来堆顶元素，插进堆
                        nodeIndexMap.put(heap[0], -1); // 原来元素出去
                        nodeIndexMap.put(curNode, 0); // 当前元素放到堆顶
                        heap[0] = curNode; // 当前元素放到堆顶，替换原来
                        heapify(0, heapSize); // 调整小根堆位置
                    }
                } else { // 堆没满
                    nodeIndexMap.put(curNode, heapSize); // 直接放到堆中
                    heap[heapSize] = curNode; // 直接放到堆中
                    heapInsert(heapSize++); // 放入堆后，调整小根堆位置
                }
            } else { // 本身就在堆中，调整小根堆位置
                heapify(preIndex, heapSize);
            }
        }

        public void printTopK() {
            System.out.println("TOP: ");
            for (int i = 0; i != heap.length; i++) {
                if (heap[i] == null) {
                    break;
                }
                System.out.print("Str: " + heap[i].str);
                System.out.println(" Times: " + heap[i].times);
            }
        }

        private void heapInsert(int index) {
            while (index != 0) {
                int parent = (index - 1) / 2;
                if (heap[index].times < heap[parent].times) {
                    swap(parent, index);
                    index = parent;
                } else {
                    break;
                }
            }
        }

        private void heapify(int index, int heapSize) {
            int l = index * 2 + 1;
            int r = index * 2 + 2;
            int smallest = index;
            while (l < heapSize) {
                if (heap[l].times < heap[index].times) {
                    smallest = l;
                }
                if (r < heapSize && heap[r].times < heap[smallest].times) {
                    smallest = r;
                }
                if (smallest != index) {
                    swap(smallest, index);
                } else {
                    break;
                }
                index = smallest;
                l = index * 2 + 1;
                r = index * 2 + 2;
            }
        }

        private void swap(int index1, int index2) {
            nodeIndexMap.put(heap[index1], index2);
            nodeIndexMap.put(heap[index2], index1);
            Node tmp = heap[index1];
            heap[index1] = heap[index2];
            heap[index2] = tmp;
        }
    }
}