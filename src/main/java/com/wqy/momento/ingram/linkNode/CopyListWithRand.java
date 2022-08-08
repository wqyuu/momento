package com.wqy.momento.ingram.linkNode;

import java.util.HashMap;

/**
 * @Author qywu11
 * @Date 2022/7/26 9:03
 * @Version 1.0
 * 复制带随机节点的链表
 */
public class CopyListWithRand {

    public static class Node{
        public int value;
        public Node next;
        public Node rand;

        public Node(int data){
            this.value = data;
        }
    }

    public static Node copyListWithRand1(Node head){
        HashMap<Node,Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null){
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }
        cur = head;

        while (cur != null){
            // cur 老
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur  = cur.next;
        }
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head){
        Node cur = head;
        Node next = null;
        // 1 -> 1'->2->2'
        while (cur != null){
            next = cur.next;
            cur.next = new Node(cur.value);
            cur.next.next = next;
        }
        cur = head;
        Node curCopy = null;
        // 1 -> 1'->2->2'
        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            curCopy.rand = cur.rand != null ?cur.rand.next: null;
            cur = next; // 向后走
        }
        Node res = head.next;
        cur = head;
        // 分离出新链表
        while (cur != null){
            next = cur.next.next;
            curCopy = cur.next;
            cur.next = next; // 跳过复制节点
            curCopy.next = next != null ? next.next : null;
            cur = next;  // 向后走
        }
        return res;
    }
}
