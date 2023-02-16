package ingram.tree;

import java.util.LinkedList;

/**
 * @Author qywu11
 * @Date 2022/8/4 9:27
 * @Version 1.0
 */
public class TestBST {
    public static class Node{
        public int value;
        public Node left;
        public Node right;
    }

    public static int preValue = Integer.MIN_VALUE;

    /**
     * 判断搜索二叉树
     * @param head
     * @return
     */
    public static boolean checkBST(Node head){
        if(head == null){
            return true;
        }
        boolean isLeftBst = checkBST(head.left);
        if(!isLeftBst){
            return false;
        }
        if(head.value <= preValue){
            return false;
        }else {
            preValue = head.value;
        }
        return checkBST(head.right);
    }

    /**
     * 判断完全二叉树
     * 1）任一节点有右无左
     * 2）满足1)，遇到第一个左右不全，接下所以节点都要是叶节点
     * @param head
     * @return
     */
    public static boolean isCBT(Node head){

        if(head == null){
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        boolean left = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            l = head.left;
            r = head.right;

            if((left && (l!=null || r!=null)) // 第一个左右不全,又发现当前节点有叶子节点
                    ||(l == null && r !=null)){
                return false;
            }
            if(l != null){
                queue.add(l);
            }
            if(r != null){
                queue.add(r);
            }
            if(l == null || r == null){
                left = true;
            }

        }
        return true;
    }
}
