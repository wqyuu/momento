package com.wqy.momento.ingram.tree;

/**
 * @Author qywu11
 * @Date 2022/8/15 8:54
 * @Version 1.0
 */
public class TreeBalanced {

    public static class Node{
        public int value;
        public Node left;
        public Node right;
    }

    public static boolean isBalanced(Node head){
        return process(head).isBalanced;
    }

    public static class ReturnType{
        public boolean isBalanced;
        public int height;
        public ReturnType(boolean isB,int hei){
            isBalanced = isB;
            height = hei;
        }
    }

    public static ReturnType process(Node x){
        if(x == null){
            return new ReturnType(true,0);
        }
        ReturnType leftData = process(x.left);
        ReturnType rightData = process(x.right);
        int height = Math.max(leftData.height, rightData.height) + 1;
        boolean isBalanced = leftData.isBalanced && rightData.isBalanced
               && Math.abs(leftData.height - rightData.height) < 2;
        return new ReturnType(isBalanced,height);
    }
}
