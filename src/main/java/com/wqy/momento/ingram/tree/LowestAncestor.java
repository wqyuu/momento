package com.wqy.momento.ingram.tree;

/**
 * @Author qywu11
 * @Date 2022/8/19 9:12
 * @Version 1.0
 * 找到node1、node2的最低公共祖先
 */
public class LowestAncestor {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public static Node lowestAncestor(Node head,Node o1,Node o2){
        // 到头了，或者返回的是o1\o2
        if(head == null || head == o1 || head == o2){
            return head;
        }
        Node left = lowestAncestor(head.left,o1,o2);
        Node right = lowestAncestor(head.right,o1,o2);
        // 左右两边汇聚到“我”这个节点上，就返回“我”
        if(left != null && right != null){
            return head;
        }
        // 左右两棵树，并不都有返回值
        return left != null ? left : right;
    }
}
