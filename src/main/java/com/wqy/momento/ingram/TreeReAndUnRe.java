package com.wqy.momento.ingram;

import java.util.Stack;

/**
 * @Author qywu11
 * @Date 2022/7/29 8:58
 * @Version 1.0
 * 二叉树 递归实现与非递归实现
 */
public class TreeReAndUnRe {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value = data;
        }
    }

    /**
     * 栈
     * 先序  头左右 1245367
     * 1、从栈中弹出一个节点cur
     * 2、打印（处理）cur.
     * 3、先右再左(如果有)
     * 4 循环
     * @param head
     */
    public static void preOrderUnRecur(Node head){
        if(head!=null){
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()){
                head = stack.pop();
                System.out.print(head.value + " ");
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 后序遍历 s2 4526731
     * s1 头右左
     * s2 左右头
     *
     * 1、弹出cur
     * 2、cur放入收集栈s2
     * 3、先左再右
     * 4、循环
     * @param head
     */
    public static void preOrderUnRecur1(Node head){
        if(head != null){
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.push(head);
            while (!s1.isEmpty()){
                head = s1.pop();
                s2.push(head);
                if(head.left != null){
                    s1.push(head.left);
                }
                if(head.right != null){
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()){
                System.out.println(s2.pop().value + " ");
            }
        }
        System.out.println();
    }


    public static void preOrderUnRecur2(Node h){
         if(h != null){
             Stack<Node> stack = new Stack<Node>();
             stack.push(h);
             Node c = null;
             while (!stack.isEmpty()){
                 c = stack.peek();
                 if(c.left != null && h != c.left && h!= c.right){
                     stack.push(c.left);
                 }else if(c.right != null && h != c.right){
                     stack.push(c.right);
                 }else {
                     System.out.println(stack.pop().value + "");
                     h = c;
                 }
             }
         }
        System.out.println();
    }

    /**
     * 中序
     * 左头右
     * 每棵子树，整棵树左边界进栈，依次弹，打印，对弹出节点的右树重复
     */
    public static void inOrderUnRecur(Node h){
        if(h != null){
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || h!=null){

                if(h!=null){
                    stack.push(h); // 左边进栈
                    h = h.left;
                }else{
                    h = stack.pop(); // 弹出
                    System.out.println(h.value + " ");
                    h = h.right; // 换到右孩子
                }
            }
        }
        System.out.println();
    }


}
