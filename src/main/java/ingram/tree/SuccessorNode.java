package ingram.tree;

/**
 * @Author qywu11
 * @Date 2022/8/24 9:05
 * @Version 1.0
 */
public class SuccessorNode {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data){
            this.value = data;
        }
    }

    public static Node getSuccessorNode(Node node){
        if(node == null){
            return node;
        }
        if(node.right != null){
            return getLeftMost(node.right);
        }else { // 无右子树
            Node parent = node.parent;
            while (parent != null && parent.left != node){ // 当前节点是其父亲节点右孩子
                node = parent;
                parent = node.parent;
            }
            return parent;
        }

    }

    public static Node getLeftMost(Node node){
        if(node == null){
            return node;
        }
        while (node.left != null){
            node = node.left;
        }
        return node;
    }
}
