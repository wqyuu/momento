package com.wqy.momento.ingram.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author qywu11
 * @Date 2022/8/24 9:18
 * @Version 1.0
 * 二叉树的序列化和反序列化
 */
public class SerialTree {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 先序遍历 序列化
     * @param head
     * @return
     */
    public static String serialByPre(Node head){
        if(head == null){
            return "#_";
        }
        String res = head.value + "_";
        res += serialByPre(head.left);
        res += serialByPre(head.right);
        return res;
    }

    public static Node reconByPreString(String preStr){
        String[] values = preStr.split("_");
        Queue<String> queue = new LinkedList<>();
        for (int i = 0; i < values.length; i++) {
            queue.add(values[i]);
        }
        return reconPreOrder(queue);
    }

    public static Node reconPreOrder(Queue<String> queue){
        String value = queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right = reconPreOrder(queue);
        return head;
    }


}
