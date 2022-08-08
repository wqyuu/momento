package com.wqy.momento.ingram.tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Author qywu11
 * @Date 2022/8/3 9:01
 * @Version 1.0
 */
public class TreeWidth {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    public static void w(Node head){
        if(head == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            if(cur.left != null){
                queue.add(cur.left);
            }
            if(cur.right != null){
                queue.add(cur.right);
            }

        }
    }

    public static int w2(Node head){
        if(head == null){
            return -1;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Map<Node,Integer> levelMap = new HashMap<>();
        levelMap.put(head,1);
        int curLevel = 1;
        int curLevelNodes = 0;
        int max = Integer.MIN_VALUE;
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if(curNodeLevel == curLevel){
                curLevelNodes ++;
            } else {
                max = Math.max(max,curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }

            if(cur.left != null){
                levelMap.put(cur.left,curNodeLevel+1);
                queue.add(cur.left);
            }
            if(cur.right != null){
                levelMap.put(cur.right,curNodeLevel+1);
                queue.add(cur.right);
            }

        }
        return max;
    }
}
